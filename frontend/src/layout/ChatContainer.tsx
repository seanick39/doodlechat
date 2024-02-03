import type React from "react";
import MessagePane from "./MessagePane";
import MessageInput from "./MessageInput";

export default function ChatContainer(): React.JSX.Element {
  return (
    <>
      <MessagePane/>
      <MessageInput/>
    </>
  )
}