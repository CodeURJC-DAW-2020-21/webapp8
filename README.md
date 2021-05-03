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
- [Interpreted HTML documentation](http://raw.githack.com/CodeURJC-DAW-2020-21/webapp8/predevelop2/api-docs/api-docs.html)

### Dockerized application execution instructions:
To run the app follow the next steps:
- 1: install docker desktop and docker compose [click here](https://desktop.docker.com/win/stable/amd64/Docker%20Desktop%20Installer.exe?utm_source=docker&utm_medium=webreferral&utm_campaign=dd-smartbutton&utm_location=header)
- 2: download project in GitHub [click here](https://github.com/CodeURJC-DAW-2020-21/webapp8/archive/refs/heads/predevelop2.zip)
- 3: Open terminal
- 4: Get into the Backend directory ```cd C:\{your download path}\webapp8\Backend```
- 5: Build image in docker ```sudo docker build -t marcoszas/webapp8 . ```
- 6: ```docker-compose up ```
- 7: Type in the browser https://localhost:8443/ and it will show the application web

### How to build the docker image:
- Step 1: execute this command in terminal: ```wget https://raw.githack.com/CodeURJC-DAW-2020-21/webapp8/predevelop2/Backend/docker_image.sh```
- Step 2: give execution permissions to the downloaded file ```chmod +x docker_image.sh```
- Step 3: execute ```./docker_image.sh```

### Classes and templates Diagram:
![Navigation Diagram](https://github.com/CodeURJC-DAW-2020-21/webapp8/blob/predevelop2/Phase3Images/ClassesAndTemplatesDiagram.png)

### Participation
In this third phase we have prepared our application to be used in the REST API format, with the aim of expanding the use of ForoCoin. All this update is properly documented.
We have also dockerized and published ForoCoin thanks to docker.

| Marcos Rodríguez | Adrián López | Adrián Sierra | Ángel Domínguez | Óscar Ramadán |
|:----------------:|:------------:|:-------------:|:---------------:|:-------------:|
| [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/8feb1c29b7da4c7d03ac364c3a029258d6319595) | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/9e3a86a3dc3485837a77777758b290bb216fd1bc) | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/1f06845a201760d145ba9f884c2fee622ec45b79) | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/90f286e04f6497e386b07c7d9aef97ed67fa9df5) |[Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/e637b98a8f335f7cda7f7cb806fdc4f6d94ef64b)  |
| [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/4e8062133857847651b4696f0ee029f04cbc3f7a) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/fbb5f14cf4801712a3ba42fe4c3aa0c9a65c9ca4) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/0552e3e854990584012fee0a673d4985c6b2d9ae) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/3d34b02a64d7f1952078c323668ac1b22c816f06) |[Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/cd70962bf667c99dd2b8017727352d89cd10d4c2)  |
| [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/7ebfb2b92aff2199d4b67b9f7150f0b305716893) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/972dbff88a7595de4576b077071523e6914fc2bb) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/5dde33e8c003444071723761bcccc03fc03a2ddf) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/e01b794e94f499c7ce511b4f1c6cb4c6895862b5) |[Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/1f499b3a44b04118418b366fba0860903f363cc5)  |
| [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/6f245890af05121f5714538f1175a5f36c63601a) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/9c70271873c978aff3b7d66214f9a6d7afe49a66) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/813de37bb3169ca1dd1d6304095b164213a513b6) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/813de37bb3169ca1dd1d6304095b164213a513b6) |[Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/4e8062133857847651b4696f0ee029f04cbc3f7a)  |
| [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/ebd3a0a26a878da5407efcd3f0e7b0d09831bc82) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/a3e98e99976d0ea49a2f054fc42cb10460da91ea) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/2344eaed9b7796448184d4ceebc1a75d2d44fd41) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/b7cf1870576521d86bd19a3fcb2592b197ba1e9b) |[Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/cf1080e3b2cfa06c70f4be995ffddde1fadb35d1)  |

### Files
| Marcos                  | Adrián L.                         | Adrián S.                           | Óscar                    | Ángel                   |
|:-----------------------:|:---------------------------------:|:-----------------------------------:|:------------------------:|:-----------------------:|
| UserDTO.java            | UserRestController.java           | CommentDTO.java                     | GraphRestController.java | EntryDTO.java           |
| UserRestController.java | CryptocurrencyRestController.java | CommentRestController.java          | docker-compose.yml       | EntryRestController.java|
| RestSecurityConfig.java | Cryptocurrency.java               | Comment.java                        | Dockerfile               | Entry.java              |
| docker_image.sh         | User.java                         | UserRestController.java             | RestSecurityConfig.java  | UserRestController.java |
| User.java               | RestSecurityConfig.java           | Readme                              | EntryRestController.java | RestSecurityConfig.java |

## PHASE 4

### HOW TO BUILD THE ANGULAR APP

In order to do the following instructions you need to have installed Docker and Docker-Compose.

- To build the Angular application, first the user must clone the repository via the command: ```git clone https://github.com/CodeURJC-DAW-2020-21/webapp8.git```.
- Second you have to change directory to the Docker directory. To do so use the command cd ```cd webapp8/Docker/```. 
- Then build the Angular application and the backend using the command ```bash docker_image.sh```.
- Finally, to execute the Docker image use the command ```sudo docker-compose up```. 
- And that's it, to use the Angular application just introduce the following url in your browser: ```https://localhost:8443/new```.

### Participation
In this fourth phase we have implemented a new Angular frontend. Now our app is accesible via the traditional MVC Spring-Boot application and trough our Angular interface. 
We have also dockerized and published ForoCoin with both designs thanks to docker.

| Marcos Rodríguez | Adrián López | Adrián Sierra | Ángel Domínguez | Óscar Ramadán |
|:----------------:|:------------:|:-------------:|:---------------:|:-------------:|
| [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/1d4733b395040122f4fd24b4458a2a2c96620c17) | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/bbee3c5de0dbc85c4f3ef4331694bf81cc9b1856) | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/3dcac55398deff92b171b907108aff85fa2ef875) | [Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/b2b752705d1fd3c2234e55c5f6c3d616b76fb9cb) |[Commit1](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/009f38e4199d129298210f39a49ed731185b1269)  |
| [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/82aa7b5745828be8009fba261138588408d4953b) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/8dc74f1ef068dd57b9bb4bdaac017ed3702f0f60) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/eb9315132efb9bac26ad6c5aa285255b33066d46) | [Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/9aef87720f71bb76acb08875f1b1716eac45d7d8) |[Commit2](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/8eb93521ebf785c1a9a5050393cab1cf1c866d89)  |
| [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/cbb8fe596a48a0bac2360367a7c78c392eea5a13) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/780ac9588503ee348fca28bfe9cc758380e52cec) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/c2b28562c83ef83a010dbc832c21aafd7efb4b76) | [Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/049e55c2da96fd66e62271dc042c21051439999a) |[Commit3](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/0d26f4d91a4213afb447951e5766ebb7660f4a86)  |
| [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/b48d684463f3f4dffc04c3dac63e655baa707228) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/29a06c3f5741de5d74de033f86b6ea14283cbd0a) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/60495547fd023073171f64419a8d5b36778e20ac) | [Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/592b9211cc04fccaec222610fec28287934d640c) |[Commit4](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/d76c68568d50fba1307046ee0597dcc74f4dcb7f)  |
| [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/384ea4aced81aa3adc0c65150fb9e0ba63a5cc57) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/f2e4507fb76dc068b77f162ef5ca1df8ec54dfea) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/eb7fc61edd10c1e593e8971aa8f4e9de7e31e89c) | [Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/94dbe714eb2f66e9908d57d9ccd1ec07bdf11dfe) |[Commit5](https://github.com/CodeURJC-DAW-2020-21/webapp8/commit/8a9cc2702d57af887de13392f6f32d94fa1a7bb2)  |

### Files
| Marcos                  | Adrián L.                         | Adrián S.                           | Óscar                        | Ángel                   |
|:-----------------------:|:---------------------------------:|:-----------------------------------:|:----------------------------:|:-----------------------:|
| userlist.component.ts   | cryptocurrency.component.html     | charts.component.ts                 | app-routing.module.ts        | entry.component.ts      |
| register.component.ts   | cryptocurrency.component.ts       | charts.component.html               | chart.component.ts           | comment.component.ts    |
| new-entry.component.ts  | cryptocurrencies.service.ts       | settings.component.ts               | favCrypto.service.ts         | send-email.component.ts |
| docker_image.sh         | friends.component.html            | settings.component.html             | header.component.html        | entries.service.ts      |
| user.service.ts         | friends.component.ts              | main-forum.component.html           | error.component.ts           | new-comment.component.ts|


### SPA class diagram and templates

![UML Diagram](/Phase4Images/UML.png)
