import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constant } from 'src/constants/constant';
import { Entity } from '../models/entity.model';

@Injectable({
  providedIn: 'root'
})
export class EntityService {

  constructor(
    private http: HttpClient
  ) { }

  public getEntityByProductId(productId: number): Observable<Entity> {
    return this.http.get<Entity>(`${Constant.COMMON_URL}/entities/product/${productId}`);
  }

  public updateEntity(id: number, entity: Entity): Observable<Entity> {
    return this.http.put<Entity>(`${Constant.COMMON_URL}/entities/${id}`, entity);
  }
}
