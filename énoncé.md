###Objectifs relativement au cours

L’objectif principal du devoir 2 est de maitriser la programmation par événements via un
système à base d’objets intégrant les concepts du cours. Les concepts objets doivent être
considérés et appliqués pour développer le système considéré dans ce devoir.
Un sous objectif se rajoute à l’objectif principal : évaluer la qualité du travail qui sera
produit en mettant en application les connaissances du cours relativement a la qualité de
la programmation objet.

##II. Contexte du devoir

Nous considérons le contexte des systèmes des transports intelligents relativement aux
systèmes de guidage. (Voir des informations sur Moodle pour plus de détails).
Parmi les fonctionnalités des systèmes de guidage, nous considérons la recherche du
meilleur chemin en fonction de l’état du trafic et des accidents. Dans ce contexte, les
véhicules sont équipés par des GPS, des moyens de communications et des capacités de
traitements d’informations. Les GPS sont munis d’écrans dont une interface qui fournit
aux conducteurs des informations sur le trafic ou des recommandations sur les prochaines
directions à prendre à partir d’un moment particulier. Par exemple lorsqu’il y a du trafic
sur une artère qui devient congestionnée ou la présence d’un accident, le conducteur est
informé par l’écran du GPS. Suite aux informations, des recommandations sont afficher
pour garantir les meilleurs chemins à prendre à partir du moment présent. 
2
Figure 1 : Exemple d’une image GPS indiquant une direction
Fonctionnement du système à développer dans ce devoir
Les concepts objets doivent être considérés et appliqués pour développer le système de
guidage décrit précédemment.

Le fonctionnement de ce système se décrit comme suit : Un réseau routier est fixé et les
véhicules se déplacent à l’intérieur de ce réseau. Le réseau routier emprunté par le
conducteur est défini par un ensemble d’artères et d’intersections. Les événements qui
arrivent sur le réseau routier peuvent être identifiés et visualisés sur le réseau routier.
Chaque véhicule circule dans ce réseau reçoit des informations sur l’état de ce réseau.
Lors d’un déplacement entre les points A et B (sommets du réseau), le conducteur du
véhicule concerné emprunte le plus court chemin. Entre les points A et B, le conducteur
sera informé régulièrement via l’écran de son GPS et dès qu’il y a un accident il lui sera
communiqué. Les nouvelles directions à prendre s’il y a lieu seront affichées par le GPS.
Le système de calcul du plus court chemin est activé au début du déplacement. Il sera
réactivé à chaque fois que les nouvelles informations du GPS affectent les résultats des
calculs précédents. Les nouvelles directions doivent être basées sur les meilleurs chemins
calculés. 
3

##III. Travail à faire dans un environnement objet

#Phase 1 : Analyse et Spécifications: 20 pts

On demande de faire une analyse du système à développer en spécifiant son fonctionnement.
Rédiger en quelques lignes le fonctionnement du système à développer. On peut
considérer à ce niveau des composantes potentielles de la librairie jdk qui permettent de répondre
au mieux aux besoins de fonctionnement du système de guidage et son environnement. Présenter
votre solution globale sous forme d’un graphe (avec ou sans un outil comme visio) pour les
différents types d’interfaces que vous avez identifiées. Les interfaces qui permettent de faire des
interactions (affichages et entrées au besoin) à concevoir concernent le réseau routier et l’écran du
GPS ainsi que toute autre composante nécessaire pour l’initialisation et le fonctionnement de
votre système.

#Phase 2 : Conception de la solution globale : 25 pts

Une fois que le quoi, (ce qui est demandé de faire) est clair, il faut procéder par les étapes
de conception : 1) Décomposition du problème : une solution pour le fonctionnement de
tout le système (routes, écran GPS, … etc). Justifier les choix de votre conception. 2)
Conception selon les concepts objets : Ensemble de classes nécessaires pour définir la
solution globale.
Pour chaque sous système de la décomposition il est important d’identifier les classes
(incluant leurs attributs, méthodes et algorithmes) et leurs relations. Vous pouvez utiliser
un outil comme UML ou Visio pour présenter les classes de votre solution et leurs
relations.
Une fois les méthodes des classes déterminées, il faut identifier le ou les algorithmes
(présentant des solutions logiques) à écrire. Parmi ces algorithmes, il faut expliquer la
manière dont se fait la recherche du meilleur chemin. Vous pouvez écrire votre
algorithme ou considérez un algorithme existant pour trouver le meilleur chemin dans un
graphe (réseau) comme l’algorithme de Dijkstra. Dans tous les cas, il faut considérer les
contraintes de votre sujet qui décrit des possibilités de retards à cause d’accidents ou de
trafics denses sur certains tronçons des routes.
Phase 3 : Codage de votre solution: 30 pts
Codez la solution conçue incluant les classes des deux phases précédentes. On demande
de considérer l’ensemble du matériel fournit en cours relativement a la qualité du code et
particulièrement sa documentation. 
4
Pour ce qui concerne l’algorithme de Dijkstra si vous le choisissez comme algorithme,
vous avez deux possibilités. 1) récupérer un code existant ou 2) coder vous-même. Ce
choix doit être clairement présenté dans votre travail. Dans tous les cas, il faut considérer
les particularités de l’énoncé relativement aux meilleurs chemins. Si vous optez pour
une réutilisation, il faut fournir la source et procéder à une révision du code avant de
l’intégrer à votre travail et de faire les modifications nécessaires. Pour ce cas, il faut
ajouter le travail de révision à la phase 4.

#Phase 4 : Révision de votre travail et qualité du travail d’équipe : 25 pts

1) Fonctionnement du code : Faire plusieurs jeux de tests pour faire varier les
évènements de la route (congestion de tronçons et accidents). Montrer pour chaque jeu
d’essai les interfaces associées (le réseau routier et la visualisation de l’interface du GPS).
Détaillez le fonctionnement de chaque test en décrivant la situation à partir du point de
départ avec la destination associée et le ou les événements ainsi que les réactions du GPS.
2) Révision de votre code : En considérant les notes du cours relativement à la qualité, on
demande de préparer une fiche de révision. Procéder alors à l’analyse de votre travail
(selon les critères vus en cours). Travaillez sur un tableau des analyses que vous préparez.
Montrez alors comment vous avez apporté des améliorations en commençant par la
première révision.
3) Qualité du travail d’équipe : Il s’agit d’un travail d’équipes. Chaque équipe comporte
quatre étudiants) : Les informations suivantes sont requises dans le rapport final. 1)
Structure de l’équipe (avec ou sans un responsable qui gère le temps des réunions et
livrables, etc), 2) organisation du travail, 3) modalité des partages du travail et des
connaissances, etc.
4) Rédaction d’un rapport global : Rédiger les résultats de chaque phase dans un
document Word bien structuré en adoptant les titres des phases du devoir et les tâches
associées. Respectez ls numérotations de l’énoncé au niveau de votre rapport. En plus de
la rédaction de votre texte expliquant chaque phase, il faut intégrer des graphes (UML,
Visio) et images écran pour illustrer le fonctionnement de système.
Livrables et modalités
• L’énoncé est disponible le 20 février
• Date de remise du travail : le 9 Avril (Aucun retard ne sera toléré).
• Consultation obligatoire à la fin de la phase 2 (estimer la semaine 3 après le
début). Les rencontres d’équipe se font sur rendez-vous avec la professeure durant
les semaines du 12 et 19 mars. Le travail se poursuit une fois que les deux
premières phases s’avèrent être dans la bonne direction.
• Il s’agit d’un travail d’équipe de quatre étudiants. 
5
• Remettre le travail composé par : le code source, les classes et le document Word
(compte rendu). Mettre tout dans un dossier que vous compressez en .zip (pas
rar). Le dossier doit porter le nom d’un membre de l’équipe.
• Envoyer le travail au plus tard le jour de la date limite à : benyahia@uqo.ca 
