# VideoRental
Application about a Videoclub internal Management

API information

WelcomePage:	 		  **GET**  http://localhost:8080/

alive: 	         		  **GET**  http://localhost:8080/management/welcome

initDataLoad:	 		  **GET**  http://localhost:8080/initDataLoad/

getCustomerById: 		  **GET**  http://localhost:8080/customers/getCustomer  		Params: [id] 

getAllCustomers: 		  **GET**  http://localhost:8080/customers/getAllCustomers

insertCustomer:  		  **POST** http://localhost:8080/customers/insertCustomer 	Params: [name,surname]

getCustomerTransactions:  **GET**  http://localhost:8080/getCustomerTransactions			Params: [customerId]

getAllMovies:			  **GET**  http://localhost:8080/getAllMovies

insertCustomerTransaction:**POST** http://localhost:8080/insertCustomerTransaction			Params: [customerId,movieId,nDays,nExtraDays]

How tu run the application: You just have to install the war file included in a tomcat server.
This application need to call the method "initDataLoad" to set the data and the database to make de application run properly, 
one SqlLite Database will be created in the root of your computer automatically,
so the first thing you have to when the application is deployed is execute this endpoint.
Then in the welcome page you are gonna be able to manage all the functions of the API with an UI that simulates a VideoClub.
You will have one table with all your customers, and when you click on each one, one table with the customers transactions will appear, there you can
check allf the movies that the user has rent, and the prices, and the glossary of the prices with details of delay time and the price charged.
