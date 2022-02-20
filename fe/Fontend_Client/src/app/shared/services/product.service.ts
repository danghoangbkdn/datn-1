import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Login } from '../models/login.model';
import { Constant } from '../constants/Constant';
import { Product } from '../models/product.model';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private currentUserSubject: BehaviorSubject<Login>
  public currentUser: Observable<Login>

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<Login>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public getAllProducts() {
    return this.http.get<Product[]>(`${Constant.PRODUCT_URL}`).pipe(
          map((products: Product[]) =>
          products.map((product:Product) => this.setProductCovers(product))));
  }

  public getProductById(bookId: number): Observable<Product> {
    return this.http.get<any>(Constant.PRODUCT_URL + '/' + bookId)
                .pipe(map((product: Product) => this.setProductCovers(product)));
  }

  public createNewProduct(title: string, author: string, description: string, image: string) {
    return this.http.post<any>(Constant.PRODUCT_URL, {title, author, description, image});
  }

  public updateProduct(bookId: number, title: string, author: string, description: string, image: string) {
    return this.http.put<any>(Constant.PRODUCT_URL + '/' + bookId, {title, author, description, image});
  }

  public deleteProduct(bookId: number) {
    return this.http.delete<any>(Constant.PRODUCT_URL + '/' + bookId);
  }

  public searchProductByTitleOrAuthor(title: string, author: string) {
    return this.http.get<any>(Constant.PRODUCT_URL + '/find?title=' + title + '&author=' + author);
  }

  private setProductCovers(product: Product): Product {
    product.covers = product.image.split(',');
    return product;
  }
}
