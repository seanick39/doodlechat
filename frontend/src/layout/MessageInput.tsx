import React, {useState} from "react";
import {Message, User} from "../types";
import {sendMessage} from "../service";

type Props = {
  user: User | null;
  onMessageSend(message: Message): void;
}

export default function MessageInput(props: Props): React.JSX.Element {
  const [messageString, setMessageString] = useState("");
  const [sendAttempted, setSendAttempted] = useState(false);
  
  const onMessageSend = (e: React.MouseEvent) => {
    e.preventDefault();
    setSendAttempted(true);
    if (messageString.length > 0 && !!props.user) {
      sendMessage({user_id: props.user.id, message: messageString})
        .then(res => {
          props.onMessageSend(res);
          setMessageString("");
          setSendAttempted(false);
        })
    }
  }
  
  const onInputChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSendAttempted(false);
    setMessageString(e.target.value);
  }
  
  const inputClassName = sendAttempted && messageString.length === 0 ? "invalid" : "";
  
  return (
    <div id="message-input-box">
      <input value={messageString} onChange={onInputChanged} placeholder="Message" className={inputClassName}/>
      <button onClick={onMessageSend}>Send</button>
    </div>
  )
}
