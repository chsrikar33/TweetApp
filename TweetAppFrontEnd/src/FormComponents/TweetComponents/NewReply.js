import { useRef } from "react";
import TweetService from "../../Service/TweetService";

const NewReply = (props) => {
  const SUCCESS = true;
  const replyRef = useRef("");

  async function replyHandler(event) {
    event.preventDefault();
    const replyText = replyRef.current.value;
    console.log(replyText);
    const fetchResponse = await TweetService.addAReply(
      props.id,
      props.email,
      replyText
    );
    const responseData = await fetchResponse.json();
    if (SUCCESS === responseData) {
      
      alert("Reply added successfully");
      props.doRefresh();
    }
    replyCancelHandler();
  }

  function replyCancelHandler() {
    props.onCancelClick();
  }

  return (
    <div>
      <form onSubmit={replyHandler}>
        <textarea
          rows={3}
          cols={45}
          maxLength="128"
          id="reply"
          name="reply"
          ref={replyRef}
          required
        ></textarea>
        <br />
        <button onClick={replyCancelHandler}>Cancel</button>
        <button>Post Reply</button>
      </form>
    </div>
  );
};

export default NewReply;
