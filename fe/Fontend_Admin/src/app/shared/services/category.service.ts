import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constant } from 'src/constants/constant';
import { Category } from '../models/category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }

  public getAllCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(`${Constant.COMMON_URL}/categories`);
  }

  public getAllRootCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(`${Constant.COMMON_URL}/categories/root-categories`);
  }

  public getAllByParentCategory(parentId: number): Observable<Category[]> {
    return this.http.get<Category[]>(`${Constant.COMMON_URL}/categories/parent-category/${parentId}`);
  }

  public post(category: Category): Observable<Category> {
    return this.http.post<Category>(`${Constant.COMMON_URL}/categories`, category);
  }

  public put(id: number, category: Category): Observable<Category> {
    return this.http.put<Category>(`${Constant.COMMON_URL}/categories/${id}`, category);
  }

  public delete(id: number): Observable<any> {
    return this.http.delete(`${Constant.COMMON_URL}/categories/${id}`);
  }

}
