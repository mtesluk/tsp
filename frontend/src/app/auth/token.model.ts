export class Token {
  constructor(
    public token: string,
    public tokenExpirationDate: Date,
    public id: number,
  ) {
  }
}
