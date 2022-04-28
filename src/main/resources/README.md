# DB & Redis Session Server
## docker-compose up -d
1. move current path
2. ```docker-compose -p bookclub up -d``` on terminal
## Database: Postgres
1. ```docker exec -it postgres_bookclub /bin/bash``` on terminal
2. ```psql -U postgres```
3. ```create database bookclub;```
4. ```create user bookclub with encrypted password 'bookclub';```
5. ```grant all privileges on database bookclub to bookclub;```
