import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {TherapieSession} from "../models/therapie-session.model";
import {WebRtcService} from "../services/web-rtc.service";
import {WebSocketServiceService} from "../services/web-socket-service.service";
import {TherapieSessionService} from "../services/therapie-session.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrl: './patients.component.css'
})
export class PatientsComponent implements OnInit, AfterViewInit  {
  Sessions: TherapieSession[] = [];
  localStream: MediaStream | null = null;
  remoteStream: MediaStream | null = null;
  activeSession: TherapieSession | null = null;

  @ViewChild('localVideo') localVideo!: ElementRef<HTMLVideoElement>;
  @ViewChild('remoteVideo') remoteVideo!: ElementRef<HTMLVideoElement>;

  constructor(
    private webRTCService: WebRtcService,
    private webSocketService: WebSocketServiceService,
    private sessionservice: TherapieSessionService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.webRTCService.localStream$.subscribe((stream: MediaStream | null) => {
      this.localStream = stream;
      if (this.localVideo && this.localStream) {
        this.localVideo.nativeElement.srcObject = this.localStream;
      }
    });

  /*  this.sessionservice.getAllSessions().subscribe(data => {
      this.Sessions = data;
    });*/
    this.sessionservice.getTherapistSessions(this.authService.getUserId()).subscribe(
      data=>{
        this.Sessions = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
      )

    this.webRTCService.remoteStream$.subscribe((stream: MediaStream | null) => {
      this.remoteStream = stream;
      if (this.remoteVideo && this.remoteStream) {
        this.remoteVideo.nativeElement.srcObject = this.remoteStream;
      }
    });
  }

  ngAfterViewInit(): void {
    if (this.localStream) {
      this.localVideo.nativeElement.srcObject = this.localStream;
    }
    if (this.remoteStream) {
      this.remoteVideo.nativeElement.srcObject = this.remoteStream;
    }
  }

  startSession(session: TherapieSession) {
    this.activeSession = session;
    this.webRTCService.startCall();
    this.webSocketService.sendMessage({ type: 'session_initiation', sessionId: session.id });
  }

  endSession() {
    this.webRTCService.endCall();
    this.webSocketService.sendMessage({ type: 'session_termination', sessionId: this.activeSession?.id });
    this.activeSession = null;
  }
}
