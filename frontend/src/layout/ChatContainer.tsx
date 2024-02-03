import type {Message} from "../types";

import React, {useEffect, useState} from "react";
import MessagePane from "./MessagePane";
import MessageInput from "./MessageInput";
import {getMessages} from "../service";

interface Props {
}

export default function ChatContainer(props: Props): React.JSX.Element {
  const [messages, setMessages] = useState<Message[]>([]);
  
  useEffect(() => {
    getMessages()
      .then(setMessages)
      .catch(console.error)
  }, [])
  
  return (
    <>
      <MessagePane messages={messages}/>
      <MessageInput/>
    </>
  )
}