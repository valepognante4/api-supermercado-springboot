package com.ejerciciofinal.PreubaTecSupermercado.service;

import com.ejerciciofinal.PreubaTecSupermercado.dto.VentaDTO;
import com.ejerciciofinal.PreubaTecSupermercado.model.Venta;

import java.util.List;

public interface IVentaService {

    List<VentaDTO> traerVentas();
    VentaDTO crearVenta(VentaDTO ventaDto);
    VentaDTO actualizarVenta(Long id, VentaDTO ventaDto);
    void eliminarVenta(Long id);

}
