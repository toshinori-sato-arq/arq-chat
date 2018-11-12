import UrlResolver from '../util/UrlResolver'

export class _Urls {
  constructor(resolver) {
    this.signup = () => resolver.resolve("/api/v1/signup")
    this.login = () => resolver.resolve('/api/v1/login');
    this.logout = () => resolver.resolve('/api/v1/logout');
    this.rooms = () => resolver.resolve('/api/v1/rooms');
    this.messages = (roomId) => resolver.resolve(`/api/v1/rooms/${roomId}/messages`);
    this.users = (roomId) => resolver.resolve(`/api/v1/rooms/${roomId}/users`);
  }
}

export default new _Urls(UrlResolver)