import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';

@Component({
  selector: 'app-registrar-producto',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './registrar-producto.component.html',
  styleUrl: './registrar-producto.component.css'
})
export class RegistrarProductoComponent {

  producto: Producto = new Producto();
  file: File | null = null;

  constructor(private productoServicio: ProductoService, private router:Router) { }
  
  ngOnInit() : void {
  }

  handleFilesChanges(event: any) : void {
    this.file = event.target.files[0];
  }

  guardarProducto() {
    const producto = {
      nombre: this.producto.nombre,
      precio: this.producto.precio,
      cantidad: this.producto.cantidad
    };

    this.productoServicio.registrarProducto(producto, this.file).subscribe(dato => {
      console.log(dato);
      this.irALaListaDeProductos();
    }, error => console.log(error))
  }

  irALaListaDeProductos() {
    this.router.navigate(['/productos']);  
  }
  
  onSubmit() {
    this.guardarProducto();
  }

}
