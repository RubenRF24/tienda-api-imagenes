package com.tienda.tienda.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_productos")
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Producto")
    private Long id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Precio", nullable = false)
    private Double precio;

    @Column(name = "Cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "Image_Url", nullable = false)
    private String imageUrl;

    public Producto() {
    }

    public Producto(String nombre, Double precio, Integer cantidad, String imageUrl) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad
                + ", imageUrl=" + imageUrl + "]";
    }

}
