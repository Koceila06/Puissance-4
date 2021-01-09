
/**
 * Représente un joueur 
 */
class Joueur{
private String nom;
private Case type;
private boolean IA;
/**
 * Un constructeur surchargé (Gumain vs IA)
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
/**
 * Constructeur surchargé (Humain vs Humain)
 * @param nom Nom du joueur 
 * @param t Type du Joueur( Couleur)
 */
Joueur(String nom,Case t){
this.type=t;
this.nom=nom;
this.IA=true;
// le nombre de pions d'un joueur est le le nombre de case totale qu'on a initialiser dans la 
//la class grille a colonne*ligne et on devise sur 2
}
public void set_IA(boolean ia){this.IA=ia;}
public void setType(Case c){this.type=c;}
/**
 * Retourne la couleur/caractere du joueur
 */
public Case  getType(){return this.type;}
/**
 * Retourne le nom du joueur 
 */
public String getNom(){return this.nom;}
/**
 * Retourne vrai si le Joueur est une Machine 
 */

public boolean get_IA(){return this.IA;}

}