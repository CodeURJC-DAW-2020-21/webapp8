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

![Favourite cryptocurrencies page](/Phase1Images/FavoriteCryptocurrencies.png "Favourite Cryptocurrencies Page")
This page display a list of the users favorite cryptocurrencies. The user can add new favourite cryptocurrencies through the main cryptocurrencies page and through recommendations. 

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
