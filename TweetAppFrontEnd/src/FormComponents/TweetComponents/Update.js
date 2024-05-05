import { useRef } from "react";
import TweetService from "../../Service/TweetService";

const Update = (props) => {

  const updateRef = useRef("");

  async function updateHandler(event) {
    event.preventDefault();
    const updatedTweet = updateRef.current.value;
    console.log(updatedTweet);
    const fetchResponse = await TweetService.updateTweet(
      props.id,
      props.email,
      updatedTweet
    );
    props.doRefresh();
    updateCancelHandler();
    

  }
  function updateCancelHandler() {
    props.onCancelClick();
  }

  return (
    <div>
      <form onSubmit={updateHandler}>
        <textarea
          rows={3}
          cols={45}
          maxLength="128"
          id="reply"
          name="reply"
          ref={updateRef}
          required
        ></textarea>
        <br />
        <button onClick={updateCancelHandler}>Cancel</button>
        <button>Update</button>
      </form>
    </div>
  )
}

export default Update