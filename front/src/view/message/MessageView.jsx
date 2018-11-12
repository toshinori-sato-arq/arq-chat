import React from 'react';
import dateFormat from 'date-fns/format'

export default function Message(props) {
  const message = props.message;
  const user = props.user || { name: "", email: "" };
  const imgSrc = ((userId) => {
    const a = userId ? userId[0] : 't';
    const c = a === 'a' ? 3 : a === 'b' ? 2 : a === 'c' ? 4 : 1;
    const ext = c === 4 ? "jpg" : "PNG"
    return `/img/kao${c}.${ext}`
  })
  return (
    <article className="media">
      <figure className="media-left">
        <p className="image is-64x64">
          <img
            src={imgSrc(user.email)}
            alt={user.name}
          />
        </p>
      </figure>
      <div className="media-content">
        <div className="content">
          <p>
            <strong>{user.name}</strong> <small>{dateFormat(new Date(message.createAt), "YYYY年M月D日 HH:mm")}</small>
            <br />
            {message.text}
          </p>
        </div>
      </div>
    </article>
  );
}

