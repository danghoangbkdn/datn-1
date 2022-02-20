export class User {
  id: number;
  email: string;
  username: string;
  password: string;
  active: number;
  detail: string;
  created: Date;
  updated: Date;
  followers: string;
  n_followers: number;
  token: string;
}

export interface IUserListData {
  totalRecords: number;
  Query: string;
  Data: Array<IUserData>;
}

export interface IUserData {
  from: string;
  to: string;
  amount: number;
}
