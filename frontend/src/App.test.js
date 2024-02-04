import {render, screen} from "@testing-library/react";

import App from "./App";

window.scrollTo = jest.fn();

test("renders text", () => {
  render(<App />);
  const doodleText = screen.getByText(/^Doodle Chat$/);
  const inputElement = screen.getByTestId("message-input");
  const messageSendButton = screen.getByRole("button");

  expect(doodleText).toBeInTheDocument();
  expect(inputElement).toBeInTheDocument();
  expect(messageSendButton).toBeInTheDocument();
});
