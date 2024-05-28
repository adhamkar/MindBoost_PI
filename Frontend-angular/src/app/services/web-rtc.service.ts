import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import { WebSocketServiceService } from './web-socket-service.service';

@Injectable({
  providedIn: 'root'
})
export class WebRtcService {
  private peerConnection!: RTCPeerConnection;
  private localStream = new BehaviorSubject<MediaStream | null>(null);
  private remoteStream = new BehaviorSubject<MediaStream | null>(null);

  localStream$ = this.localStream.asObservable();
  remoteStream$ = this.remoteStream.asObservable();

  constructor(private webSocketService: WebSocketServiceService) {
    this.webSocketService.messages.subscribe(async message => {
      if (message instanceof Promise) {
        message = await message;
      }
      this.handleSignalingData(message);
    });

  }

  async startCall() {
    console.log('Starting call...');
    this.peerConnection = new RTCPeerConnection();

    const stream = await navigator.mediaDevices.getUserMedia({ video: true, audio: true });
    this.localStream.next(stream);
    stream.getTracks().forEach(track => this.peerConnection.addTrack(track, stream));

    const offer = await this.peerConnection.createOffer();
    await this.peerConnection.setLocalDescription(offer);

    this.webSocketService.sendMessage({ type: 'offer', data: offer });
    console.log('Offer sent:', offer);

    this.peerConnection.ontrack = (event) => {
      this.remoteStream.next(event.streams[0]);
    };

    this.peerConnection.onicecandidate = (event) => {
      if (event.candidate) {
        this.webSocketService.sendMessage({ type: 'ice-candidate', data: event.candidate });
      }
    };
  }

  async handleOffer(offer: RTCSessionDescriptionInit) {
    console.log('Handling offer:', offer);
    this.peerConnection = new RTCPeerConnection();

    const stream = await navigator.mediaDevices.getUserMedia({ video: true, audio: true });
    this.localStream.next(stream);
    stream.getTracks().forEach(track => this.peerConnection.addTrack(track, stream));

    await this.peerConnection.setRemoteDescription(new RTCSessionDescription(offer));

    const answer = await this.peerConnection.createAnswer();
    await this.peerConnection.setLocalDescription(answer);

    this.webSocketService.sendMessage({ type: 'answer', data: answer });
    console.log('Answer sent:', answer);

    this.peerConnection.ontrack = (event) => {
      this.remoteStream.next(event.streams[0]);
    };

    this.peerConnection.onicecandidate = (event) => {
      if (event.candidate) {
        this.webSocketService.sendMessage({ type: 'ice-candidate', data: event.candidate });
      }
    };
  }

  async handleAnswer(answer: RTCSessionDescriptionInit) {
    console.log('Handling answer:', answer);
    await this.peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
  }

  async handleIceCandidate(candidate: RTCIceCandidate) {
    console.log('Handling ICE candidate:', candidate);
    await this.peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
  }

  async handleSignalingData(data: any) {
    console.log('Signaling data received:', data);

    // Check if data is a promise
    if (data instanceof Promise) {
      data = await data;
    }

    if (!data || !data.type) {
      console.error('Received signaling data is null or undefined or missing type:', data);
      return;
    }

    try {
      switch (data.type) {
        case 'offer':
          await this.handleOffer(data.data);
          break;
        case 'answer':
          await this.handleAnswer(data.data);
          break;
        case 'ice-candidate':
          await this.handleIceCandidate(data.data);
          break;
        case 'join_call':
          console.log('Patient is joining the call');
          break;
        default:
          console.warn(`Unknown signaling data type: ${data.type}`);
      }
    } catch (error) {
      console.error('Error handling signaling data:', error);
    }
  }
  endCall() {
    // Logic to end the WebRTC call
    this.localStream.next(null);
    this.remoteStream.next(null);
    // Additional cleanup logic if necessary
  }
}
