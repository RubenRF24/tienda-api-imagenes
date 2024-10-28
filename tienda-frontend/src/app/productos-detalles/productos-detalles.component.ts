import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';

@Component({
  selector: 'app-productos-detalles',
  standalone: true,
  imports: [],
  templateUrl: './productos-detalles.component.html',
  styleUrl: './productos-detalles.component.css'
})
export class ProductosDetallesComponent {

  id: number;
  producto: Producto;

  constructor(private route: ActivatedRoute, private productoServicio: ProductoService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.producto = new Producto();
    this.productoServicio.obtenerProductoPorId(this.id).subscribe(dato => {
      this.producto = dato;
      Swal.fire( 'Detalles del producto '+this.producto.nombre);
    });
  }
  
}
