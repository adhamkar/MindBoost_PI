import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private baseUrl = "http://localhost:8085/posts";
  private url = "http://localhost:8085";

  constructor(private http: HttpClient) { }

  public getCommentsFromPost(id:string):Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.baseUrl}`);
  }

  public saveComment(postId:number,comment: Comment): Observable<Object> {
    return this.http.post(`${this.url}/comment_pt/${postId}`, comment);
  }

  public deleteComment(id:number): Observable<Object> {
    return this.http.delete<Comment>(`${this.baseUrl}`+"/comments/"+id);
  }
}
