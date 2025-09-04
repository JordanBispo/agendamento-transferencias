package com.jordanbispo.agendamentotransferencias.controller;

import com.jordanbispo.agendamentotransferencias.model.Transferencia;
import com.jordanbispo.agendamentotransferencias.service.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {
  private final TransferenciaService service;
  public TransferenciaController(TransferenciaService service){
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Transferencia> criar(@RequestBody Transferencia t){
    try {
      return ResponseEntity.ok(service.agendar(t));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @GetMapping
  public List<Transferencia> listar(){
    return service.listar();
  }
}