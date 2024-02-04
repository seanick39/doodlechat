import type {Message, MessageRequest, User} from "./types";

import {URL_DEMO_USER, URL_MESSAGES} from "./constants";

const requestHeaders = {
  "Content-Type": "application/json",
  "Accept": "application/json",
}

/** Get messages API call.
 *  Optional param afterMessageId to get messages after this id.
 *  Use-case: to poll for new messages, and add those new messages to state of older messages. */
export function getMessages(afterMessageId?: string): Promise<Message[]> {
  let url = `${URL_MESSAGES}`;
  if (!!afterMessageId?.length) {
    url += "/?afterMessageId=" + afterMessageId;
  }
  return fetch(url, {headers: requestHeaders}).then(res => res.json());
}

/** Get demo user (this user) which is saved to database as "DoodleUser" */
export function getDemoUser(): Promise<User> {
  return fetch(URL_DEMO_USER, {headers: requestHeaders}).then(res => res.json());
}

export function sendMessage(message: MessageRequest): Promise<Message> {
  return fetch(URL_MESSAGES, {method: "POST", headers: requestHeaders, body: JSON.stringify(message)})
    .then(res => res.json());
}