# Système de guidage routier

## Fonctionnement

Le système sera composé de deux interfaces: l'interface utilisateur et l'interface simulation.

### Interface utilisateur
  
L'interface utilisateur comprendra uniquement la vu et contrôles qui seront visible pour un utilisateur. Un cadre affichera le graph (la carte). Un cadre affichera une flèche indiquant la direction que l'utilisateur doit emprunter, par exemple tout droit, à gauche ou à droite. Un autre cadre affichera le temps restant pour arriver à destination. Un cadre servira à avertir un évènement et informer la recherche d'un nouveau chemin.

### Interface simulation  

L'interface simulation permettera de gérer la simulation, soit le déclanchement d'accidents et de traffic. Deux boutons permettront de simuler un nouvel évènement au hasard sur le chemin qui est présentement emprunté par l'utilisateur.

### Graph

Les points et les chemins seront identifiés par des lettres. Après 26 itérations, on ajoute une lettre ex: a,..., y, z, aa, ab, ...
