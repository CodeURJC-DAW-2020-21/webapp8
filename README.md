# webapp8
FUNCIONALITIES:
- Real time updates for cryptocurrency values.
- Show graphics regarding information about the price of cryptoccurencies throughout time.
- Forum creation, where the users can create entries, and the users can comment in said entries. The users will be able to vote them too.
- Being able to choose x cryptocurrencies so the web warns you when the prices are going up or down, based on a limit the user have decided.
- Log in.
- Register.
- Forgotten password recovery.
- Section with the most popular cryptocurrencies.
- Section with news about cryptocurrencies.
- The registered users will be able to update a profile picture.
- Possibility to sort the entries based on their puntuation, relevance, publication date, etc.
- The users will be able to report comments and entries.

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
