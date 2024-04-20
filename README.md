# Projet de Détermination de Satisfaisabilité SAT via Graphes et Composantes Fortement Connexes

Ce projet vise à résoudre des instances du problème de satisfaisabilité (SAT) en les modélisant sous forme de graphes et en utilisant des algorithmes pour identifier les composantes fortement connexes. La satisfaisabilité SAT est un problème classique en informatique théorique consistant à déterminer si une expression booléenne peut être satisfaite par une affectation de valeurs aux variables.

## Méthodologie
  - On transforme une instance SAT en un graphe orienté.
  - On identifie les composantes fortement connexes du graphe.
  - Si une composante contient un littéral et son opposé, alors l'instance SAT est insatisfiable.

## Utilisation
  - Cloner le dépôt : Utilisez la commande git clone pour obtenir une copie locale du dépôt.
  - Ouvrir le dépôt dans son environnement de développement préféré.
  - Exécuter le programme (Tester.java)