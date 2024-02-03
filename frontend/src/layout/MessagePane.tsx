import type React from "react";
import type {Message, User} from "../types";

import MessageBox from "../components/MessageBox";

type Props = {
  messages: Message[];
  user: User | null;
}

export default function MessagePane(props: Props): React.JSX.Element {
  
  const reversedMessages = [...props.messages].reverse();
  
  return (
    <div id="message-pane">
      {reversedMessages.map((message, i) => <MessageBox message={message} user={props.user} key={i} />)}
      <div id="message-pane-anchor"/>
    </div>
  )
}
