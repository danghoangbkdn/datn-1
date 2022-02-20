export class Post {
  id: number;
  user_id: number;
  username: string;
  post_title: string;
  post_description: string;
  post_content: string;
  status: string;
  created: Date;
  updated: Date;
  images: [];
  likes: number;
  comments: number;
}

export interface IPostListData {
  totalRecords: number;
  Query: string;
  Data: Array<IPostData>;
}

export interface IPostData {
  from: string;
  to: string;
  amount: number;
}
