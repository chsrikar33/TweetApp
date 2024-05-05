import Tweet from "./Tweet";


const Tweets = (props) => {
  function refresh() {
     props.doRefresh();
  
  }
  return (
    <div>
      {props.tweetList.map((tweet) => (
        <Tweet
          key={tweet.id}
          id={tweet.id}
          email={tweet.email}
          tweet={tweet.tweet}
          tweetTime={tweet.tweetTime}
          reply={tweet.reply}
          globalUserName={props.globalUserName}
          doRefresh={refresh}
        />
      ))}
       
    </div>
  );
};

export default Tweets;
