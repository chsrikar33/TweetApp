const TWEETAPP_REST_API_URL = "http://localhost:8090/api/v1.0/tweets";

class TweetService {
  async getMyTweets(email) {
    console.log("tweetservice: " + email);
    const requestOptions = {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    };
    const fetchResponse = await fetch(
      TWEETAPP_REST_API_URL + "/" + email,
      requestOptions
    );
    console.log("fetchResponse: " + fetchResponse);
    return fetchResponse;
  }
  async getAllTweets() {
    const requestOptions = {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    };
    const fetchResponse = await fetch(
      TWEETAPP_REST_API_URL + "/all",
      requestOptions
    );
    console.log("fetchResponse: " + fetchResponse);
    return fetchResponse;
  }
  async addAReply(id, email, reply) {
    console.log("id: " + id);
    console.log("email: " + email);
    console.log("reply: " + reply);
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        email: email,
        reply: reply,
        replyFrom: email,
      }),
    };
    const fetchResponse = await fetch(
      TWEETAPP_REST_API_URL + "/" + email + "/reply/" + id,
      requestOptions
    );
    console.log("fetchResponse: " + fetchResponse);
    return fetchResponse;
  }
  async updateTweet(id, email, updatedTweet) {
    console.log("id: " + id);
    console.log("email: " + email);
    console.log("updatedTweet: " + updatedTweet);
    const requestOptions = {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        tweet: updatedTweet,
      }),
    };
    const fetchResponse = await fetch(
      TWEETAPP_REST_API_URL + "/" + email + "/update/"+ id,
      requestOptions
    );
    console.log("fetchResponse: " + fetchResponse);
    return fetchResponse;
  }
  async deleteTweet(id, email) {
    console.log("id: " + id);
    console.log("email: " + email);
    const requestOptions = {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({}),
    };
    const fetchResponse = await fetch(
      TWEETAPP_REST_API_URL + "/" + email + "/delete/" + id,
      requestOptions
    );
    console.log("fetchResponse: " + fetchResponse);
    return fetchResponse;
  }
}
export default new TweetService();
