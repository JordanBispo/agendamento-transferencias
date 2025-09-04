package com.jordanbispo.agendamentotransferencias.service;

import com.jordanbispo.agendamentotransferencias.model.Transferencia;
import com.jordanbispo.agendamentotransferencias.repository.TransferenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferenciaServiceTest {

    @Mock
    private TransferenciaRepository repo;

    @InjectMocks
    private TransferenciaService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCalcularTaxaParaHoje() {
        LocalDate hoje = LocalDate.now();
        Transferencia t = Transferencia.builder()
                .valor(BigDecimal.valueOf(100))
                .dataTransferencia(hoje)
                .build();

        when(repo.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Transferencia resultado = service.agendar(t);

        // 0 dias → 2.5% de 100 = 2.5
        assertEquals(BigDecimal.valueOf(2.5), resultado.getTaxa());
        assertEquals(hoje, resultado.getDataAgendamento());
        verify(repo).save(resultado);
    }

    @Test
    void deveLancarParaFaixaInvalida() {
        LocalDate futuro = LocalDate.now().plusDays(100);
        Transferencia t = Transferencia.builder()
                .valor(BigDecimal.ONE)
                .dataTransferencia(futuro)
                .build();

        assertThrows(IllegalArgumentException.class, () -> service.agendar(t));
        verify(repo, never()).save(any());
    }
}
