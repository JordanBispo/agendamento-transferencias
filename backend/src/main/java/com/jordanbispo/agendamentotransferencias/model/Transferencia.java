package com.jordanbispo.agendamentotransferencias.model;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transferencia {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String contaOrigem;
  private String contaDestino;
  private BigDecimal valor;
  private BigDecimal taxa;
  private LocalDate dataTransferencia;
  private LocalDate dataAgendamento;
}