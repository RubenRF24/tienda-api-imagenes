package com.tienda.tienda.servicio;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tienda.tienda.modelo.Producto;

public interface ProductoServicio {

    List<Producto> listarProducto(String palabraClave);

    Producto obtenerProducto(Long id);

    Producto guardarProducto(Producto producto);

    void eliminarProducto(Long id);

    Producto convertJsonToProducto(String stringMeme) throws JsonProcessingException;

}
