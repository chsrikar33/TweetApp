import React, { useState } from "react";
import UserService from "../../Service/UserService";
import "./LoginForm.css";
import { Link,useNavigate,createSearchParams } from "react-router-dom";

const LoginForm = (props) => {
    const navigate = useNavigate();
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  //const [loginResponse, setLoginResponse] = useState("");
  const [error, setError] = useState(false);
  const[isValid,setIsValid]=useState(false);
  const [errorContent, setErrorContent] = useState("");
  

  const userNameHandler = (event) => {
    setUserName(event.target.value);
  };

  const passwordHandler = (event) => {
    setPassword(event.target.value);
  };


  async function loginHandler(event) {
    event.preventDefault();
    console.log(userName);
    console.log(password);
    const userCredentials={
        email:userName,
        password:password

    };
    const fetchResponse = await UserService.validateLogin(userCredentials);
    const responseData = await fetchResponse.json();
    console.log(responseData);
    if (responseData !== null) {
      let loginResponse = responseData.response;
      console.log("LoginResponse: " + loginResponse);
      console.log("ResponseData: " + responseData);
      if (true === responseData) {
        //props.setValidLogin(userName);
        setErrorContent("Login Success");
        setIsValid(true);
        navigate({
            pathname:"/TweetHome",
            search:createSearchParams({
                id:userName
            }).toString()
           
        });

      }
      
      if (false ===responseData) {
        //setError(true);
        setErrorContent("Not valid Credintals");
        setIsValid(false);
      }
    }
  };

  return (

    <div className="form-control">
      <form onSubmit={loginHandler}>
      <div className="email mb-3">
        <label htmlFor="userName">UserName</label>
        <br />
        <input
          name="userName"
          id="userName"
          type="text"
          onChange={userNameHandler}
          value={userName}
        ></input>
      </div>
        <div className="password mb-3">
        <label htmlFor="password">Password</label>
        <br />
        <input
          name="password"
          id="password"
          type="password"
          onChange={passwordHandler}
          value={password}
        ></input>
        </div>
        <div className="col-2">
            <div className="forgot-password text-end">
           <Link to="/ForgotPassword">Forgot password?</Link>
            </div>
        </div> 
        <div className="text-left">
         <button type="submit" className="btn btn-primary w-20 theme-btn mx-auto">Log In</button>
        </div>
        
      </form>
      <p>{errorContent}</p>
       <div className="auth-option text-left pt-2">No Account? <Link className="text-link" to="/Register" >Sign up </Link></div>
    </div>
  );
};

export default LoginForm;
