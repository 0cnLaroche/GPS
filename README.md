# Système de guidage routier

## Instructions pour importer dans eclipse
1. cloner dans le workspace d'eclipse (meme place que vos autres projets)
2. Dans eclipse, creer un nouveau projet nomme exactement "SystemGuidageRoutier"
3. Le projet devrait s'importer automatiquement

## Fonctionnement

Le système sera composé de deux interfaces: l'interface utilisateur et l'interface simulation.

### Interface utilisateur
  
L'interface utilisateur comprendra uniquement la vu et contrôles qui seront visible pour un utilisateur. Un cadre affichera le graph (la carte) et le chemin choisi devra être mis en évidence par une différente couleur ou autre. Un cadre affichera une flèche indiquant la direction que l'utilisateur doit emprunter, par exemple tout droit, à gauche ou à droite. Un autre cadre affichera le temps restant pour arriver à destination. Un cadre servira à avertir un évènement, sa localisation et informer la recherche d'un nouveau chemin. Un signal sonore annoncera un évènement.

#### Classes impliqués
 - JFrame
 - JLabel
 - JPanel
 - Canvas

### Interface simulation  

L'interface simulation permettera de gérer la simulation, soit le déclanchement d'accidents et de traffic. Deux boutons permettront de simuler un nouvel évènement au hasard sur le chemin qui est présentement emprunté par l'utilisateur.

#### Classes impliqués
 - JFrame
 - JLabel
 - JButton
 
### Graph

Les points et les chemins seront identifiés par des lettres. Après 26 itérations, on ajoute une lettre ex: a,..., y, z, aa, ab, ...
