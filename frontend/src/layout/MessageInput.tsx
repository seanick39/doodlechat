import React, {useCallback, useEffect, useState} from "react";
import {Message, User} from "../types";
import {sendMessage} from "../service";

type Props = {
  user: User | null;
  onMessageSend(message: Message): void;
}

export default function MessageInput(props: Props): React.JSX.Element {
  const [messageString, setMessageString] = useState("");
  const [sendAttempted, setSendAttempted] = useState(false);
  
  const onMessageSend = useCallback((e?: React.MouseEvent) => {
    if (e)  {
      e.preventDefault();
    }
    setSendAttempted(true);
    if (messageString.length > 0 && !!props.user) {
      sendMessage({user_id: props.user.id, message: messageString})
        .then(res => {
          props.onMessageSend(res);
          setMessageString("");
          setSendAttempted(false);
        })
    }
  }, [props, messageString]);
  
  const keyDownListener = useCallback((k: any) => {
    if (k.code === "Enter" || k.code === "Return") {
      onMessageSend();
    }
  }, [onMessageSend]);
  
  useEffect(() => {
    document.addEventListener("keydown", keyDownListener)
    return () => document.removeEventListener("keydown", keyDownListener);
  }, [keyDownListener]);
  
  const onInputChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSendAttempted(false);
    setMessageString(e.target.value);
  }
  
  const inputClassName = sendAttempted && messageString.length === 0 ? "invalid" : "";
  
  return (
    <div id="message-input-box">
      <input data-testid="message-input" value={messageString} onChange={onInputChanged} placeholder="Message" className={inputClassName}/>
      <button role="button" onClick={onMessageSend}>Send</button>
    </div>
  )
}
