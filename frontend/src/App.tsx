import "./App.css";
import type React from "react";
import ChatContainer from "./layout/MessagePane";
import Header from "./layout/Header";

function App(): React.JSX.Element {
  return (
    <div id="app">
      <Header/>
      <ChatContainer/>
    </div>
  );
}

export default App;
