import React ,{useState}from 'react';
import { Link } from "react-router-dom";
import UserService from "../../Service/UserService";
import "./Register.css";

const Register = () => {

    const[email ,setEmail]=useState("");
    const[lastName ,setLastName]=useState("");
    const[gender ,setGender]=useState("");
    const[date ,setDate]=useState("");
    const[firstName ,setFirstName]=useState("");
    const[password ,setPassword]=useState("");
    const[registerStatus ,setRegisterStatus]=useState("");

    const emailHandler = (event) => {
        setEmail(event.target.value);
      };
    const lastNameHandler = (event) => {
        setLastName(event.target.value);
      };
    const genderHandler = (event) => {
        setGender(event.target.value);
      };
    const dateHandler = (event) => {
        setDate(event.target.value);
      };
    const firstNameHandler = (event) => {
        setFirstName(event.target.value);
      };
    const passwordHandler = (event) => {
        setPassword(event.target.value);
      };
    async function registerHandler(){

        const registerCredintals={
            email:email,
            lastName:lastName,
            gender:gender,
            date:date,
            firstName:firstName,
            password:password
    
        };
        const fetchResponse = await UserService.registerUser(registerCredintals);
        const responseData = await fetchResponse.json();
        console.log(responseData);
        if (responseData !== null) {
            //let loginResponse = responseData.response;
           // console.log("LoginResponse: " + loginResponse);
            console.log("ResponseData: " + responseData);
            if (true === responseData) {
              //props.setValidLogin(userName);
              setRegisterStatus("Registered");
            }
            if (false ===responseData) {
              setRegisterStatus("UserAlreadyRegistered");
            }
          }



    }
  return (
    <div className="form-control">
        <form onSubmit={registerHandler}>
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
        <label htmlFor="lastName">LastName</label>
        <br />
        <input
          name="lastName"
          id="lastName"
          type="text"
          onChange={lastNameHandler}
          value={lastName}
        ></input>
        <br />
        <label htmlFor="gender">Gender</label>
        <br />
        <input
          name="gender"
          id="gender"
          type="text"
          onChange={genderHandler}
          value={gender}
        ></input>
        <br />
        <label htmlFor="date">Date</label>
        <br/>
        <input
          name="date"
          id="date"
          type="date"
          onChange={dateHandler}
          value={date}
        ></input>
        <br />
        <label htmlFor="text">FirstName</label>
        <br />
        <input
          name="firstName"
          id="firstName"
          type="text"
          onChange={firstNameHandler}
          value={firstName}
        ></input>
        <br />
        <label htmlFor="password">Password</label>
        <br />
        <input
          name="password"
          id="password"
          type="password"
          onChange={passwordHandler}
          value={password}
        ></input>
        <br />
            <button type='submit'> SignUp</button>
        </form>
        <div className="auth-option text-center pt-2">Have an account? <Link className="text-link" to="/login" >Sign in</Link></div>
        <p>{registerStatus}</p>
    </div>
  )
}

export default Register