import {Component, OnInit} from '@angular/core';
import {WebSocketServiceService} from "../services/web-socket-service.service";
import {WebRtcService} from "../services/web-rtc.service";

@Component({
  selector: 'app-call',
  templateUrl: './call.component.html',
  styleUrl: './call.component.css'
})
export class CallComponent  implements OnInit{
  localStream!: MediaStream;
  remoteStream!: MediaStream;

  constructor(
    private webRTCService: WebRtcService,
    private webSocketService: WebSocketServiceService
  ) {}

  ngOnInit(): void {
    this.webRTCService.localStream$.subscribe(stream => {
      this.localStream = stream!;
    });

    this.webRTCService.remoteStream$.subscribe(stream => {
      this.remoteStream = stream!;
    });
  }

  joinCall() {
    console.log('Joining the call...');
    this.webSocketService.sendMessage({ type: 'join_call' });
    alert('Joining the call...');
  }
}
