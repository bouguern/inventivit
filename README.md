# Dream Case App - Restful API

L’objectif de ce mini projet est de créer une application Restful Web Service à l'aide de Spring Boot, et ayant 4 fonctionnements Create, Read, Update, Delete (CRUD)

## Prerequisites

- Pour exécuter cette application dans CMD, vous avez besoin de :
- Maven installé
- Java 11
- Spring Boot 2.7.18

## Getting Started

### Exécution de l'application

1. Extraire le zip
2. Allez dans le dossier de l'application et exécutez ces commandes:

   ```bash
   cd inventivit
   mvnw clean install
   java -jar -Dspring.profiles.active=dev target/inventivit-0.0.1-SNAPSHOT.jar
   ### Ou pour le PROD
   java -jar -Dspring.profiles.active=prod target/inventivit-0.0.1-SNAPSHOT.jar
   ### Ou un IDE (Eclipse, ...)

#### Une fois l'application lancée, vous pouvez y accéder en utilisant URL suivante: http://localhost:8080
#### Vous pouvez accéder à la base de données en DEV ENV ( in memory H2 ) l'aide de l'URL suivante : http://localhost:8080/h2-console/

Puis entrez ces données dans l'image : 

![User Guide](/images/DB.png)

Voici des exemples de requêtes API que vous pouvez effectuer :

- POST http://localhost:8080/cases/ (créez un nouveau dossier)
- GET http://localhost:8080/cases/1 (récupère les détails du dossier par caseId)
- PUT http://localhost:8080/cases/1 (modifier un dossier par caseId)
- DELETE http://localhost:8080/cases/1 (supprimer un dossier par caseId)

## Documentation

### API Documentation

La documentation de l'API fournit des détails sur les points de terminaison, les formats de requête et de réponse, ainsi que des exemples d'utilisation.

#### Vous pouvez accéder à la documentation de l'API à l'aide de l'URL suivante : http://localhost:8080/swagger-ui/index.html
