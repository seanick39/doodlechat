export type User = {
  id: string;
  name: string
}

// response message received from backend
export type Message = {
  id: string;
  message: string;
  user: User;
  created_at: string;
}

// request message to send while saving a new message
export type MessageRequest = {
  message: string;
  user_id: string;
}
