import Cookies from 'js-cookie'
import Constant from './Constant'

export class _CookieManager {
  constructor(constant) {
    this.keys = {
      sessionId: constant.cookie.sessionId.key,
    }
  }

  loadSessionId() {
    return Cookies.get(this.keys.sessionId)
  }

  saveSessionId(sessionId) {
    Cookies.set(this.keys.sessionId, sessionId)
  }

  removeSessionId() {
    Cookies.remove(this.keys.sessionId)
  }
}

export default new _CookieManager(Constant)