
# Projet Zenbox

Ce projet met en place une solution de traitement de langage couplée à un système client-serveur. Cette solution permet de contrôler en langage naturel sous forme textuelle des objets connectés, comme une lampe ikea. 

## Bien démarrer

Ces instructions vont vous permettre d'obtenir une version fonctionnelle du projet sous votre environnement. 

### Prérequis

Ce que vous avez besoin d'installer et comment l'installer

* Java  
* Node JS
* EclipseEE (optionnel, si run le code java via terminal)

### Installation

Ces exemples vont vous mener pas à pas à travers l'installation afin d'avoir la solution prête à l'emploi sur votre machine

Clonez le répertoire github

```
git clone https://github.com/davidb987654/zenbox.git
```

téléchargez la dernière version de Core NLP, voir https://stanfordnlp.github.io/CoreNLP/download.html

Depuis eclipseEE, créer un projet maven. Ajouter tous les fichiers du dépot git presents dans Code java CoreNLP/src/corenlp dans votre dossier src puis modifier votre fichier pom.xml en le remplaçant par celui present dans le dépot Git.

Veillez à bien ajouter les librairies correspondant à CoreNLP téléchargées précedemment dans le Path. Il faudra également ajouter les librairies java permettant de run mysql (my sql connector).

## Déploiement

Pour lancer notre application vous devez suivre ces étapes :

  1. Sous Eclipse, 
```
run Serveur.java
```
  2. Via le terminal, dans le dossier pytradfri 
```
node server.js
```
  3. Via le terminalans le dossier Code App React
```
npm start
```
Une page navigateur s'ouvre, il vous suffit desormais de taper votre commande et de cliquer sur le bouton

## Auteurs
* **Guiplain Léo** 
* **Aguirre Max**
* **Izabelle François**

