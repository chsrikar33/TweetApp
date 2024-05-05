package com.tweetapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tweetapp.exception.UserAlreadyExistException;
import com.tweetapp.service.TweetAppServiceImpl;

@SpringBootApplication
public class TweetAppApplication {

	public static TweetAppServiceImpl tweetAppService;
	static Scanner sc = new Scanner(System.in);
	public static String email = "";
	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(TweetAppApplication.class, args);
		tweetAppService = context.getBean(TweetAppServiceImpl.class);
		System.out.println("Welcome to Tweet App");
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	public static void menu() {
		boolean isLoggedIn = false;

		do {
			int n;
			if (!isLoggedIn) {
				System.out
						.println("Select one option from below options \n 1.Register \n 2.Login \n 3.Forgot Password");

				n = sc.nextInt();
				switch (n) {
				case 1:
					try {
						registerTweetApp();
					} catch (UserAlreadyExistException e) {
						System.out.println(e.getMessage());

					}
					break;
				case 2:
					isLoggedIn = loginTweetApp();
					break;
				case 3:
					forgetPassword();
					break;
				default:
					System.out.println("Enter the correct Option");
				}
			} else {
				System.out.println(
						"Select one option from below options \n 1.Post a Tweet \n 2.View my tweets \n 3.View all Users \n"
								+ " 4.Logout \n 5.View ALl Users Tweets \n 6.Reset Password \n 7.Update Tweet \n 8.Delete Tweet" );
				n = sc.nextInt();
				switch (n) {
				case 1:
					//postATweet();
					break;
				case 2:
					viewTweets();
					break;
				case 3:
					viewAllUsers();
					break;
				case 4:
					isLoggedIn = false;
					break;
				case 5:
					viewAllUsersTweets();
					break;
				case 6:
					resetPassword();
					break;
				case 7:
					updateTweet();
					break;
				case 8:
					//deleteTweet();
					break;
				default:
					System.out.println("Enter the correct Option");
				}

			}
		} while (true);

	}


	public static void registerTweetApp() throws UserAlreadyExistException {
		System.out.println("Enter email");
		email = sc.next();
		boolean isExist = tweetAppService.validateEmail(email);
		if (!isExist) {
			System.out.println("This " + email + " is already use .Please register with other email");
			menu();
		}
		System.out.println("Enter firstName");
		String firstName = sc.next();
		System.out.println("Enter lastName");
		String lastName = sc.next();
		System.out.println("Enter Gender");
		String gender = sc.next();
		System.out.println("Enter Date of Birth in dd-mm-yyyy");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(Locale.ENGLISH);
		LocalDate date;
		try {
			date = LocalDate.parse(sc.next(), dateFormat);
		} catch (Exception e) {
			throw new UserAlreadyExistException("Invalid Date Format");
		}

		System.out.println("Enter password");
		String password = sc.next();
		tweetAppService.storeData(email, lastName, gender, date, firstName, password);
		//tweetAppService.storeDataInTweetPost(email);
		System.out.println("Registered");
	}

	public static boolean loginTweetApp() {
		System.out.println("Enter email");
		email = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		boolean status = tweetAppService.checkCredentials(email, password);
		if (status)
			System.out.println("LoggedIn");
		else
			System.out.println("Invalid Credenitals");
		return status;
	}

	public static void forgetPassword() {
		System.out.println("Enter Email");
		email = sc.next();
		System.out.println("Enter Date");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(Locale.ENGLISH);
		LocalDate date = LocalDate.parse(sc.next(), dateFormat);
		System.out.println("Enter firstName");
		String firstName = sc.next();
		System.out.println("Enter new Password");
		String password = sc.next();
		/*String status = tweetAppService.forgotPassword(email, password,date, firstName);
		System.out.println(status);
		if (status.equals("Credentials Matched")) {
			//System.out.println("Enter new Password");
			//String password = sc.next();
			String resetPassword = tweetAppService.resetPassword(firstName, password);
			System.out.println(resetPassword);
		}*/
	}

	/*public static void postATweet() {
		sc.nextLine();
		System.out.println("Enter the tweet");
		String tweet = sc.nextLine();
		tweetAppService.postATweet(email, tweet);

	}*/

	public static void viewAllUsers() {
		tweetAppService.UsersInTweetApp();
	}

	public static void viewTweets() {
		tweetAppService.viewTweets(email);
	}

	public static void viewAllUsersTweets() {
		tweetAppService.viewAllUsersTweets();

	}

	public static void resetPassword() {

		System.out.println("Enter old Password");
		String oldPassword = sc.next();
		String result = tweetAppService.checkOldPassword(email, oldPassword);
		if (result.equals("matched")) {
			System.out.println("Enter new password");
			String newPassword = sc.next();
			String resetPassword = tweetAppService.resetPassword(email, newPassword);
			System.out.println(resetPassword);
		} else {
			System.out.println(result);

		}
	}

	/*public static void deleteTweet() {
		System.out.println("Enter id");
		long id=sc.nextLong();
		tweetAppService.deleteTweet(id);
		
	}*/

	public static void updateTweet() {
		System.out.println("Enter id");
		long id=sc.nextLong();
		sc.nextLine();
		System.out.println("Enter updated Tweet");
		String tweet=sc.nextLine();
		//tweetAppService.updateTweet(id,tweet);		
	}

}
