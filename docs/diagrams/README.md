# Diagrammes du Projet — Houssy Badr

## 🔧 Installation du plugin PlantUML dans IntelliJ

1. File → Settings (Ctrl+Alt+S)
2. Plugins → Marketplace
3. Cherche **"PlantUML Integration"**
4. Installe le plugin → Redémarre IntelliJ
5. (Optionnel) Installe Graphviz : https://graphviz.org/download/

## 👁️ Visualiser un diagramme

1. Ouvre un fichier `.puml` dans IntelliJ
2. Le panneau de prévisualisation apparaît automatiquement à droite
3. Si non visible : View → Tool Windows → PlantUML

## 🖼️ Exporter en PNG (pour le rapport PDF)

**Méthode 1 — Bouton export :** Dans le panneau PlantUML → icône 💾 "Save current diagram" → choisir PNG

**Méthode 2 — Serveur en ligne :**
1. Va sur https://www.plantuml.com/plantuml/uml
2. Colle le contenu du `.puml` → Submit → enregistrer l'image

## 📋 Liste des diagrammes

| # | Fichier | Description |
|---|---------|-------------|
| 1 | `01-architecture-technique.puml` | Architecture en couches (Présentation / Backend / BDD) |
| 2 | `02-class-diagram.puml` | Diagramme de classes JPA avec héritage SINGLE_TABLE |
| 3 | `03-use-case.puml` | Cas d'utilisation par rôle (Client / Employé / Admin) |
| 4 | `04-sequence-auth-jwt.puml` | Séquence complète d'authentification JWT |
| 5 | `05-deployment.puml` | Déploiement (navigateur → Spring Boot → BDD) |
