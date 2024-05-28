const WebSocket = require('ws');

const ws = new WebSocket.Server({ port: 8080 });

ws.on('message', async (message) => {
  try {
    const data = JSON.parse(message);
    console.log('Message received:', data);
    if (!data.type) {
      throw new Error('Message missing type');
    }

    switch (data.type) {
      case 'join_call':
        ws.send(JSON.stringify({ type: 'ready_to_join' }));
        break;
      default:
        wss.clients.forEach(client => {
          if (client !== ws && client.readyState === WebSocket.OPEN) {
            client.send(message);
          }
        });
        break;
    }
  } catch (error) {
    console.error('Failed to parse message:', message, error);
    ws.send(JSON.stringify({ type: 'error', message: 'Invalid message format' }));
  }
});

console.log('WebSocket signaling server is running on ws://localhost:8080');
