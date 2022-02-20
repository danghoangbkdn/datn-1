import { Injectable } from '@angular/core';

import { Post } from '../_models/post';
import {Constant} from 'src/constants/constant';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  public getDailyPost() {
    return this.http.get<any>(Constant.POSTS_URL + '/last4');
  }

  public getPostsByStatus(status: string) {
    return this.http.get<any>(Constant.URL_API + '/admin/api/posts?status=' + status);
  }

  public getPostsMonthlyFigure() {
    return this.http.get<any>(Constant.POSTS_URL + '/register-figure');
  }

  public updatePostByAdmin(postId: number, status: string) {
    return this.http.put<any>(Constant.URL_API + '/admin/api/post/' + postId, {status});
  }

}
