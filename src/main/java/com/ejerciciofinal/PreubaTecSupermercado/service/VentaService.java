package com.ejerciciofinal.PreubaTecSupermercado.service;

import com.ejerciciofinal.PreubaTecSupermercado.dto.DetalleVentaDTO;
import com.ejerciciofinal.PreubaTecSupermercado.dto.VentaDTO;
import com.ejerciciofinal.PreubaTecSupermercado.exception.NotFoundException;
import com.ejerciciofinal.PreubaTecSupermercado.mapper.Mapper;
import com.ejerciciofinal.PreubaTecSupermercado.model.DetalleVenta;
import com.ejerciciofinal.PreubaTecSupermercado.model.Producto;
import com.ejerciciofinal.PreubaTecSupermercado.model.Sucursal;
import com.ejerciciofinal.PreubaTecSupermercado.model.Venta;
import com.ejerciciofinal.PreubaTecSupermercado.repository.ProductoRepository;
import com.ejerciciofinal.PreubaTecSupermercado.repository.SucursalRepository;
import com.ejerciciofinal.PreubaTecSupermercado.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private SucursalRepository sucursalRepo;

    @Override
    public List<VentaDTO> traerVentas() {

        List<Venta> ventas = ventaRepo.findAll();
        List<VentaDTO> ventasDto = new ArrayList<>();

        VentaDTO dto;
        for(Venta v : ventas){
            dto = Mapper.toDTO(v);
            ventasDto.add(dto);
        }

        return ventasDto;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {

        //Validaciones
        if (ventaDto == null) throw new RuntimeException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Debe incluir la sucursal");
        if (ventaDto.getIdSucursal() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        //Buscar la sucursal
        Sucursal suc = sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);
        if(suc == null){
           throw new NotFoundException("Sucursal no encontrada");
        }

        //Crear la venta
        Venta vent = new Venta();
        vent.setFecha(ventaDto.getFecha());
        vent.setEstado(ventaDto.getEstado());
        vent.setSucursal(suc);
        vent.setTotal(ventaDto.getTotal());

        //La lista de detalles
        //-- Acá están los productos
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for(DetalleVentaDTO detDTO : ventaDto.getDetalle()){
            //Buscar producto por id (tu detDTO usa id como id de producto)
            Producto p = productoRepo.findByNombre(detDTO.getNombreProd()).orElse(null);
            if(p == null) {
                throw new RuntimeException("Producto no encontrado: " + detDTO.getNombreProd());
            }

            //Crear detalle
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProducto(p);
            detalleVent.setPrecio(detDTO.getPrecio());
            detalleVent.setCantProd(detDTO.getCantProd());
            detalleVent.setVenta(vent);

            detalles.add(detalleVent);
            totalCalculado = totalCalculado + (detDTO.getPrecio() * detDTO.getCantProd());
        }

        //Seteamos la lista de detalle venta
        vent.setDetalle(detalles);

        //Guardamos en la BD
        vent = ventaRepo.save(vent);

        //Mapeo de salida
        VentaDTO ventaSalida = Mapper.toDTO(vent);

        return ventaSalida;

    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
        //Buscar si la venta existe para actualizarla
        Venta v = ventaRepo.findById(id).orElse(null);
        if(v == null) throw new RuntimeException("Venta no encontrada");

        if(ventaDto.getFecha() != null) {
           v.setFecha(ventaDto.getFecha());
        }

        if(ventaDto.getEstado() != null) {
            v.setEstado(ventaDto.getEstado());
        }

        if(ventaDto.getTotal() != null) {
            v.setTotal(ventaDto.getTotal());
        }

        if(ventaDto.getIdSucursal() != null) {
            Sucursal suc = sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);
            if(suc == null) throw new RuntimeException("Sucursal no encontrada");
            v.setSucursal(suc);
        }

        ventaRepo.save(v);

        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if(v == null) throw new RuntimeException("Venta no encontrada");
        ventaRepo.delete(v);
    }
}
