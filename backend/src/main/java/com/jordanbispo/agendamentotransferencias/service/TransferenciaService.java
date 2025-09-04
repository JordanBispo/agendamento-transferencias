package com.jordanbispo.agendamentotransferencias.service;

import com.jordanbispo.agendamentotransferencias.model.Transferencia;
import com.jordanbispo.agendamentotransferencias.repository.TransferenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransferenciaService {
  private final TransferenciaRepository repo;
  public TransferenciaService(TransferenciaRepository repo) {
    this.repo = repo;
  }

  @Transactional
  public Transferencia agendar(Transferencia t) {
    LocalDate hoje = LocalDate.now();
    long dias = ChronoUnit.DAYS.between(hoje, t.getDataTransferencia());
    BigDecimal taxa = calcularTaxa(dias, t.getValor());
    t.setTaxa(taxa);
    t.setDataAgendamento(hoje);
    return repo.save(t);
  }

  public List<Transferencia> listar() {
    return repo.findAll();
  }

  private BigDecimal calcularTaxa(long dias, BigDecimal valor) {
    if (dias >= 0 && dias <= 0)     return valor.multiply(BigDecimal.valueOf(0.025));
    if (dias >= 1 && dias <= 10)    return BigDecimal.ZERO;
    if (dias >= 11 && dias <= 20)   return valor.multiply(BigDecimal.valueOf(0.082));
    if (dias >= 21 && dias <= 30)   return valor.multiply(BigDecimal.valueOf(0.069));
    if (dias >= 31 && dias <= 40)   return valor.multiply(BigDecimal.valueOf(0.047));
    if (dias >= 41 && dias <= 50)   return valor.multiply(BigDecimal.valueOf(0.017));
    throw new IllegalArgumentException("Não existe faixa de taxa para " + dias + " dias.");
  }
}