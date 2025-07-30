
# Paimon Moe – Application mobile de gestion d’équipements énergétiques

> **Projet universitaire 2025 — Développement mobile / PHP & MySQL**

---

## Présentation

**Paimon Moe** est une application mobile Android immersive qui permet à un utilisateur (l’« invocateur ») de :
- réserver des créneaux d’utilisation d’équipements énergétiques,
- consulter les habitats disponibles et leur état,
- visualiser un calendrier des réservations,
- suivre sa consommation et cumuler des « éco-coins » en fonction de son comportement.

L'univers graphique et sonore est inspiré de *Genshin Impact*, rendant l’expérience utilisateur ludique et attractive.

> Ce projet a été entièrement réalisé en autonomie dans le cadre d’un projet universitaire.

---

## Fonctionnalités

- **Authentification sécurisée** (email/mot de passe, Facebook)
- **Réservation de créneaux** avec sélection de l’équipement, date et horaire
- **Interface personnalisée** avec thèmes, illustrations et animations
- **Gestion des habitats** : état des appareils (allumé/éteint), étage, consommation (W)
- **Calendrier** : visualisation de la charge énergétique horaire
- **Système de récompenses** : cumul d’**éco-coins** selon le comportement
- **Historique complet** des réservations
- **Interface fluide et responsive**

---

## Technologies utilisées

- **Langages** : PHP (backend), Java (Android)
- **Backend** : PHP/MySQL hébergé via **XAMPP**
- **Base de données** : gérée avec **phpMyAdmin**
- **IDE** : Android Studio
- **Design** : Interface enrichie via ressources visuelles Genshin + animations

---

## Installation & Lancement

1. Cloner le projet dans Android Studio.
2. Modifier l'adresse IP dans `SessionManager.java` pour qu'elle corresponde à votre serveur PHP/MySQL local.
3. Démarrer XAMPP et importer la base de données fournie dans phpMyAdmin. (residence_db.sql)
4. Lancer l’application dans l’émulateur Android Studio ou sur un appareil réel.

---

## Structure du projet

```
├── app/
│   ├── manifests/... (AndroidManifest.xml)
│   ├── java/... (code source)
│   └── res/... (interfaces XML, images)
```

---

## Sujets abordés

- Gestion de sessions utilisateur
- Authentification sécurisée
- Communication Android ↔ PHP/MySQL (HTTP POST)
- Sérialisation JSON
- Affichage dynamique des données
- Application mobile complète sans framework externe

---
## Screenshot
<img width="353" height="665" alt="image" src="https://github.com/user-attachments/assets/c11f8a4a-9ae4-4b5d-9eae-ba741e6f7851" />
<img width="316" height="656" alt="image" src="https://github.com/user-attachments/assets/3a6a087f-feac-4b29-a75e-f2fd7b542174" />
<img width="298" height="602" alt="image" src="https://github.com/user-attachments/assets/57307039-3d2c-4d1d-995d-6406cd72313c" />
<img width="291" height="585" alt="image" src="https://github.com/user-attachments/assets/1c61cfe7-99ac-4949-82a1-2a489508143c" />
<img width="285" height="584" alt="image" src="https://github.com/user-attachments/assets/47050e09-3a42-438e-bd9b-a4bb4b9e3f32" />
<img width="281" height="592" alt="image" src="https://github.com/user-attachments/assets/d8783707-b45b-4f98-98ee-aa03249ecc7e" />
<img width="293" height="552" alt="image" src="https://github.com/user-attachments/assets/c5a5a651-7d4d-40ce-9f74-d5764b8534a0" />
<img width="295" height="563" alt="image" src="https://github.com/user-attachments/assets/2f43b1d3-9f88-4cc9-bf15-e2e81748b47c" />

---

## Auteur

Projet entièrement conçu et développé par **Willien Hu**  
Étudiant en BUT Informatique – Spécialité Développement d’applications  
[GitHub – @Kuyakii](https://github.com/Kuyakii)

---

## 📌 Remarques

> Pour tester l’application :
- Modifier l’adresse IP dans la classe `SessionManager`.
- Importer le fichier `.sql` dans votre base de données locale.
