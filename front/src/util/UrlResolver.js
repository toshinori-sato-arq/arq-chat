//const urlPrefix = "http://localhost:9000"
//const urlPrefix = "https://arq-chat.mybluemix.net"
const urlPrefix = ""

export default {
  resolve(url) {
    return `${urlPrefix}${url}`
  }
}