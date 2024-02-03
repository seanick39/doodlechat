import React, {useState} from "react";
import {Message, User} from "../types";
import {sendMessage} from "../service";

type Props = {
  user: User | null;
  onMessageSend(message: Message): void;
}

export default function MessageInput(props: Props): React.JSX.Element {
  const [messageString, setMessageString] = useState("");
  
  const onMessageSend = (e: React.MouseEvent) => {
    e.preventDefault();
    if (messageString.length > 0 && !!props.user) {
      sendMessage({user_id: props.user.id, message: messageString})
        .then(res => {
          props.onMessageSend(res);
          setMessageString("");
        })
    }
  }
  
  return (
    <>
      <input value={messageString} onChange={e => setMessageString(e.target.value)} placeholder="Message"/>
      <button onClick={onMessageSend}>Send</button>
    </>
  )
}
