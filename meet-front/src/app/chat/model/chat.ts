import {Message} from "./message";
import {User} from "../../user/model/user";

export class Chat {
  id!: number;
  name!: string;
  members!: User[];
  messages!: Message[];
}
