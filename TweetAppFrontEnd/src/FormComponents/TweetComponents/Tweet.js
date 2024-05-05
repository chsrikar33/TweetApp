import { useState } from "react";
import NewReply from "./NewReply";
import Reply from "./Reply";
import Update from "./Update";
import "./Tweet.css";
import TweetService from "../../Service/TweetService";

const Tweet = (props) => {
  const [clickedReply, setClickedReply] = useState(false);
  const [clickedUpdate, setClickedUpdate] = useState(false);

  function clickReplyHandler() {
    setClickedReply(!clickedReply);
  }
  function clickUpdateHandler() {
    setClickedUpdate(!clickedUpdate);
  }
  function refresh() {
    props.doRefresh();
  }
  async function deleteHandler(event) {
    event.preventDefault();
    await TweetService.deleteTweet(
      props.id,
      props.email,
    );
    refresh();
    
  }

  return (
    <div className="tweet">
      <label className="tweet-control2">@{props.email}</label>
      {//<label className="tweet-control1">{props.localDateTime}</label>
      }<br />
      <label className="tweet-control3">{props.tweet}</label>
      <br />
      <button>Like</button>
      <button onClick={clickReplyHandler}>Reply</button>
      <button onClick={clickUpdateHandler}>Update</button>
      <button onClick={deleteHandler}>Delete</button>


      {clickedReply && (
        <NewReply
          onCancelClick={clickReplyHandler}
          id={props.id}
          email={props.email}
          doRefresh={refresh}
        />
      )}
      {clickedUpdate && (
        <Update
        onCancelClick={clickUpdateHandler}
          id={props.id}
          email={props.email}
          doRefresh={refresh}
        />
      )}
    
      <Reply reply={props.reply}/>
     
    </div>
  );
};

export default Tweet;
