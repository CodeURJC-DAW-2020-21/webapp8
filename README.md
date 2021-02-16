# webapp8
## APP NAME: FOROCOIN

TEAM MEMBERS: Marcos Rodríguez García (Correo: m.rodriguezgar.2018@alumnos.urjc.es Github: marcoszas), Ángel Domínguez Figueros (Correo: a.dominguezf.2018@alumnos.urjc.es Github: AngelDomFig97), Adrián Sierra Robles (Correo: a.sierrar.2017@alumnos.urjc.es Github: adrisierra), Óscar Ramadán Pérez (Correo: o.ramadan.2018@alumnos.urjc.es Github: Oscarprezz), Adrián López Couso (Correo: a.lopezco.2018@alumnos.urjc.es Github: Adriton1).

COORDINATION APP: https://trello.com/b/Y1LuXB9J/aplicacion-web

ENTITIES:
- User: entity made to contain the client data.
- Forum entry: entity that contains all the data related to the forum entry, like the user who wrote it, the votes, the publication date, etc.
- Cryptocurrency: entity that contains the necessary data to manage the cryptocurrencies.
- Comment: entity that contains the data from the comments written in the entries, like who wrote it, the votes, the publication date, etc.

USER TYPES:
- Anonymous user: Unregistered users will be able to navigate throughout the web page to see the forum entries as well as their comments, the cryptocurrencies values throughout the time
  in the web graphics. Nevertheless he won't be able to create entries or comments neither save cryptocurrencies in the followed cryptoccurencies list.
- Registered user: The registered user will be able to perform everything the anonymous user can do, plus being able to create entries, comment entries and have a list
  with the cryptocurrencies he has decided to follow.
- Admin: He will be able to add or delete cryptocurrencies, entries and comments, delete users and read from an inbox of reported accounts by the rest of users of the web.

USER PERMISSIONS:
- Anonymous user: He won't have any kind of permission to modify anything in the web app.
- Registered user: He will be able to delete or modify an entry or a comment (if there's an edit it will be notified on the web app). Only him will be able to modify his list of
  favourite cryptocurrencies.
- Admin: He has total control over the web app. 

IMAGES: Registered users will be able to upload a profile image.

GRAPHICS: The web application will have graphics to show the price fluctuation of the cryptocurrencies throughtout the time.

COMPLEMENTARY TECHNOLOGY: The web application will make use of API Rests to get information in real time of prices from the cryptocurrencies.

ALGORITHM OR ADVANCED QUERY: Users will be able to sort the entries based on their relevance, votes or publication date. 
