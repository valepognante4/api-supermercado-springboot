package com.ejerciciofinal.PreubaTecSupermercado.repository;

import com.ejerciciofinal.PreubaTecSupermercado.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
