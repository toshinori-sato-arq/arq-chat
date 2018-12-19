# ARQ-Chat
サーバサイドはWebAPIのみを実装し、フロントエンド(HTML,CSS,JavaScript,画像)とは分離して開発することを目指します　　

## サーバサイド
「front」ディレクトリ以外はすべてサーバサイドのコードになります

### 言語
[Scala 2.12.6](https://www.scala-lang.org/)  
Javaに置き換えたいと考えています

### Webフレームワーク
[Play Framework 2.6.18](https://www.playframework.com/)  
Javaへの移行に伴いSpring Frameworkに移行しようと考えています

### DB
今のところ使用していない

### 動作環境
IBM Bluemix
TODO:
※詳細は後程記述

### 起動方法
[sbt](https://dwango.github.io/scala_text/sbt-install.html)をインストールして次のコマンドをプロジェクトディレクトリ直下でたたく
```
$ sbt run
```

次のようなメッセージが出たら「http://localhost:9000/」へアクセス
```
--- (Running the application, auto-reloading is enabled) ---

[info] p.c.s.AkkaHttpServer - Listening for HTTP on /0:0:0:0:0:0:0:0:9000

(Server started, use Enter to stop and go back to the console...)
```

ARQ Chatのログインページが表示されば成功です

## フロントエンド
frontディレクトリにソースファイルを配置  

### 使用技術
* [React.js](https://reactjs.org/)
* [Axios](https://github.com/axios/axios)：ajaxライブラリ
Vue.jsへ置き換えたいと考えています。

### ビルド方法
[Node.js](https://nodejs.org/ja/)をインストールし、front次のコマンドをたたく
```
$ npm ci
$ npm run build
```
※npm ciについてはソースファイルクローン時の一度でほぼ大丈夫です。