import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {webSocket,WebSocketSubject} from "rxjs/webSocket";

@Injectable({
  providedIn: 'root'
})
export class WebSocketServiceService {
  private socket$: WebSocketSubject<any>;
  private messagesSubject: Subject<any> = new Subject();
  public messages: Observable<any> = this.messagesSubject.asObservable();

  constructor() {
    this.socket$ = webSocket({
      url: 'ws://localhost:8080',
      deserializer: async ({ data }) => {
        if (typeof data === 'string') {
          return JSON.parse(data);
        } else if (data instanceof Blob) {
          const text = await data.text();
          return JSON.parse(text);
        } else {
          return data;
        }
      }
    });

    this.socket$.subscribe(
      msg => this.messagesSubject.next(msg),
      err => console.error(err)
    );
  }
  sendMessage(msg: any) {
    console.log('Sending message:', msg);
    this.socket$.next(JSON.stringify(msg));
  }
}
