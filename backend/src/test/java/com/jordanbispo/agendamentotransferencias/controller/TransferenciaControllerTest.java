package com.jordanbispo.agendamentotransferencias.controller;

import com.jordanbispo.agendamentotransferencias.model.Transferencia;
import com.jordanbispo.agendamentotransferencias.service.TransferenciaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferenciaController.class)
class TransferenciaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransferenciaService service;

    @Test
    void postSucesso() throws Exception {
        LocalDate data = LocalDate.now();
        Transferencia t = Transferencia.builder()
                .contaOrigem("111")
                .contaDestino("222")
                .valor(BigDecimal.TEN)
                .dataTransferencia(data)
                .build();
        when(service.agendar(any())).thenReturn(t);
        mvc.perform(post("/api/transferencias")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"contaOrigem\":\"111\",\"contaDestino\":\"222\",\"valor\":10,\"dataTransferencia\":\"" + data + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contaOrigem").value("111"));
    }

    @Test
    void postBadRequest() throws Exception {
        when(service.agendar(any())).thenThrow(new IllegalArgumentException());
        mvc.perform(post("/api/transferencias")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"contaOrigem\":\"x\",\"contaDestino\":\"y\",\"valor\":1,\"dataTransferencia\":\"2100-01-01\"}"))
                .andExpect(status().isBadRequest());
    }
}
