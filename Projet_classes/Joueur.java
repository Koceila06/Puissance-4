
/**
 * Représente un joueur 
 */
class Joueur{
private String nom;
private Case type;
private boolean IA;
/**
 * Un constructeur surchargé 
 * @param nom Le nom du joueur 
 * @param t La couleur/caractere du joueur 
 */
Joueur(String nom,Case t,boolean ia){
this.type=t;
this.nom=nom;
this.IA=ia;
// le nombre de pions d'un joueur est le le nombre de case totale qu'on a initialiser dans la 
//la class grille a colonne*ligne et on devise sur 2


}
Joueur(String nom,Case t){
this.type=t;
this.nom=nom;

// le nombre de pions d'un joueur est le le nombre de case totale qu'on a initialiser dans la 
//la class grille a colonne*ligne et on devise sur 2


}
/**
 * Retourne la couleur/caractere du joueur
 */
public Case  getType(){return this.type;}
/**
 * Retourne le nom du joueur 
 */
public String getNom(){return this.nom;}


public boolean get_IA(){return this.IA;}

}