
/**
 * Enumération des 3 valeurs possibles d'une case de la grille du jeu
 */
enum Case {
    //Les couleurs peuvent servir pour l'interface
X("Rouge"),O("Jaune"),VIDE;
String Couleur;
Case(String couleur){
this.Couleur=couleur;
}
Case(){

}
}