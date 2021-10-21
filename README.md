# PubGTour - A Case Study

## Problem Statement
Build a system to list PubG (game) tournaments, show statistics of matches of a
tournament and bookmark favourite match.

## Requirements
- The application needs to fetch PubG tournaments data by registering with the following link
and get API key required to call the APIs.
● https://developer.pubg.com/

- Reference APIs:
  1. https://api.pubg.com/tournaments
  2. https://api.pubg.com/tournaments/<tournament-ID>
  3. https://api.pubg.com/shards/tournament/matches/82821ad4-1b53-48e0-b116-1e33b89bb6c3
  
- A frontend web app where the user can register/login to the application, list PubG
tournaments, show matches details for selected tournament and bookmark favourite
match.
- User can add match into favourite list and should be able to view the favourite matches for
user.

## Modules

### UserService - should be able to manage user accounts.

### UI (User interface) - should be able to
1. List PubG tournaments and summary of matches for each tournament
2. View details of a match with in a tournament
3. Add a match to favourite list
4. should be able to see favourite matches
5. UI should be responsive which can run smoothly on various devices

### FavouriteService - should be able to store all the favourite PubG matches for a user

## Tech Stack
- Spring Boot
- Angular
- CI (Gitlab Runner)
- Docker, Docker Compose

## Flow of Modules

### Building frontend
- Building responsive views:
1. Register/Login
2. Show list of PubG tournaments - populating from external API
3. Show match details - populating from external API
4. Build a view to show favourite matches
- Using Services to populate these data in views
- Stitching these views using Routes and Guards
- Making the UI Responsive
- E2E test cases and unit test cases
- Writing CI configuration file
- Dockerize the frontend

### Building the UserService
- Creating a server in Spring Boot to facilitate user registration and login using JWT token and
MySQL
- Writing swagger documentation
- Unit Testing
- Write CI Configuration
- Dockerize the application
- Write docker-compose file to build both frontend and backend application

### Building the Favourite Service
- Building a server in Spring Boot to facilitate CRUD operation over favourite PubG matches
stored in MySQL
- Writing Swagger Documentation
- Write Test Cases
- Write CI Configuration
- Dockerize the application
- Update the docker-compose

### Demonstrate the entire application
