
const TWEETAPP_REST_API_URL='http://localhost:8090/api/v1.0/tweets';

class UserService {

    async validateLogin(userCredentials){
        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(userCredentials),
        };
        const fetchResponse = await fetch(TWEETAPP_REST_API_URL + '/login', requestOptions);
       console.log("fetchResponse: "+fetchResponse);
        return fetchResponse;
    }

    async registerUser(registerCredentials){
        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(registerCredentials),
        };
        const fetchResponse = await fetch(TWEETAPP_REST_API_URL + '/register', requestOptions);
       // console.log("fetchResponse: "+fetchResponse);
        return fetchResponse;
    }

    async forgotPassword(NewPassword,email){
        const requestOptions = {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(NewPassword,email),
        };
        const fetchResponse = await fetch(TWEETAPP_REST_API_URL +'/'+email+'/forgot', requestOptions);
        //console.log("fetchResponse: "+fetchResponse);
        return fetchResponse;
    }
    async createTweet(tweetData,email){
        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(tweetData),
        };
        const fetchResponse = await fetch(TWEETAPP_REST_API_URL +'/'+email+'/add', requestOptions);
       // console.log("fetchResponse: "+fetchResponse);
        return fetchResponse;
    }
    async getAllUsers(email) {
        console.log("tweetservice: " + email);
        const requestOptions = {
          method: "GET",
          headers: { "Content-Type": "application/json" },
        };
        const fetchResponse = await fetch(
          TWEETAPP_REST_API_URL + "/users/all",
          requestOptions
        );
        console.log("fetchResponse: " + fetchResponse);
        return fetchResponse;
      }
}


export default new UserService();