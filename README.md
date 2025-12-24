PROJECT OVERVIEW
This project is a simple wallet system built using Java, Spring Boot, and MongoDB.
It allows users to register/login using one single API, check their balance, and transfer money between wallets.
The goal is to simulate a basic digital wallet workflow.

HOW TO SET UP THE PROJECT
This section explains how to install, configure, and run the project on your local machine.

CLONE THE PROJECT
Use this command to download the project into your system: git clone https://github.com/sachi-n8/r1xchan.gitcd r1xchan
This will bring all source code to your local machine.

START MONGODB
The application requires MongoDB to store user wallet data.
Start local MongoDB:mongod
MongoDB is now available at:
mongodb://localhost:27017

CONFIGURE APPLICATION PROPERTIES
This file connects your Spring Boot project with MongoDB.
Location:src/main/resources/application.properties
Add the following:
spring.data.mongodb.uri=mongodb://localhost:27017/miniwallet
spring.data.mongodb.database=miniwallet
server.port=8080
This ensures the server uses MongoDB correctly.


RUN THE SERVER
Run using Maven:mvn spring-boot:run

DATA MODEL
This section explains what data is stored in the database.

Wallet Model:
{
"userId": "string",
"balance": number
}
The wallet keeps track of:
A user’s unique ID
The current balance in their wallet

USer Model:
{
"userId": "abc123",
"firstName": "Sachin",
"lastName": "Kumar",
"email": "sachin@example.com
",
"password": "encrypted_password_here"
}
userId Type: String
Description: This is the unique identifier for the user.
It is automatically stored as the MongoDB document ID.
This value is used to map each user to their wallet.
firstNameType: String
Description: The user’s first name.
lastNameType: String
Description: The user’s last name.
emailType: String
Description: The user’s email address.
This is typically used for login or communication.
passwordType: String
Description: The user’s encrypted password used for authentication.
This should never be stored in plain text.


RELATIONSHIPS BETWEEN DATA
This project uses a simple relationship model.
1 User → Many Wallet
Each user has exactly one wallet.
During a transfer:Sender’s balance is reduced
Receiver’s balance is increased
Both operations must succeed together (atomic transaction).

API DOCUMENTATION
This section describes all available APIs and what they do.
LOGIN API (REGISTER + LOGIN IN SAME ENDPOINT)localhost:8080/r1xchange/api/v1/account/login
POST /login What this API does:
If the user does NOT exist: It automatically registers the user and creates a wallet.
If the user already exists: It logs them in.

Purpose:
This single API functions as both register and login.
Request Example:
{
"email": "sachin@gmail.com",
"password": "test123",
}
Success Response Example:
{
"message": "Login successful",
"userId": "user001",
"jwt": qwertyuiopasdfghjkxcvbndfghjdfghjdfghdfghdfvbfgb
}
Error Examples:
400 – Missing username or password
401 – Invalid credentials

 GET WALLET BALANCE
GET http://localhost:8080/r1xchange/api/v1/account/d8eb8876-6808-4f8e-9407-cf4f3c89c861/balance)
What this API does:
Returns the current balance for the given userId.
Example Request:
GET /wallet/balance/user001

Response:
{
"userId": "user001",
"balance": 100
}

Error:
404 – Wallet not found

 TRANSFER MONEY
POST /transfer
What this API does:
Deducts money from one user
Adds money to another user
Ensures both updates happen safely

Request Example:
{
"fromUserId": "user001",
"toUserId": "user002",
"amount": 50
}

Success Response:
{
"message": "Transfer successful"
}

Errors:
400 – Insufficient balance
404 – Wallet not found
500 – Transaction failed

ERROR HANDLING
This section explains common error codes returned by the server.
400 – Bad Request (missing/invalid data)
401 – Unauthorized (invalid login)
404 – Wallet not found
500 – Internal Server Error

RUNNING TESTS
This project includes integration tests.
Run tests using:
mvn test
Tests are located in:
src/test/java/

PROJECT STRUCTURE
This section explains what each folder contains.
controller/ - API endpoints
service/ - Business logic (wallet operations)
repo/ - MongoDB repository
model/ - Wallet data model
config/ - Security, JWT configuration
