import './Tweet.css';

const Reply = (props) => {
  const replies = props.reply;
  if (replies.length === 0) {
    return;
  }
  const content = <h5>Replies</h5>;
  const replyContent = replies.map((reply) => (
    <div key={reply.replyId}>
      <label className="tweet-control2">@{reply.replyFrom}</label>
      <br />
      <label className="tweet-control3">{reply.reply}</label>
      <br />
    </div>
  ));
  return <div className="tweet">{content}{replyContent}</div>;
};

export default Reply;
