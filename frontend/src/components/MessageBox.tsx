import type React from "react";
import type {Message, User} from "../types";

type Props = {
  message: Message;
  user: User | null;
}

export default function MessageBox(props: Props): React.JSX.Element {
  const {message} = props;
  
  const isDemoUser = !!props.user && props.user.id === message.user.id;
  const boxClassName = "message-box" + (isDemoUser ? " this-user" : "");
  const userName = isDemoUser ? "You" : message.user.name;
  
  return (
    <div className={boxClassName}>
      <div className="message-meta">
        <div className="message-box-username">{userName}</div>
        <div className="message-box-timestamp">{message.created_at}</div>
      </div>
      <p>{message.message}</p>
    </div>
  )
}
