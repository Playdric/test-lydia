# Test Technique Lydia
## Architecture 
* MVVM
* Clean Archi
## Organisation des modules
#### `app`
Contient l'UI + un package dédié à l'injection de dépendances
#### `presentation`
Contient tous les ViewModel et les états d'UI
#### `data` 
Contient : 
* Les modèles de donnée remote (représantant la donnée qui vient de l'API)
* Les modèles de donnée local (qui représente la donnée utilisée au sein de l'app)
* La base de donnée locale
* Les appels réseaux et la gestion de la pagination des données
## Librairies utilisées
### Koin
Injection de dépendance
* Simple d'utilisation
* Utilise Kotlin DSL
* License permet l'utilisation commerciale
### Coil 
Gestion de chargement d'images
* Simple d'utilisation
* Rapide et léger
* License permet l'utilisation comerciale
### Retrofit + Gson
Appels réseaux
* Grande modularité de configuration
* Grande communauté d'utilisateurs
* Mise en avant par Google
* License permet l'utilisation commerciale
### Junit
Tests unitaires
## Tests
* Tests unitaires sur la BDD et le mapping des données remote vers local
