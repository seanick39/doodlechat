import type {User} from "./types";

import React, {useEffect, useState} from "react";
import ChatContainer from "./layout/ChatContainer";
import Header from "./layout/Header";
import {getDemoUser} from "./service";

import "./App.css";

function App(): React.JSX.Element {
  const [user, setUser] = useState<User | null>(null);
  
  useEffect(() => {
    getDemoUser().then(setUser).catch(console.error);
  }, [])
  
  return (
    <div id="app">
      <div id="app-container">
        <Header user={user}/>
        <ChatContainer user={user}/>
      </div>
    </div>
  );
}

export default App;
