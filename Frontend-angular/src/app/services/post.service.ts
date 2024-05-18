import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../models/post.model";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl = "http://localhost:8085/posts";
  private url = "http://localhost:8085";

  constructor(private http: HttpClient) { }

  public getAllPosts():Observable<Post[]> {
    return this.http.get<Post[]>(`${this.baseUrl}`);
  }

  public getPost(id:string):Observable<Post> {
    return this.http.get<Post>(`${this.baseUrl}`+"/"+id);
  }

  public getComments(id:string):Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.baseUrl}`+"/"+id+"/comments");
  }
  public createPost(post: Post): Observable<Object> {

    return this.http.post(`${this.baseUrl}`+"/patient_post", post);
  }
  public createPatientPost(post: any) {
    return this.http.post(`${this.url}/patient_post`, post);
  }

  // Méthode pour créer un post pour un thérapeute
  public createTherapistPost(post: Post, therapistID: number): Observable<Post> {
    return this.http.post<Post>(`${this.baseUrl}/therapist_post?therapistID=${therapistID}`, post);
  }
  public updatePost(id: string, post:Post): Observable<Object> {
    return this.http.put(`${this.baseUrl}`+"/"+id, post);
  }
  public deletePost(id:string): Observable<Object> {
    return this.http.delete<Post>(`${this.baseUrl}`+"/"+id);
  }
}
