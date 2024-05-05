import React,{useState,useCallback,useEffect} from 'react';
import UserService from "../../Service/UserService";
import TweetService from "../../Service/TweetService"
import Tweets from "./Tweets";
import ViewAllUsers from './ViewAllUsers';
import { useSearchParams,Link} from "react-router-dom";

const Home = () => {
  const[tweet ,setTweet]=useState("");
  const[newTweet ,setNewTweet]=useState("");
  const [tweets, setTweets] = useState([]);
  const [allTweets, setAllTweets] = useState([]);
  const [users, setUsers] = useState([]);
  const[registerStatus ,setRegisterStatus]=useState("");
  const[viewAllUsers,setViewAllUsers]=useState(false);
  const[viewAllTweets,setViewAllTweets]=useState(false);
  const[reload,setReload]=useState(false);
  const[searchparams]=useSearchParams();
  const email=searchparams.get("id");
  const tweetHandler = (event) => {
    setTweet(event.target.value);
  };
  function refresh() {
    setReload(!reload);
    
  }
  const getTweets = useCallback(async () => {
    const fetchResponse = await TweetService.getMyTweets(email);
   const responseData = await fetchResponse.json();
    console.log(responseData);
    const tweetList = responseData.tweetList;
    if(tweetList===null)
    {
      return <p>No tweets</p>
    }
    setTweets(tweetList);
  }, [email,newTweet]);

  useEffect(() => {
    getTweets();
  }, [getTweets,reload]);
  //
  const getAllTweets = useCallback(async () => {
    const fetchResponse = await TweetService.getAllTweets();
   const responseData = await fetchResponse.json();
    console.log(responseData);
    const tweetList = responseData.tweetList;
    if(tweetList===null)
    {
      return <p>No tweets</p>
    }
    setAllTweets(tweetList);
  },[]);

  useEffect(() => {
    getAllTweets();
  }, [getAllTweets,reload]);

  //
  const getUsers = useCallback(async () => {
   const fetchResponse = await UserService.getAllUsers(email);
   const responseData = await fetchResponse.json();
    console.log(responseData);
    const userList = responseData.userList;
    setUsers(userList);
  }, [email]);

  useEffect(() => {
    getUsers();
  }, [getUsers]);
 
  async function createTweetHandler(event){
    event.preventDefault();
    
    const tweetData={
        tweet:tweet
    };
    const fetchResponse = await UserService.createTweet(tweetData,email);
    const responseDataTweet = await fetchResponse.json();
    console.log(responseDataTweet);
    if (responseDataTweet !== null) {
        console.log("ResponseDataTweet: " + responseDataTweet);
        setNewTweet(responseDataTweet);
        if (responseDataTweet>0) {
          setRegisterStatus("TweetPosted");
        }
      }

    }
    function viewAllUsersHandler(){
      setViewAllUsers(!viewAllUsers);
    }
    function viewAllTweetsHandler(){
      setViewAllTweets(!viewAllTweets);
    }
  return (
    <div>
      <form onSubmit={createTweetHandler}>
      
        <label htmlFor="tweet">Tweet</label>
        <br />
        <input
          name="tweet"
          id="tweet"
          type="text"
          onChange={tweetHandler}
          value={tweet}
        ></input>
      <button type='submit'>Create Tweet</button>
      </form>
      <br />
      <br />
      <button onClick={viewAllTweetsHandler}>viewAllTweets</button>
      <br />
      <br />
      <button onClick={viewAllUsersHandler} >viewAllUsers</button>
      <button><Link to="/">Logout</Link></button>
      
      <Tweets tweetList={tweets} globalUserName={email} doRefresh={refresh}/>
      {viewAllUsers&&<ViewAllUsers usersList={users}></ViewAllUsers>}
      {viewAllTweets&&<Tweets tweetList={allTweets} globalUserName={email}/>}
      <p>{registerStatus}</p>
    </div>
  )
}

export default Home