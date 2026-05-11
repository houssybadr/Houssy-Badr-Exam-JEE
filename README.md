# Houssy-Badr-Exam-JEE — Gestion des Contrats d'Assurance

**Auteur :** Houssy Badr  
**Examen :** Architecture Distribuée et Middleware — JEE Spring Boot + Angular  
**Filière :** GLSID / II-BDCC — ENSET Mohammedia, Université Hassan II

---

## Architecture Technique

```
Angular 21 (localhost:4200)
         │  HTTP + JWT Bearer
         ▼
Spring Boot 3.2 (localhost:8080)
   ├── Spring Security + JWT (jjwt 0.12.5)
   ├── REST Controllers (@PreAuthorize)
   ├── Service Layer (Interface + Impl)
   ├── Spring Data JPA (Hibernate)
   └── H2 (dev) / MySQL (prod)
```

## Lancement Backend

```bash
cd Houssy-Badr-Exam-JEE
./mvnw spring-boot:run
```

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 Console : http://localhost:8080/h2-console

## Lancement Frontend

```bash
cd frontend
npm install
ng serve
```

- Application : http://localhost:4200

## Comptes de test

| Utilisateur | Mot de passe | Rôles |
|-------------|--------------|-------|
| admin       | admin123     | ADMIN + EMPLOYE + CLIENT |
| employe1    | employe123   | EMPLOYE + CLIENT |
| client1     | client123    | CLIENT |

## API REST principales

| Méthode | Endpoint | Description | Rôle requis |
|---------|----------|-------------|-------------|
| POST | /api/auth/login | Authentification JWT | Public |
| GET | /api/clients | Liste des clients | EMPLOYE/ADMIN |
| POST | /api/clients | Créer un client | EMPLOYE/ADMIN |
| GET | /api/contrats | Liste des contrats | EMPLOYE/ADMIN |
| POST | /api/contrats | Souscrire un contrat | EMPLOYE/ADMIN |
| PUT | /api/contrats/{id}/valider | Valider un contrat | EMPLOYE/ADMIN |
| PUT | /api/contrats/{id}/resilier | Résilier un contrat | EMPLOYE/ADMIN |
| GET | /api/paiements/contrat/{id} | Paiements d'un contrat | CLIENT/EMPLOYE/ADMIN |
| POST | /api/paiements | Enregistrer un paiement | EMPLOYE/ADMIN |

## Structure du Projet

```
src/main/java/ma/houssybadr/assurance/
├── entities/         # Entités JPA (Client, ContratAssurance, sous-types, Paiement)
├── enums/            # StatutContrat, TypeLogement, NiveauCouverture, TypePaiement
├── repositories/     # Spring Data JPA Repositories
├── dtos/             # DTOs (records Java)
├── mappers/          # Mappers Entity ↔ DTO
├── services/         # Interfaces + Implémentations
├── web/              # REST Controllers
├── exceptions/       # GlobalExceptionHandler
├── config/           # SwaggerConfig
└── security/         # JWT, SecurityConfig, AppUser/AppRole

frontend/src/app/
├── models/           # Interfaces TypeScript
├── services/         # AuthService, ClientService, ContratService, PaiementService
├── interceptors/     # JwtInterceptor
├── guards/           # AuthGuard
└── components/       # Login, Navbar, Clients, Contrats, Paiements

docs/diagrams/        # Diagrammes PlantUML (.puml)
```
