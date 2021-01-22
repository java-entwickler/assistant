import {Client} from "./client";
import {Payment} from "./payment";

export class Job {
  jobId: number;
  title: string;
  client: Client;
  payment: Payment;
  completed: boolean;
  paid: boolean;
}
