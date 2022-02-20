import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constant } from 'src/constants/constant';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(
    private http: HttpClient
  ) { }

  public uploadFile(file: FormData): Observable<any> {
    return this.http.post(`${Constant.COMMON_URL}/files/upload`, file);
  }
}
