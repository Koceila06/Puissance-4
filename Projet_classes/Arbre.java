/**
 * Arbre du jeu
 */
class Arbre{
Noeud racine;
/**
 * Constructeur surchargé
 * @param root La racine de l'arbre
 */
Arbre(Noeud root){
this.racine=root;
}
/**
 * Renvoie la racine de l'arbre
 */
public Noeud getRoot(){return this.racine;}
}