package com.tienda.tienda.controlador;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tienda.tienda.modelo.Producto;
import com.tienda.tienda.servicio.IUplooadFileService;
import com.tienda.tienda.servicio.ProductoServicio;

@RestController
@RequestMapping("/api")
public class TiendaControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private IUplooadFileService uploadFileService;

    @GetMapping("/productos")
    public ResponseEntity<?> listarProductos(@Param("palabraClave") String palabraClave) {

        try {
            List<Producto> productos = productoServicio.listarProducto(palabraClave);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al listar los productos\n" + e.getMessage());

        }
    }

    @PostMapping(value = "/productos", consumes =

    { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> guardarProducto(@RequestPart("producto") String stringProducto,
            @RequestPart("file") MultipartFile image) {
        try {
            Producto producto = productoServicio.convertJsonToProducto(stringProducto);
            if (producto.getId() == null) {
                producto.setId(0L);
            }
            System.out.println(producto.toString());
            System.out.println(image);
            System.out.println(!image.isEmpty());
            if (!image.isEmpty()) {
                if (producto.getId() > 0 && producto.getImageUrl() != null && producto.getImageUrl().length() > 0) {
                    uploadFileService.delete(producto.getImageUrl());
                }
                System.out.println("guardando imagen");
                String imageUrl = uploadFileService.copy(image);
                System.out.println(imageUrl);
                System.out.println("guarado imagen");
                producto.setImageUrl(imageUrl);
            }
            System.out.println(producto.toString());
            productoServicio.guardarProducto(producto);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al guardar el producto\n" + e.getMessage());
        }
    }

    @GetMapping(value = "productos/uploads/{filename}")
    public ResponseEntity<?> getMethodName(@PathVariable String filename) {
        Resource resource = null;
        try {
            resource = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getMethodName(@PathVariable(name = "id") Long id) {

        Producto producto = productoServicio.obtenerProducto(id);

        return ResponseEntity.ok(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> putMethodName(@PathVariable(name = "id") Long id,
            @RequestBody Producto detallesProducto) {

        Producto producto = productoServicio.obtenerProducto(id);

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());
        producto.setCantidad(detallesProducto.getCantidad());

        Producto productoActualizado = productoServicio.guardarProducto(producto);

        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMethodName(@PathVariable(name = "id") Long id) {

        Producto producto = productoServicio.obtenerProducto(id);

        productoServicio.eliminarProducto(producto.getId());

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);

        return ResponseEntity.ok(respuesta);
    }

}
