import type React from "react";
import type {User} from "../types";

type Props = {
  user: User | null;
}

export default function Header(props: Props): React.JSX.Element {
  return (
    <header>
      <div id="header-username">{props.user?.name}</div>
    </header>
  )
}
