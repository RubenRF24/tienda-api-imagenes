package com.tienda.tienda.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda.tienda.excepciones.ResourceNotFoundException;
import com.tienda.tienda.modelo.Producto;
import com.tienda.tienda.repositorio.ProductoRepositorio;
import com.tienda.tienda.servicio.ProductoServicio;

@Service
public class ProductoServicioImp implements ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> listarProducto(String palabraClave) {
        if (palabraClave != null) {
            return productoRepositorio.findAll(palabraClave);
        }
        return productoRepositorio.findAll();
    }

    @Override
    public Producto obtenerProducto(Long id) {
        return productoRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el id: " + id));
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepositorio.deleteById(id);
    }

    @Override
    public Producto convertJsonToProducto(String stringProducto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Producto producto = objectMapper.readValue(stringProducto, Producto.class);
        return producto;
    }

}
