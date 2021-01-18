# Jeu puissance 4


## Présentation du projet
L’objectif principal de ce projet est de développer nos compétences en programmation et de mettre en
pratique les différentes notions théoriques acquises en programmation orienté objet ainsi qu’en
algorithmique et structures de données.
Le projet consiste à implémenter différentes versions du jeu de puissance 4 : la version basique
permettant d’affronter deux joueurs humains et la version permettant à un joueur humain d’affronter
un ordinateur (IA). La qualité de l’IA est déterminée en fonction de la stratégie (algorithme) adoptée.
## Règles du jeu 
Le but du jeu est d'aligner une suite de 4 pions de même couleur sur une grille comptant 6 rangées et 7
colonnes. Tour à tour les deux joueurs placent un
pion dans la colonne de leur choix, le pion coulisse alors jusqu'à la position la plus basse possible dans
la colonne à la suite de quoi c'est à l'adversaire de jouer. Le vainqueur est le joueur qui réalise le
premier un alignement (horizontal, vertical ou diagonal) consécutif d'au moins quatre pions de sa
couleur. Si, alors que toutes les cases de la grille de jeu sont remplies, aucun des deux joueurs n'a
réalisé un tel alignement, la partie est déclarée nulle
## Comment jouer ? 
Pour jouer,il faut chercher la classe "Partie" et l'exécuter,il y'a deux mode de jeu :
<br/>
**Humain vs Humain :** 
<br/>
Les joueurs ont la possibilité de choisir la taille de la grille( elle doit impérativement être supérieure à 4 pour les lignes et les colonnes, sinon ça contredirait le principe du jeu), le choix de colonne se fait selon la volonté du joueur à qui est le tour.
<br/>
**Humain vs IA :**
Il y a trois niveaux de difficulté de l'IA:
<br/>
1_IA_simple :
<br/>
De même que pour humain vs humain, le joueur peut choisir la taille de la grille, le choix de colonne se fait aléatoirement pour l'IA.
<br/>
2_IA_max :
<br/>
la taille de la grille est fixée( 7 colonnes et 6 lignes), l'IA simule le jeu et attribue un score pour chaque situation tout en dessinant l'arbre du jeu selon la profondeur souhaitée (j'ai choisie la profondeur 5 pour que l'exécution ne soit pas trop longue), l'IA remonte à chaque fois le meilleur score( en utilisant la récurrence) et permet ainsi d'indiquer le meilleur coup à jouer.
<br/>
3_IA_Alpha-Beta :
<br/>
la taille de la grille est fixée( 7 colonnes et 6 lignes), L'IA choisie la colonne à jouer presque de la même façon que IA max mais en réduisant le nombre de nœuds évalué, permettant ainsi pouvoir choisir une profondeur plus grande et en conséquent avoir une IA plus forte.
<br/>
Dans les deux modes de jeu, la partie est automatiquement enregistrée, permettant ainsi aux joueurs de continuer la partie après l'avoir quitté s'ils le souhaitent, sinon rejouer une nouvelle partie.
## Auteur
Koceila Kemiche
