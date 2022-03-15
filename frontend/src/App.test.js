import { render, screen } from "@testing-library/react";

import App from "./App";

test("renders text", () => {
  render(<App />);
  const doodleText = screen.getByText(/^Doodle Chat$/);

  expect(doodleText).toBeInTheDocument();
});
