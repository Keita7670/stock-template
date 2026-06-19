# Template Spring Boot + JPA + MySQL

## Avant de lancer

1. Créez la base de données dans MySQL :
   ```sql
   CREATE DATABASE stockdb;
   ```

2. Vérifiez `src/main/resources/application.properties` :
   - `spring.datasource.url` → vérifiez le **port** (3306 par défaut)
   - `spring.datasource.username` / `password` → vos identifiants MySQL

3. Dans IntelliJ : clic droit sur `pom.xml` → **Maven → Reload Project**

## Lancer le projet

```bash
mvn spring-boot:run
```

ou exécutez `StockApplication.java` directement dans IntelliJ.

## Tester

- `GET    http://localhost:8080/api/produits`
- `POST   http://localhost:8080/api/produits`   (body JSON: {"nom":"Stylo","quantite":10,"prix":1.5})
- `GET    http://localhost:8080/api/produits/1`
- `PUT    http://localhost:8080/api/produits/1`
- `DELETE http://localhost:8080/api/produits/1`

## Pour un nouveau projet

1. Copiez ce dossier entier
2. Renommez le package `com.example.stock` si besoin (Refactor → Rename dans IntelliJ)
3. Adaptez `pom.xml` (`artifactId`, `name`)
4. Renommez/adaptez `Produit` → votre entité

## En cas d'erreur "entityManagerFactory not found"

- Vérifiez que MySQL est démarré
- Vérifiez le port dans `application.properties`
- Vérifiez que `mvn dependency:tree` ne montre pas de versions Spring Boot mélangées
- File → Invalidate Caches / Restart
