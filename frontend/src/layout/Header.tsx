import type React from "react";
import type {User} from "../types";

// @ts-ignore
import {ReactComponent as UserIconSVG} from "../user.svg";

type Props = {
  user: User | null;
}

export default function Header(props: Props): React.JSX.Element {
  return (
    <header>
      <div id="header-container">
        <div id="header-logo"><img src="/favicon.ico" alt="logo"/></div>
        <div id="header-brand">Doodle Chat</div>
        <div id="header-user">
          <UserIconSVG/>
          <div id="header-username">{props.user?.name}</div>
        </div>
      </div>
    </header>
  )
}
