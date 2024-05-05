package com.tweetapp.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.LoginUser;
import com.tweetapp.dto.NewPassword;
import com.tweetapp.entity.UserEntity;
import com.tweetapp.exception.UserAlreadyExistException;
import com.tweetapp.model.JwtRequest;
import com.tweetapp.model.JwtResponse;
import com.tweetapp.service.TweetAppServiceImpl;
import com.tweetapp.utility.JWTUtility;

import io.swagger.annotations.ApiOperation;
@CrossOrigin("*")
@RestController
public class UserController {
	
	@Autowired
	TweetAppServiceImpl tweetAppService;
	
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TweetAppServiceImpl userService;

    @GetMapping("/")
    public String home() {
        return "Welcome to Daily Code Buffer!!";
    }

    @PostMapping("/api/v1.0/tweets/login")
    public ResponseEntity<?>authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
        	return new ResponseEntity<>(false,HttpStatus.OK);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return new ResponseEntity<>(new JwtResponse(token),HttpStatus.OK);
       
    }
		
	
	///
	
	
	
		@ApiOperation("Register user")
	@PostMapping("/api/v1.0/tweets/register")
	public boolean registerTweetApp(@RequestBody UserEntity tweetApp) {
		String email = tweetApp.getEmail();
		String firstName = tweetApp.getFirstName();
		String lastName = tweetApp.getLastName(); 
		String gender = tweetApp.getGender();
		LocalDate date = tweetApp.getDate();
		String password = tweetApp.getPassword();
		try {
			tweetAppService.storeData(email, lastName, gender, date, firstName, password);
			System.out.println("Registered");
			return true;
		} catch (UserAlreadyExistException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	/*@ApiOperation("Login user")
	@PostMapping("OLDLOGIN")
	public boolean loginTweetApp(@RequestBody LoginUser loginUser) {
		String email = loginUser.getEmail();
		String password = loginUser.getPassword();
		boolean status = tweetAppService.checkCredentials(email, password);
		if (status)
			System.out.println("LoggedIn");
		else
			System.out.println("Invalid Credenitals");
		return status;
	}*/

	@ApiOperation("Forgot password")
	@PutMapping("/api/v1.0/tweets/forgot")
	public boolean forgetPassword(@RequestBody NewPassword newPassword) {
	 return tweetAppService.forgotPassword( newPassword.getEmail(),newPassword.getNewPassword(),newPassword.getDate(),newPassword.getFirstName());	
	}

}
