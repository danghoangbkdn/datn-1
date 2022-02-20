export class FileDTO {
  name: string;
  path: string;

  public constructor(init?: Partial<FileDTO>) {
    return Object.assign(this, init);
  }
}
