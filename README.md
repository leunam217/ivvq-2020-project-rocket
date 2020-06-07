 Rocket Shop Application
=======================
Application de commerce électronique développée pour effectuer des opérations de rôle d'utilisateur admin et client avec les interfaces utilisateurs respectives.

  - Les services web RESTfull : La construction de l'API à l'aide de spring boot est utilisée pour gérer toutes les opérations du back-end.
  - Front End : Interfaces utilisateur conçues et développées à l'aide de services web utilisant Vue.js pour traiter les actions appropriées de l'utilisateur.
 
 Membres du groupe Rocket
 ========================
 
* Marième KEITA
* Malalatiana RANDRIANARIJAONA
* Jonathan LAO-KAN
* Manuel CABARCOS-BAULINA

Build et Lancement de l'application en local
============================================

```sh
$ docker-compose build
$ docker-compose up -d
```
Lancement de l'environnement des test
======================================

```sh
$ docker-compose up testting-env
```

Lien vers Heroku
========================================

* <https://rocket-ivvq-project.herokuapp.com/>

Diagramme De Classes V1.0
=========================

 ![models](https://user-images.githubusercontent.com/55536171/79555437-e0eceb80-809f-11ea-9994-21374698a8c6.png)

Technologies utilisées
======================

* Vue JS
* SpringBoot
* PostGreSQL
* Docker
* JUNIT5, JACOCO, Spotbugs, Spotless & ESLint
* Gherkin
* Heroku
