import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from './producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  //Esta URL obtiene el listado de todos los empleados
  private baseURL = 'http://localhost:8080/api/productos';
  
  constructor(private httpClient: HttpClient) { }
  
  //Este metodo nos sirve para obtener los Productos
  obtenerListaDeProductos(): Observable<Producto[]>{
    return this.httpClient.get<Producto[]>(`${this.baseURL}`);
  }

  //Este metodo nos sirve para obtener un Producto por su id
  obtenerProductoPorId(id: number): Observable<Producto> {
    return this.httpClient.get<Producto>(`${this.baseURL}/${id}`);
  }

  obtenerProductoPorNombre(nombre: string): Observable<Producto[]> {
    return this.httpClient.get<Producto[]>(`${this.baseURL}?palabraClave=${nombre}`);
  }

  //Este metodo nos sirve para registrar un Producto
  registrarProducto(producto, file: File): Observable<Object> {
    let formData = new FormData();
    formData.append('file', file);
    formData.append('producto', JSON.stringify(producto));
    return this.httpClient.post(`${this.baseURL}`, formData);
  }

}
