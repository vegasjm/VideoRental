# VideoRental
Application about a Videoclub internal Management

How to run the application: 

Software Requirements: Maven4, Git2, JDK8
 
You just have to clone the project from My GitHub repository,
running this Command Line in the folder you decide:

**git clone git://github.com/vegasjm/VideoRental**

Then you need to go inside the folder which reside the file pom.xml and run the following command line:

**mvn spring-boot:run**

Then open the local url:

**localhost:8080**

Here you will have a UI to test all the features of the application.
You also can interact with the API’s resources using SwaggerUI in the URL:

**http://localhost:8080/swagger-ui.html**

This application needs to call the method "initDataLoad" to set the data and the database to make the application run properly, 
one SqlLite Database will be created in the root of the USER HOME folder automatically, You can change the place where you want to store the
database changing the path of the DataSource Bean in the File: **PropertiesConfigurer.java**,
so the first thing you have to do when the application is deployed is execute this endpoint, In the UI that there is a
button for this purpouse called "Restart Database".

Then in the welcome page you are gonna be able to manage all the functions of the API with an UI that simulates a VideoClub.
You will have one table with all your customers, and when you click on each one, one table with the customers transactions will appear, there you can
check allf the movies that the user has rent, and the prices, and the glossary of the prices with details of delay time and the price charged.

API information

WelcomePage:	 		 
**GET**  http://localhost:8080/

alive: 	         		  
**GET**  http://localhost:8080/api/management/welcome

getCustomerTransactions:  
**GET**  http://localhost:8080/api/management/getCustomerTransactions   Params: [customerId]

getAllMovies:			  
**GET**  http://localhost:8080/api/management/getAllMovies

insertCustomerTransaction:    
**POST** http://localhost:8080/api/management/insertCustomerTransaction Params: [customerId,movieId,nDays,nExtraDays]

priceSimulation:   
**POST** http://localhost:8080/api/management/priceSimulation   Params: [movieId,nDays,nExtraDays]

initDataLoad:	 		  
**GET**  http://localhost:8080/initDataLoad/

getCustomerById: 		  
**GET**  http://localhost:8080/api/customers/getCustomer    Params: [id] 

getAllCustomers: 		  
**GET**  http://localhost:8080/api/customers/getAllCustomers

insertCustomer:  		  
**POST** http://localhost:8080/api/customers/insertCustomer Params: [name,surname]
