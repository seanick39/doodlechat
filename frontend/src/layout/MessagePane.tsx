import React, {useEffect, useRef} from "react";
import type {Message, User} from "../types";

import MessageBox from "../components/MessageBox";

type Props = {
  messages: Message[];
  user: User | null;
}

export default function MessagePane(props: Props): React.JSX.Element {
  
  let ref = useRef<HTMLElement>(null);
  
  // effect to scroll to bottom of message pane on first mount, so we're on the last message
  useEffect(() => {
    if (ref.current) {
      const bottom = ref.current.offsetTop + ref.current.offsetHeight;
      window.scrollTo({ top: bottom });
    }
  }, [])
  
  return (
    // @ts-ignore
    <div id="message-pane" ref={ref}>
      {props.messages.map((message, i) => <MessageBox message={message} user={props.user} key={i} />)}
      <div id="message-pane-anchor"/>
    </div>
  )
}
