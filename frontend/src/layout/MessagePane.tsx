import type React from "react";
import type {Message} from "../types";

import MessageBox from "../components/MessageBox";

type Props = {
  messages: Message[];
}

export default function MessagePane(props: Props): React.JSX.Element {
  
  return (
    <div>
      {props.messages.map((message, i) => <MessageBox message={message} key={i} />)}
    </div>
  )
}
