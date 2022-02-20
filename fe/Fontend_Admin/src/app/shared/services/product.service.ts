import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constant } from 'src/constants/constant';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public getAllProduct(): Observable<Product[]> {
    return this.http.get<Product[]>(`${Constant.COMMON_URL}/products`);
  }

  public getAllProductByNameContain(name: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${Constant.COMMON_URL}/products/search?name=${name}`);
  }

  public getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(`${Constant.COMMON_URL}/products/${id}`);
  }

  public createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${Constant.COMMON_URL}/products`, product);
  }

  public updateProduct(id: number, product: Product): Observable<Product> {
    return this.http.put<Product>(`${Constant.COMMON_URL}/products/${id}`, product);
  }

  public deteleProduct(id: number): Observable<any> {
    return this.http.delete<any>(`${Constant.COMMON_URL}/products/${id}`);
  }
}
