import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';

@Component({
  selector: 'app-lista-productos',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './lista-productos.component.html',
  styleUrl: './lista-productos.component.css'
})
export class ListaProductosComponent {

  nombre: string;
  productos: Producto[];

  constructor(private productoServicio: ProductoService, private router: Router) { }
  
  ngOnInit(): void {
    this.obtenerProductos();
  }

   private obtenerProductos() {
    this.productoServicio.obtenerListaDeProductos().subscribe(dato => {
      this.productos = dato;
    });
  }

  verDetallesDelProducto(id: number) {
    this.router.navigate(['productos-detalles', id])
  }

  buscarProductoPorNombre(nombre: string) {
    this.productoServicio.obtenerProductoPorNombre(nombre).subscribe(dato => {
      this.productos = dato;
    });
  }

  onSubmit() {
    const inputElement = <HTMLInputElement>document.getElementById('palabraClave');
    this.nombre = inputElement.value;
  
    this.buscarProductoPorNombre(this.nombre);
  }
}
