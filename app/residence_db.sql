-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 24 juil. 2025 à 17:41
-- Version du serveur : 11.7.2-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `residence_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `consommation`
--

CREATE TABLE `consommation` (
  `id_consommation` int(11) NOT NULL,
  `id_habitat` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `heure` time NOT NULL,
  `consommation_watt` int(11) NOT NULL,
  `id_equipement` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `consommation`
--

INSERT INTO `consommation` (`id_consommation`, `id_habitat`, `date`, `heure`, `consommation_watt`, `id_equipement`) VALUES
(1, 1, '2025-03-20', '08:00:00', 1200, NULL),
(2, 1, '2025-03-21', '12:00:00', 850, NULL),
(3, 1, '2025-03-22', '18:00:00', 930, NULL),
(4, 22, '2025-03-21', '07:00:00', 600, NULL),
(5, 22, '2025-03-22', '15:00:00', 780, NULL),
(6, 22, '2025-03-23', '22:00:00', 910, NULL),
(7, 23, '2025-03-20', '06:30:00', 500, NULL),
(8, 23, '2025-03-21', '13:00:00', 620, NULL),
(9, 23, '2025-03-22', '20:30:00', 870, NULL),
(10, 7, '2025-03-20', '10:00:00', 450, NULL),
(11, 7, '2025-03-21', '16:00:00', 690, NULL),
(12, 25, '2025-03-20', '11:00:00', 700, NULL),
(13, 25, '2025-03-21', '19:00:00', 980, NULL),
(14, 3, '2025-03-19', '09:00:00', 400, NULL),
(15, 8, '2025-03-18', '14:00:00', 760, NULL),
(16, 13, '2025-03-22', '12:00:00', 680, NULL),
(17, 19, '2025-03-23', '15:30:00', 890, NULL),
(18, 26, '2025-03-22', '17:30:00', 940, NULL),
(19, 1, '2025-03-28', '00:30:00', 894, NULL),
(20, 1, '2025-03-24', '08:30:00', 782, NULL),
(21, 1, '2025-03-26', '07:30:00', 879, NULL),
(22, 1, '2025-03-26', '18:00:00', 598, NULL),
(23, 1, '2025-03-26', '09:30:00', 648, NULL),
(24, 1, '2025-03-27', '16:00:00', 508, NULL),
(25, 1, '2025-03-29', '22:30:00', 910, NULL),
(26, 1, '2025-03-26', '00:00:00', 1162, NULL),
(27, 1, '2025-03-28', '04:00:00', 528, NULL),
(28, 1, '2025-03-29', '07:00:00', 1165, NULL),
(29, 8, '2025-03-25', '05:30:00', 683, NULL),
(30, 7, '2025-03-28', '21:00:00', 1266, NULL),
(31, 3, '2025-03-27', '17:30:00', 1078, NULL),
(32, 8, '2025-03-28', '20:00:00', 936, NULL),
(33, 3, '2025-03-26', '13:30:00', 1116, NULL),
(34, 8, '2025-03-25', '10:30:00', 504, NULL),
(35, 7, '2025-03-25', '07:00:00', 755, NULL),
(36, 10, '2025-03-24', '22:00:00', 761, NULL),
(37, 2, '2025-03-29', '21:00:00', 815, NULL),
(38, 8, '2025-03-25', '16:30:00', 1079, NULL),
(39, 2, '2025-03-31', '07:30:00', 54, NULL),
(40, 1, '2025-04-07', '06:30:00', 45, NULL),
(42, 7, '2025-04-07', '13:00:00', 68, NULL),
(44, 10, '2025-04-07', '15:00:00', 103, NULL),
(46, 2, '2025-04-09', '04:30:00', 172, NULL),
(47, 20, '2025-04-10', '09:00:00', 130, NULL),
(48, 2, '2025-04-09', '03:00:00', 145, NULL),
(49, 9, '2025-04-10', '00:00:00', 62, NULL),
(50, 1, '2025-04-10', '14:30:00', 57, NULL),
(51, 20, '2025-04-08', '20:30:00', 68, NULL),
(52, 20, '2025-04-07', '17:30:00', 52, NULL),
(53, 9, '2025-04-11', '16:00:00', 92, NULL),
(54, 20, '2025-04-11', '01:30:00', 177, NULL),
(57, 8, '2025-04-08', '08:00:00', 145, NULL),
(59, 9, '2025-04-08', '08:30:00', 84, NULL),
(60, 20, '2025-04-07', '06:30:00', 49, NULL),
(62, 8, '2025-04-09', '10:30:00', 153, NULL),
(64, 2, '2025-04-10', '17:00:00', 143, NULL),
(65, 1, '2025-04-10', '19:00:00', 96, NULL),
(66, 2, '2025-04-07', '14:30:00', 127, NULL),
(67, 9, '2025-04-11', '00:30:00', 83, NULL),
(68, 9, '2025-04-10', '01:30:00', 113, NULL),
(70, 7, '2025-04-07', '21:00:00', 43, NULL),
(71, 1, '2025-04-10', '07:00:00', 134, NULL),
(72, 2, '2025-04-10', '17:30:00', 107, NULL),
(75, 9, '2025-04-11', '11:00:00', 149, NULL),
(76, 26, '2025-04-18', '01:30:00', 54, NULL),
(77, 7, '2025-04-15', '18:30:00', 142, NULL),
(78, 20, '2025-04-16', '12:30:00', 49, NULL),
(79, 9, '2025-04-15', '13:00:00', 94, NULL),
(80, 1, '2025-04-17', '10:30:00', 65, NULL),
(81, 1, '2025-04-13', '19:00:00', 85, NULL),
(82, 9, '2025-04-14', '06:00:00', 119, NULL),
(83, 1, '2025-04-14', '17:00:00', 69, NULL),
(84, 2, '2025-04-13', '07:30:00', 180, NULL),
(85, 9, '2025-04-12', '19:00:00', 155, NULL),
(86, 10, '2025-04-16', '08:00:00', 119, NULL),
(87, 10, '2025-04-17', '08:00:00', 79, NULL),
(88, 1, '2025-04-15', '22:30:00', 119, NULL),
(89, 10, '2025-04-12', '06:30:00', 102, NULL),
(90, 7, '2025-04-15', '02:00:00', 90, NULL),
(91, 1, '2025-04-16', '07:30:00', 63, NULL),
(92, 2, '2025-04-14', '10:00:00', 172, NULL),
(93, 8, '2025-04-13', '12:00:00', 164, NULL),
(94, 8, '2025-04-14', '14:30:00', 146, NULL),
(95, 7, '2025-04-16', '02:00:00', 143, NULL),
(96, 20, '2025-04-16', '08:30:00', 174, NULL),
(97, 7, '2025-04-16', '23:00:00', 122, NULL),
(98, 10, '2025-04-13', '15:00:00', 107, NULL),
(99, 2, '2025-04-14', '12:30:00', 165, NULL),
(100, 2, '2025-04-13', '02:00:00', 117, NULL),
(101, 20, '2025-04-17', '04:30:00', 52, NULL),
(102, 1, '2025-04-15', '05:00:00', 58, NULL),
(103, 22, '2025-04-15', '14:00:00', 77, NULL),
(104, 2, '2025-04-14', '15:30:00', 71, NULL),
(105, 1, '2025-04-17', '09:30:00', 151, NULL),
(106, 1, '2025-04-16', '16:00:00', 83, NULL),
(107, 8, '2025-04-17', '03:30:00', 151, NULL),
(108, 27, '2025-04-15', '14:00:00', 138, NULL),
(109, 7, '2025-04-15', '13:00:00', 124, NULL),
(110, 1, '2025-04-17', '02:30:00', 83, NULL),
(111, 28, '2025-04-15', '17:00:00', 57, NULL),
(112, 1, '2025-04-12', '06:00:00', 177, NULL),
(113, 2, '2025-04-16', '23:30:00', 59, NULL),
(114, 2, '2025-04-16', '06:00:00', 167, NULL),
(115, 8, '2025-04-15', '06:30:00', 62, NULL),
(116, 1, '2025-04-13', '04:30:00', 166, NULL),
(117, 9, '2025-04-19', '06:30:00', 139, NULL),
(118, 8, '2025-04-16', '04:30:00', 78, NULL),
(119, 1, '2025-04-14', '02:30:00', 97, NULL),
(120, 20, '2025-04-16', '05:00:00', 79, NULL),
(121, 10, '2025-04-12', '18:00:00', 120, NULL),
(122, 22, '2025-04-17', '15:00:00', 41, NULL),
(123, 26, '2025-04-16', '12:30:00', 151, NULL),
(124, 22, '2025-04-17', '11:00:00', 80, NULL),
(125, 20, '2025-04-16', '09:30:00', 49, NULL),
(126, 1, '2025-01-01', '06:00:00', 800, 1),
(127, 1, '2025-01-01', '08:00:00', 800, 1),
(128, 1, '2025-01-01', '10:00:00', 800, 1),
(129, 1, '2025-01-01', '12:00:00', 800, 1),
(130, 1, '2025-01-01', '14:00:00', 800, 1),
(131, 1, '2025-01-01', '16:00:00', 800, 1),
(132, 1, '2025-01-01', '18:00:00', 800, 1),
(133, 1, '2025-01-01', '20:00:00', 800, 1),
(134, 1, '2025-01-02', '18:00:00', 800, 1),
(135, 1, '2025-01-03', '06:00:00', 800, 1),
(136, 1, '2025-01-03', '08:00:00', 800, 1),
(137, 1, '2025-01-02', '06:00:00', 800, 1),
(138, 1, '2025-01-02', '08:00:00', 800, 1),
(139, 1, '2025-01-04', '06:00:00', 800, 1),
(140, 1, '2025-01-01', '06:00:00', 450, 2),
(141, 1, '2025-01-01', '06:00:00', 800, 1),
(142, 1, '2025-01-02', '09:00:00', 800, 1),
(143, 1, '2025-01-02', '11:00:00', 800, 1),
(144, 1, '2025-01-02', '13:00:00', 800, 1),
(145, 1, '2025-01-02', '15:00:00', 800, 1),
(146, 1, '2025-01-04', '06:00:00', 450, 2),
(147, 1, '2025-01-04', '08:00:00', 450, 2),
(148, 1, '2025-01-04', '10:00:00', 450, 2),
(149, 1, '2025-04-05', '06:00:00', 800, 1),
(150, 1, '2025-01-05', '06:00:00', 800, 1),
(151, 1, '2025-01-05', '06:00:00', 800, 1),
(152, 1, '2025-01-05', '08:00:00', 800, 1),
(153, 1, '2025-01-05', '06:00:00', 800, 1),
(154, 1, '2025-01-05', '08:00:00', 800, 1),
(155, 1, '2025-01-05', '10:00:00', 800, 1),
(156, 1, '2025-01-05', '12:00:00', 800, 1),
(157, 1, '2025-01-05', '14:00:00', 800, 1),
(158, 1, '2025-01-07', '06:00:00', 800, 1),
(159, 1, '2025-01-07', '08:00:00', 800, 1),
(160, 1, '2025-01-07', '12:00:00', 800, 1),
(161, 1, '2025-01-07', '14:00:00', 800, 1),
(162, 1, '2025-01-07', '16:00:00', 800, 1),
(163, 1, '2025-01-09', '06:00:00', 800, 1),
(164, 1, '2025-01-09', '06:00:00', 800, 1),
(165, 1, '2025-01-09', '08:00:00', 800, 1),
(166, 1, '2025-01-09', '10:00:00', 800, 1),
(167, 1, '2025-01-09', '12:00:00', 800, 1),
(168, 1, '2025-01-09', '14:00:00', 800, 1);

-- --------------------------------------------------------

--
-- Structure de la table `equipement`
--

CREATE TABLE `equipement` (
  `id_equipement` int(11) NOT NULL,
  `nom` text NOT NULL,
  `consommation_watt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `equipement`
--

INSERT INTO `equipement` (`id_equipement`, `nom`, `consommation_watt`) VALUES
(1, 'Catalyseur Primaire', 800),
(2, 'Fourneau de Sucrose', 450),
(3, 'Cryo-Frigo de Eula', 300),
(4, 'Grimoire Lumineux', 200),
(5, 'Amplificateur Anémo', 600),
(6, 'Condensateur Électro', 700),
(11, 'Diffuseur Hydro', 300),
(12, 'Coffre Pyro', 600),
(13, 'Harpistor Électro', 500),
(14, 'Nébulo-Réflecteur', 450),
(99, 'Distillateur Primaire', 400),
(100, 'Extracteur de Résine', 250);

-- --------------------------------------------------------

--
-- Structure de la table `habitat`
--

CREATE TABLE `habitat` (
  `id_habitat` int(11) NOT NULL,
  `nom` text NOT NULL,
  `id_resident` int(11) DEFAULT NULL,
  `etage` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `habitat`
--

INSERT INTO `habitat` (`id_habitat`, `nom`, `id_resident`, `etage`) VALUES
(1, 'Laboratoire de l\'Alchimie', 1, 0),
(2, 'Chambre de Lisa', 6, 3),
(3, 'Salle Cryo de Eula', 7, 1),
(7, 'Bibliothèque des Astres', 1, 2),
(8, 'Chambre de la Chevalière', 1, 1),
(9, 'Observatoire de Mona', 1, 3),
(10, 'Atelier de Cyno', 6, 2),
(11, 'Bureau d\'Alhaitham', 6, 3),
(12, 'Jardin de Nahida', 10, 1),
(13, 'Salle d\'Archives', 6, 2),
(14, 'Salon de Jean', 2, 1),
(15, 'Terrasse de Venti', 2, 3),
(16, 'Salle de Résonance', 6, 2),
(17, 'Cabinet de recherche', 6, 3),
(18, 'Serre Magique', 7, 1),
(19, 'CryoLab', 7, 2),
(20, 'Forge Anémo', 9, 3),
(21, 'Repos de Ganyu', 8, 1),
(22, 'Temple de Liyue', 1, 3),
(23, 'Auberge Wangshu', 1, 2),
(24, 'Caverne Abyssale', 1, 3),
(25, 'Studio Yanfei', 1, 2),
(26, 'Labo de Potion Xinyan', 1, 1),
(27, 'Planétarium de Mondstadt', 1, 2),
(28, 'Cabinet Alchimique', 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `habitatequipement`
--

CREATE TABLE `habitatequipement` (
  `id_habitat` int(11) NOT NULL,
  `id_equipement` int(11) NOT NULL,
  `etat` text DEFAULT 'eteint'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `habitatequipement`
--

INSERT INTO `habitatequipement` (`id_habitat`, `id_equipement`, `etat`) VALUES
(1, 1, 'allumé'),
(1, 2, 'éteint'),
(1, 3, 'allumé'),
(1, 5, 'allumé'),
(2, 4, 'allumé'),
(2, 6, 'éteint'),
(3, 2, 'éteint'),
(3, 3, 'allumé'),
(7, 1, 'eteint'),
(7, 11, 'allumé'),
(8, 3, 'eteint'),
(8, 12, 'éteint'),
(9, 1, 'eteint'),
(9, 13, 'allumé'),
(10, 11, 'allumé'),
(11, 12, 'allumé'),
(12, 14, 'éteint'),
(13, 11, 'allumé'),
(14, 14, 'allumé'),
(15, 13, 'allumé'),
(16, 12, 'éteint'),
(17, 13, 'allumé'),
(18, 14, 'éteint'),
(19, 12, 'allumé'),
(20, 11, 'allumé'),
(21, 13, 'allumé'),
(22, 14, 'allumé'),
(23, 12, 'allumé'),
(24, 13, 'éteint'),
(25, 11, 'allumé'),
(26, 14, 'éteint'),
(28, 99, 'allumé'),
(28, 100, 'éteint');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL,
  `id_resident` int(11) DEFAULT NULL,
  `id_habitat` int(11) DEFAULT NULL,
  `id_equipement` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `heure_debut` time NOT NULL,
  `heure_fin` time NOT NULL,
  `etat_validation` text DEFAULT 'en_attente'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `id_resident`, `id_habitat`, `id_equipement`, `date`, `heure_debut`, `heure_fin`, `etat_validation`) VALUES
(1, 1, 1, 1, '2025-03-31', '16:55:00', '05:55:00', 'en_attente'),
(2, 1, 1, 1, '2025-03-31', '16:55:00', '05:55:00', 'en_attente'),
(3, 1, 1, 1, '2025-03-31', '16:55:00', '05:55:00', 'en_attente'),
(4, 1, 1, 1, '2025-03-30', '09:55:00', '20:55:00', 'en_attente'),
(5, 1, 23, 12, '2025-03-26', '21:11:00', '22:12:00', 'en_attente'),
(6, 1, 1, 1, '2025-03-19', '21:51:00', '22:51:00', 'en_attente'),
(7, 1, 1, 1, '2025-03-18', '21:52:00', '22:52:00', 'en_attente'),
(8, 1, 1, 1, '2025-03-25', '21:55:00', '21:55:00', 'en_attente'),
(9, 1, 8, 12, '2025-03-31', '23:29:00', '23:29:00', 'en_attente'),
(10, 1, 8, 12, '2025-04-01', '20:28:00', '21:28:00', 'en_attente'),
(11, 1, 8, 3, '2025-04-01', '20:42:00', '22:42:00', 'en_attente'),
(12, 1, 1, 1, '2025-04-30', '20:42:00', '22:42:00', 'en_attente'),
(13, 8, 21, 13, '2025-04-02', '13:42:00', '15:42:00', 'en_attente'),
(14, 1, 1, 1, '2025-04-02', '21:16:00', '21:16:00', 'en_attente'),
(15, 1, 1, 1, '2025-01-01', '06:12:00', '09:12:00', 'en_attente'),
(16, 1, 1, 1, '2025-01-01', '06:16:00', '10:16:00', 'en_attente'),
(17, 1, 1, 1, '2025-04-02', '06:19:00', '20:19:00', 'en_attente'),
(18, 1, 1, 1, '2025-01-01', '06:23:00', '20:23:00', 'en_attente'),
(19, 1, 1, 1, '2025-01-01', '06:33:00', '20:33:00', 'en_attente'),
(20, 1, 1, 1, '2025-01-01', '06:33:00', '20:33:00', 'en_attente'),
(21, 1, 1, 1, '2025-01-01', '06:33:00', '20:33:00', 'en_attente'),
(22, 1, 1, 1, '2025-01-01', '06:33:00', '20:33:00', 'en_attente'),
(23, 1, 1, 1, '2025-01-01', '06:34:00', '22:34:00', 'en_attente'),
(24, 1, 1, 1, '2025-01-01', '06:34:00', '22:34:00', 'en_attente'),
(25, 1, 1, 1, '2025-01-01', '06:34:00', '22:34:00', 'en_attente'),
(26, 1, 1, 1, '2025-01-02', '18:38:00', '20:38:00', 'en_attente'),
(27, 1, 1, 1, '2025-01-03', '06:47:00', '10:48:00', 'en_attente'),
(28, 1, 1, 1, '2025-01-02', '06:52:00', '10:52:00', 'en_attente'),
(29, 1, 1, 1, '2025-01-02', '06:52:00', '10:52:00', 'en_attente'),
(30, 1, 1, 1, '2025-01-02', '06:52:00', '10:52:00', 'en_attente'),
(31, 1, 1, 1, '2025-01-04', '06:53:00', '08:53:00', 'en_attente'),
(32, 1, 1, 1, '2025-01-01', '06:00:00', '08:00:00', 'en_attente'),
(33, 1, 1, 1, '2025-01-01', '06:00:00', '08:00:00', 'en_attente'),
(34, 1, 1, 1, '2025-01-01', '06:03:00', '08:03:00', 'en_attente'),
(35, 1, 1, 2, '2025-01-01', '06:04:00', '08:04:00', 'en_attente'),
(36, 1, 1, 1, '2025-01-01', '06:06:00', '08:06:00', 'en_attente'),
(37, 1, 1, 1, '2025-01-02', '09:35:00', '16:35:00', 'en_attente'),
(38, 1, 1, 2, '2025-01-04', '06:52:00', '12:52:00', 'en_attente'),
(39, 1, 1, 1, '2025-04-05', '06:00:00', '07:00:00', 'en_attente'),
(40, 1, 1, 1, '2025-01-05', '06:00:00', '07:01:00', 'en_attente'),
(41, 1, 1, 1, '2025-01-05', '06:01:00', '10:01:00', 'en_attente'),
(42, 1, 1, 1, '2025-01-05', '06:03:00', '16:03:00', 'en_attente'),
(43, 1, 1, 1, '2025-01-07', '06:26:00', '10:26:00', 'en_attente'),
(44, 1, 1, 1, '2025-01-07', '12:26:00', '18:26:00', 'en_attente'),
(45, 1, 1, 1, '2025-01-09', '06:12:00', '08:12:00', 'en_attente'),
(46, 1, 1, 1, '2025-01-09', '06:12:00', '16:12:00', 'en_attente');

-- --------------------------------------------------------

--
-- Structure de la table `resident`
--

CREATE TABLE `resident` (
  `id_resident` int(11) NOT NULL,
  `nom` text NOT NULL,
  `prenom` text NOT NULL,
  `email` text NOT NULL,
  `mot_de_passe` text NOT NULL,
  `telephone` text DEFAULT NULL,
  `eco_coin` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `resident`
--

INSERT INTO `resident` (`id_resident`, `nom`, `prenom`, `email`, `mot_de_passe`, `telephone`, `eco_coin`) VALUES
(1, 'Willien', 'HU', 'huwillien56@gmail.com', '$2y$10$484q2g7EHN2oNHsrMt9yteb79CNmopUe.ejEB.JAslrtc.yL1Z2ea', NULL, 510),
(2, 'Albedo', 'Kreideprinz', 'albedo@mondstadt.gn', '$2y$10$rZ8AEKMj.YtW8N.3IDgJjOBepxtt.KwXvKJzG.C1yGXnA2DBnUL7u', '123456789', 20),
(6, 'Lisa', 'Minci', 'lisa@sumeru.ac', '$2y$10$AKm5J7vO6CCPVc/NFoZbI.QDnTYaYOZCEAl7ToWTZ7msydhL2HInC', '0605040302', 80),
(7, 'Eula', 'Lawrence', 'eula@favonius.kn', '$2y$10$fOTydUkXLN.1rW0iPzZ8cuYpAxkGiV9T8Uk1oqAL0QyF4n7Oi4SWe', '0708091011', 150),
(8, 'Klee', 'Sparkknight', 'klee@knights.mn', 'boom123', '0102030405', 70),
(9, 'Diluc', 'Ragnvindr', 'diluc@dawn.wn', 'vinber123', '0504030201', 120),
(10, 'Kuya', 'Kuyakii', 'huwillie', '$2y$10$CaHKN6VIR2.HelDX64OGEupOXM3MewqbVkWfga/82YMcSeLXYHG9K', NULL, 0),
(20, 'Kuya', 'Kuyakii', '123', '$2y$10$m3wGjg7am/ErZIVjSfN2EeJHGwrZUv5In6a9zzvazJg5hosUuHuXS', NULL, 0),
(29, 'Kuya', 'Kuyakii', 'huwillien57@gmail.com', '$2y$10$Na63Uicv8vEZ0yGu2fZnweGNPHxawMBRFf5vMVUS3cLdjsxmJnfdm', 'androidx.appcompat.widget.AppCompatEditText{f276477 VFED..CL. ........ 394,55-812,118 #7f0a018b app:id/phone aid=1073741829}', 0),
(30, 'Momo', 'Momo', 'momo@momo.momo', '$2y$10$xkfdCVeyb1nnpVrZhjBYpO98/NJ/jOVx3sTo6EG7OXLzqkU6t4wQa', '1', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `consommation`
--
ALTER TABLE `consommation`
  ADD PRIMARY KEY (`id_consommation`),
  ADD KEY `id_habitat` (`id_habitat`);

--
-- Index pour la table `equipement`
--
ALTER TABLE `equipement`
  ADD PRIMARY KEY (`id_equipement`);

--
-- Index pour la table `habitat`
--
ALTER TABLE `habitat`
  ADD PRIMARY KEY (`id_habitat`),
  ADD KEY `fk_habitat_resident` (`id_resident`);

--
-- Index pour la table `habitatequipement`
--
ALTER TABLE `habitatequipement`
  ADD PRIMARY KEY (`id_habitat`,`id_equipement`),
  ADD KEY `id_equipement` (`id_equipement`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reservation`),
  ADD KEY `id_resident` (`id_resident`),
  ADD KEY `id_equipement` (`id_equipement`),
  ADD KEY `fk_reservation_habitat` (`id_habitat`);

--
-- Index pour la table `resident`
--
ALTER TABLE `resident`
  ADD PRIMARY KEY (`id_resident`),
  ADD UNIQUE KEY `email` (`email`) USING HASH;

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `consommation`
--
ALTER TABLE `consommation`
  MODIFY `id_consommation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=169;

--
-- AUTO_INCREMENT pour la table `equipement`
--
ALTER TABLE `equipement`
  MODIFY `id_equipement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT pour la table `habitat`
--
ALTER TABLE `habitat`
  MODIFY `id_habitat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT pour la table `resident`
--
ALTER TABLE `resident`
  MODIFY `id_resident` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `consommation`
--
ALTER TABLE `consommation`
  ADD CONSTRAINT `consommation_ibfk_1` FOREIGN KEY (`id_habitat`) REFERENCES `habitat` (`id_habitat`);

--
-- Contraintes pour la table `habitat`
--
ALTER TABLE `habitat`
  ADD CONSTRAINT `fk_habitat_resident` FOREIGN KEY (`id_resident`) REFERENCES `resident` (`id_resident`);

--
-- Contraintes pour la table `habitatequipement`
--
ALTER TABLE `habitatequipement`
  ADD CONSTRAINT `habitatequipement_ibfk_1` FOREIGN KEY (`id_habitat`) REFERENCES `habitat` (`id_habitat`),
  ADD CONSTRAINT `habitatequipement_ibfk_2` FOREIGN KEY (`id_equipement`) REFERENCES `equipement` (`id_equipement`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_reservation_habitat` FOREIGN KEY (`id_habitat`) REFERENCES `habitat` (`id_habitat`),
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`id_resident`) REFERENCES `resident` (`id_resident`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`id_equipement`) REFERENCES `equipement` (`id_equipement`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
