
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
3. Démarrer XAMPP et importer la base de données fournie dans phpMyAdmin.
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

## Auteur

Projet entièrement conçu et développé par **Willien Hu**  
Étudiant en BUT Informatique – Spécialité Développement d’applications  
[GitHub – @Kuyakii](https://github.com/Kuyakii)

---

## 📌 Remarques

> Pour tester l’application :
- Modifier l’adresse IP dans la classe `SessionManager`.
- Importer le fichier `.sql` dans votre base de données locale.
