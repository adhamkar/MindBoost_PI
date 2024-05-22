import {Component, ViewEncapsulation} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css'],
  encapsulation: ViewEncapsulation.Emulated
})
export class ChatbotComponent {
  constructor(private http: HttpClient) {}

  handleKeyPress(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      this.sendMessage();
    }
  }

  sendMessage() {
    const chatInput = (document.getElementById('chat-input') as HTMLInputElement);
    const chatBody = document.getElementById('chat-body');
    const message = chatInput.value.trim();

    if (!chatBody) {
      console.error('Chat body element not found');
      return;
    }

    if (message) {
      const isScrollbarAtBottom = chatBody.scrollHeight - chatBody.clientHeight <= chatBody.scrollTop + 1;

      const userMessageContainer = document.createElement('div');
      userMessageContainer.className = 'chat-message-container';

      const userMessageIcon = document.createElement('img');
      userMessageIcon.src = 'assets/satisfaction.png';
      userMessageIcon.className='message-icon'
      // userMessageIcon.style.border="border-radius:50%"
      // userMessageIcon.style.width="30px";
      // userMessageIcon.style.height="30px";
      // userMessageIcon.style.margin="0 10px";

      console.log(userMessageIcon.className)
      const userMessage = document.createElement('div');
      userMessage.className = 'chat-message user';
      // userMessageContainer.style.padding="10px";
      // userMessageContainer.style.maxWidth="70%";
      // userMessageContainer.style.border="5px"

      userMessage.textContent = message;

      userMessageContainer.appendChild(userMessage);
      userMessageContainer.appendChild(userMessageIcon);

      chatBody.appendChild(userMessageContainer);

      if (isScrollbarAtBottom) {
        chatBody.scrollTop = chatBody.scrollHeight;
      }

      chatInput.value = '';

      this.http.post('/parse', { value1: message })
        .subscribe((data: any) => {
          const botMessageContainer = document.createElement('div');
          botMessageContainer.className = 'chat-message-container';

          const botMessageIcon = document.createElement('img');
          botMessageIcon.src = 'assets/robot.png';
          botMessageIcon.className = 'message-icon';

          const botMessage = document.createElement('div');
          botMessage.className = 'chat-message bot';
          botMessage.textContent = data.response.join('\n');

          botMessageContainer.appendChild(botMessageIcon);
          botMessageContainer.appendChild(botMessage);

          chatBody.appendChild(botMessageContainer);
          chatBody.scrollTop = chatBody.scrollHeight;
        }, error => {
          console.error('Error:', error);
        });
    }
  }
}
