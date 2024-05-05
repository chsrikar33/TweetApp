import React from "react";
import {BrowserRouter,Routes,Route} from "react-router-dom";
import LoginForm from "./FormComponents/UserComponents/LoginForm";
import Register from "./FormComponents/UserComponents/Register";
import ForgotPassword from "./FormComponents/UserComponents/ForgotPassword";
import TweetHome from "./FormComponents/TweetComponents/TweetHome"
import './App.css';

const App = () => {

  return (

    <BrowserRouter>
     <h1>TweetApp</h1> 
      <Routes>
      <Route path='/login' element={<LoginForm/>}/>
      <Route path='/register' element={<Register/>}/>
      <Route path='/forgotPassword' element={<ForgotPassword/>} />
      <Route path='/tweetHome' element={<TweetHome/>}/>
      <Route path='/' element={<LoginForm/>}/>
      </Routes>
  </BrowserRouter>
  );
};

export default App;
