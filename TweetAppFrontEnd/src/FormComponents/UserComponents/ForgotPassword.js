import React ,{useState}from 'react'
import UserService from "../../Service/UserService";
import "./ForgotPassword.css";
import { Link } from "react-router-dom";
const ForgotPassword = () => {

    const[email ,setEmail]=useState("");
    const[firstName ,setFirstName]=useState("");
    const[newPassword ,setNewPassword]=useState("");
    const[date ,setDate]=useState("");
    const[passwordStatus,setPasswordStatus]=useState("");

    const emailHandler = (event) => {
        setEmail(event.target.value);
      };
      const dateHandler = (event) => {
        setDate(event.target.value);
      };
    const firstNameHandler = (event) => {
        setFirstName(event.target.value);
      };
    const newPasswordHandler = (event) => {
        setNewPassword(event.target.value);
      };
      async function forgotPasswordHandler(){

        const NewPassword={
            date:date,
            firstName:firstName,
            newPassword:newPassword
    
        };
        const fetchResponse = await UserService.forgotPassword(NewPassword,email);
        const responseData = await fetchResponse.json();
        console.log(responseData);
        if (responseData !== null) {
            //let loginResponse = responseData.response;
           // console.log("LoginResponse: " + loginResponse);
            console.log("ResponseData: " + responseData);
            if ("Credentials Matched" === responseData) {
              //props.setValidLogin(userName);
              setPasswordStatus("PasswordChanged");
            }
            if ("Enter Valid Date of Birth and FirstName" ===responseData) {
              setPasswordStatus("Wrong Credintals");
            }
          }


        }

  return (
    <div className='form-control'>
        <form onSubmit={forgotPasswordHandler}>
        <label htmlFor="email">Email</label>
        <br />
        <input
          name="email"
          id="email"
          type="text"
          onChange={emailHandler}
          value={email}
        ></input>
        <br />
        <label htmlFor="newPassword">NewPassword</label>
        <br />
        <input
          name="newPassword"
          id="newPassword"
          type="password"
          onChange={newPasswordHandler}
          value={newPassword}
        ></input>
        <br/>
        <label htmlFor="text">FirstName</label>
        <br />
        <input
          name="firstName"
          id="firstName"
          type="text"
          onChange={firstNameHandler}
          value={firstName}
        ></input>
        <br/>
         <label htmlFor="date">Date</label>
         <br/>
        <input
          name="date"
          id="date"
          type="date"
          onChange={dateHandler}
          value={date}
        ></input>
        <br/>

        <button type='submit'>ForgotPassword</button>
        </form>
       
        <Link to="/login" >Back to Login</Link>
        <p>{passwordStatus}</p>

    </div>
  )
}
export default ForgotPassword;