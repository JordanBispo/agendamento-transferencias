package com.jordanbispo.agendamentotransferencias.repository;

import com.jordanbispo.agendamentotransferencias.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> { }