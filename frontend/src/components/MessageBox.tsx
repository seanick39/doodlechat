import type React from "react";
import type {Message} from "../types";

type Props = {
  message: Message;
}

export default function MessageBox(props: Props): React.JSX.Element {
  const {message} = props;
  
  return (
    <div>
      <span>{message.user.name}</span>
      <span>{message.created_at}</span>
      <span>{message.message}</span>
    </div>
  )
}
