# webapp8
## FOROCOIN

Team Members | Email | GitHub
:------------: | :-----: | :------:
Marcos Rodríguez García | m.rodriguezgar.2018@alumnos.urjc.es | marcoszas
Ángel Domínguez Figueros | a.dominguezf.2018@alumnos.urjc.es | AngelDomFig97
Adrián Sierra Robles | a.sierrar.2017@alumnos.urjc.es | adrisierra
Óscar Ramadán Pérez | o.ramadan.2018@alumnos.urjc.es | Oscarprezz
Adrián López Couso | a.lopezco.2018@alumnos.urjc.es | Adriton1

### COORDINATION APP:
https://trello.com/b/Y1LuXB9J/aplicacion-web

### ENTITIES:
- **User:** entity made to contain the client data.
- **Forum entry:** entity that contains all the data related to the forum entry, like the user who wrote it, the votes, the publication date, etc.
- **Cryptocurrency:** entity that contains the necessary data to manage the cryptocurrencies.
- **Comment:** entity that contains the data from the comments written in the entries, like who wrote it, the votes, the publication date, etc.

### USER TYPES:
- **Anonymous user:** Unregistered users will be able to navigate throughout the web page to see the forum entries as well as their comments, the cryptocurrencies values throughout the time
  in the web graphics. Nevertheless he won't be able to create entries or comments neither save cryptocurrencies in the followed cryptoccurencies list.
- **Registered user:** The registered user will be able to perform everything the anonymous user can do, plus being able to create entries, comment entries and have a list
  with the cryptocurrencies he has decided to follow.
- **Admin:** He will be able to add or delete cryptocurrencies, entries and comments, delete users and read from an inbox of reported accounts by the rest of users of the web.

### USER PERMISSIONS:
- **Anonymous user:** He won't have any kind of permission to modify anything in the web app.
- **Registered user:** He will be able to delete or modify an entry or a comment (if there's an edit it will be notified on the web app). Only him will be able to modify his list of
  favourite cryptocurrencies.
- **Admin:** He has total control over the web app. 

### IMAGES:
Registered users will be able to upload a profile image.

### GRAPHICS:
The web application will have graphics to show the price fluctuation of the cryptocurrencies throughtout the time.

### COMPLEMENTARY TECHNOLOGY:
The web application will make use of API Rests to get information in real time of prices from the cryptocurrencies.

### ALGORITHM OR ADVANCED QUERY:
Recomendation system based on the favorite cryptocurrencies of the the people you interact with.

## PHASE 1

![Forum page](/Phase1Images/Foro.png "Forum")
This is the web application front page, which contains the forum, where users will be able to create entries. The entries can be commented by the rest of the users.

![Comments page](/Phase1Images/Comments.png "Comments")
This is the comments page. It shows the comments the users have made about forum entries. The users will have the possibility to like this comments as well as comment them. 

![New entry modal](/Phase1Images/NuevaEntrada.png "New Entry")
This modal allows the user to create a new entry with a title, a description and an attachment.

![New comment modal](/Phase1Images/EntryReply.png "Entry Reply")
This modal allows the users to comment on entries as well as on commentaries. They can write a description and add an attachment.

![Cryptocurrencies page](/Phase1Images/AllCryptocurrencies.png "Cryptocurrencies Page")
This page displays a list of cryptocurrencies, showing their price in real time, a little graph of their price changes and a button to add the cryptocurrencies to a favorites list.

![Bitcoin page](/Phase1Images/Bitcoin.png "Bitcoin Page")
When the users click on one of the cryptocurrencies listed they will be redirected to a page like this. In here there will be real time graphs displaying the price of the cryptocurrencies.

![Favourite cryptocurrencies page](/Phase1Images/FavoriteCryptocurrencies.png "Favourite Cryptocurrencies Page")
This page display a list of the users favorite cryptocurrencies. The user can add new favourite cryptocurrencies through the main cryptocurrencies page and through recommendations. 

![User Settings page](/Phase1Images/Settings.png "User Settings Page")
This page allows the user to edit their profile image, nickname, name, forename etc.

![Login page](/Phase1Images/Login.png "Login Page")
This is the login where user will be able to log in to the web application if they have an existing account.

![Register page](/Phase1Images/Registro.png "Registration Page")
In this page the users will be able to create a new account if they don't already have one.

![Forgotten password page](/Phase1Images/PasswordRecovery.png "Password Recovery Page")
If the users forget their passwords, they will be redirected to this page to get an email to change their password.

![Error 401 page](/Phase1Images/401Error.png "401 Error Page")
If the users tries to enter an unrestricted area in the web application they will be redirected to this page.

![Error 404 page](/Phase1Images/404Error.png "404 Error Page")
If the url requested by the user is not found in the server they will be redirected to this page.

![Error 500 page](/Phase1Images/500Error.png "500 Error Page")
If there is a server error the user will be redirected to this page.

## Navigation Diagram
![Navigation Diagram Image](/Phase1Images/NavigationDiagram.png "Navigation Diagram")



## PHASE 2

### Navigation Diagram

![Navigation Diagram Image](/Phase2Images/NavigationDiagram.png "Navigation Diagram")
New Navigation Diagram 

![Admin User List](/Phase2Images/UserList.png "Admin User List")
Admin diagram added to Navigation Diagram

![CommentUserRegistered](/Phase2Images/CommentUserRegistered.png"Forum")
New index for registered users

![CryptFav](/Phase2Images/CryptFav.png "CryptFav")
Favourite Criptocurrency  

![CryptosUserNotRegistered](/Phase2Images/CryptosUserNotRegistered.png "CryptosUserNotRegistered")
Criptocurrency screen for all users

![CrytosUserNotRegistered](/Phase2Images/CrytosUserRegistered.png "CrytosUserNotRegistered")
Criptocurrency screen for registered users

![IndexAdmin](/Phase2Images/IndexAdmin.png "IndexAdmin")
New admin index

![IndexUserNotRegistered](/Phase2Images/IndexUserNotRegistered.png "IndexUserNotRegistered")
Index for all users

![IndexUserRegistered](/Phase2Images/IndexUserRegistered.png "IndexUserRegistered")
Index for registered users

![NewEntryUserNotRegistered](/Phase2Images/NewEntryUserNotRegistered.png "NewEntryUserNotRegistered")
New entry screen for no registered users

![NewEntryUserRegistered](/Phase2Images/NewEntryUserRegistered.png "NewEntryUserRegistered")
New entry screen for registered users

![RecommendationSystem](/Phase2Images/RecommendationSystem.png "RecommendationSystem")
New recommendation system

![UserAdminList](/Phase2Images/UserListAdmin.png "UserAdminList")
Admin user administration list

![UserSettings](/Phase2Images/UserSettings.png "UserSettings")
User settings screen



### Execution Instruction
You can download the complete code in our github link https://github.com/CodeURJC-DAW-2020-21/webapp8, build it and run it with IntelliJIdea 2020.
You will need at least Java 11, MySQL 8.0 and Docker.
To create the MySQL server you have to run the following command: sudo docker run --rm -e MYSQL_ROOT_PASSWORD=DawWebApp8 -e MYSQL_DATABASE=webapp8_bbdd -p 3306:3306 -d mysql:8.0.22
After the MySQL server has been created, the application can be runned from you IDE of choice.
To see the web app you must copy this link in to your browser: https://localhost:8443/


### DataBase Entities Diagram
![Navigation Diagram Image](/Phase2Images/EntityDiagram.png "Entity Diagram")

### Class and Templates Diagram
![ClassAndTemplatesDiagram](/Phase2Images/ClassAndTemplatesDiagram.png "ClassAndTemplatesDiagram")

### Participation

In this phase we perform the application using Spring Boot. The most important upgrades that we have made are: a MySql Database for the web, a registration and login system, a user permission system and the possibility to upload images, create entries and comments.
The administrator can also delete users using a new user list and delete also their own comments. We have also implemented the possible different error pages.

| Marcos Rodríguez                                                                                           | Adrián López                                                                                               | Adrián Sierra                                                                                              | Ángel Domínguez                                                                                            | Óscar Ramadán
|:-----------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------:|
| [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/d9f9538feb6a295faa3c79631783b8194d69690d) | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/7b465060d2dbdb364b2c96e1d6e89588ac510d74) | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/2d9556ea84e21ff5d0c76e82166835e7cb25bef3) | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/6a88ba0958a9593ef043e5c98a7838726fcb9316) |[Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/35c200c3d23dfcee4b03c79662893178de8e51ee)  |
| [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/a84aa13a65502664f89a2a2721201a2da86cd5d6) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/ca003c95d2f860b42b8cc0d1eeb104d8af5ed49e) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/ff86c605f1e940f646c52e26264e81295befad17) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/1261c301f5252017d109575bf2dab595fd802f95) |[Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/636e85dd3a326b72f36ca9165a29eb7e75c7decb)  |
| [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/c7c56a3427da9bce0c1a074cfa7e9092bef5d8d0) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/7b9a13407812a9d91bd5de955dbd705d6146f405) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/24d574cb605ba854b32f4a93c99a542d651d10a6) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/f6188599e0a2a18e2e01ef7647d2d8b5a0a6acb2) |[Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/de2e5d3e716e5133de2a490e2d94f1bb937bcc23)  |
| [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/792f17a1c946534850bfbdaa15b26385813f25fe) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/df110b555994d5cc1b0fa96bb7554cf16eea9e2f) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/b4f034f15d27c56035e835d1397b4a131e53e14c) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/f0c06823870e4f0f1ebff09edcda06c640f233b7) |[Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/8a13d8c7a39543010641aca5c193cdb13edf61dc)  |
| [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/515d81a835a2d9c39d82c23eadd41ac1b30998df) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/b53ef4d8507a3013016fecf06ee74cb8e4141a55) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/eca332f999c2734897c4925ca50385e78ab6352b) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/1261c301f5252017d109575bf2dab595fd802f95) |[Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/cf1080e3b2cfa06c70f4be995ffddde1fadb35d1)  |

### Files
| Marcos                  | Adrián L.                | Adrián S.                       | Óscar                   | Ángel                    |
|:-----------------------:|:------------------------:|:-------------------------------:|:-----------------------:|:------------------------:|
| Header and footer       | RegisterController.java  | README                          | MyErrorController.java  | Models                   |
| Index.html              | ForgotPassController.java| MyErrorController.java          | PostController.java     | Database                 |
| UserController.java     | CriptocurrencyController.java | CommentController.java     | application.properties  | UserController.java      |
| userList.html           | password.html            | CriptocurrencyController.java   | Security                | RegisterController.java  |
| PostController.java     | criptomonedas.html       | CommentRepository.java          | UserService.java        | loginError.html          |


## PHASE 3

We have implemented a API REST for the application and we've also packaged and distributed it using Docker technology. On the other hand, we have tested and included a collection of Postman requests.

### API REST documentation:
- [HTML FILE](https://github.com/CodeURJC-DAW-2020-21/webapp8/blob/predevelop2/api-docs/api-docs.html)
- [YAML file](https://github.com/CodeURJC-DAW-2020-21/webapp8/blob/predevelop2/api-docs/api-docs.yaml)
- [Interpreted HTML documentation]()

### Dockerized application execution instructions:
To run the app follow the next steps:
- Step 1: install docker desktop and docker compose [click here](https://desktop.docker.com/win/stable/amd64/Docker%20Desktop%20Installer.exe?utm_source=docker&utm_medium=webreferral&utm_campaign=dd-smartbutton&utm_location=header)
- Step 2: download project in GitHub [click here](https://github.com/CodeURJC-DAW-2020-21/webapp8/archive/refs/heads/predevelop2.zip)
- Step 3: Open terminal
- Step 4: Get into directory Backend ```cd C:\{your download path}\webapp8\Backend```
- Step 5: Build image in docker```sudo docker build -t marcoszas/webapp8 . ```
- Step 6: ```docker-compose up ```
- Step 7: Type in the browser https://localhost:8443/ and it will show the application web

### How to build the docker image:
- Step 1: Install docker desktop and docker compose [click here](https://desktop.docker.com/win/stable/amd64/Docker%20Desktop%20Installer.exe?utm_source=docker&utm_medium=webreferral&utm_campaign=dd-smartbutton&utm_location=header)  
- Step 2: Clone project from GitHub [click here](https://github.com/CodeURJC-DAW-2020-21/webapp8.git)
- Step 3: Open terminal
- Step 4: Get into directory Backend ```cd C:\{your download path}\webapp8\Backend```
- Step 5: Execute ```./docker_image.sh```




