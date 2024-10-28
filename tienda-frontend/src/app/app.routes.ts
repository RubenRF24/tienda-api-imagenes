import { Routes } from '@angular/router';
import { ListaProductosComponent } from './lista-productos/lista-productos.component';
import { ProductosDetallesComponent } from './productos-detalles/productos-detalles.component';
import { RegistrarProductoComponent } from './registrar-producto/registrar-producto.component';

export const routes: Routes = [
    { path: 'productos', component: ListaProductosComponent },
    { path: '', redirectTo: 'productos', pathMatch: 'full' },
    { path: 'productos-detalles/:id', component: ProductosDetallesComponent },
    { path: 'registrar-producto', component: RegistrarProductoComponent }
];
