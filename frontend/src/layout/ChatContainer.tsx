import type {Message, User} from "../types";

import React, {useEffect, useState} from "react";
import MessagePane from "./MessagePane";
import MessageInput from "./MessageInput";
import {getMessages} from "../service";
import {usePolling} from "../utils";

type Props = {
  user: User | null
}

export default function ChatContainer(props: Props): React.JSX.Element {
  const [messages, setMessages] = useState<Message[]>([]);
  
  // load initial messages on first mount
  useEffect(() => {
    getMessages()
      .then(setMessages)
      .catch(console.error)
  }, [])
  
  // poll at an interval of 5 seconds for new messages with the last message's uuid
  usePolling(() => {
    getMessages(messages.at(-1)?.id ?? "")
      .then(newMessages => setMessages(oldMessages => oldMessages.concat(newMessages)))
      .catch(console.error)
  }, 5000);
  
  const onMessageSend = (message: Message) => {
    setMessages(oldMessages => oldMessages.concat(message));
  }
  
  return (
    <>
      <MessagePane messages={messages} user={props.user}/>
      <MessageInput user={props.user} onMessageSend={onMessageSend}/>
    </>
  )
}