#Authentication
https://github.com/amaurym/react-redux-auth0-kit

#TODO:
Door tijdsgebrek en het leren uit mijn fouten zijn er een aantal stukken code die aan verbetering toe zijn:

##Use File server
Images worden als byte[] geupload naar de DB voor gebruikgsemak, 
maar voor dit te optimaliseren en ook videos toe te laten zou dit beter gebeuren via een file server.
(Er is een branch voorzien waar al grote stukken code voor videos toe te voegen in staat)

##Use Redux everywhere 
Redux was compleet nieuw voor mij, hierdoor heb ik een aantal fouten gemaakt tijdens het gebruiken hiervan.
1 van de fouten is het niet gebruiken voor alles. Ik heb veel React components herbruikbaar gemaakt. 
Maar heb hiervoor niet met states gewerkt. Dit zorgt voor een hel van functies en objecten doorgeven.
Dit is op te lossen door gebruik te maken van redux voor alle objecten in forms.  