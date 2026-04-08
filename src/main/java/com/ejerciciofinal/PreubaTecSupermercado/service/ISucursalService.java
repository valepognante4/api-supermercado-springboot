package com.ejerciciofinal.PreubaTecSupermercado.service;

import com.ejerciciofinal.PreubaTecSupermercado.dto.SucursalDTO;
import com.ejerciciofinal.PreubaTecSupermercado.model.Sucursal;

import java.util.List;

public interface ISucursalService {

    List<SucursalDTO> traerSucursales();
    SucursalDTO crearSucursal(SucursalDTO sucursalDto);
    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto);
    void eliminarSucursal(Long id);
}
