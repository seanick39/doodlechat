export type User = {
  id: string;
  name: string
}

export type Message = {
  id: string;
  message: string;
  user: User;
  created_at: string;
}

export type MessageRequest = {
  message: string;
  user_id: string;
}
