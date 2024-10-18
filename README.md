# Projet Facturation - Ekwateur

Ce projet est une application Spring Boot qui permet de calculer les factures des clients en fonction de leur consommation d'électricité et de gaz.

## Prérequis

- JDK 21 ou supérieur
- Maven 3.x
- Un environnement Spring Boot

## Installation

Pour installer le projet, cloner le repository puis compiler et installer les dépendances via Maven :

```bash
mvn clean install
```

## Lancement de l'application

Pour lancer l'application, démarrer une configuration Spring Boot via Maven :

```bash
mvn spring-boot:run
```
## Utilisation

Une fois l'application démarrée, tester le calcul des factures via un appel HTTP POST à l'URL suivante :

```bash
http://localhost:8080/factures/calculer
```
## Body

Le endpoint attend un body au format JSON comme suit :

```bash
[
  {
    "consommationElectrique": 350,
    "consommationGaz": 150
  },
  {
    "consommationElectrique": 500,
    "consommationGaz": 250
  },
  {
    "consommationElectrique": 1000,
    "consommationGaz": 500
  }
]
```

## Réponse attendue

L'application retourne les factures calculées des clients avec pour chaque facture ledit client ainsi que le montant qu'il doit.
