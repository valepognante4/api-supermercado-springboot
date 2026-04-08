package com.ejerciciofinal.PreubaTecSupermercado.service;

import com.ejerciciofinal.PreubaTecSupermercado.dto.ProductoDTO;
import com.ejerciciofinal.PreubaTecSupermercado.model.Producto;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> traerProductos();
    ProductoDTO crearProducto(ProductoDTO productoDto);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto);
    void eliminarProducto(Long id);
}
