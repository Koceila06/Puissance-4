class Joueur{
    private String nom;
    private Case type;
  
    Joueur(String nom,Case t){
        this.type=t;
        this.nom=nom;
        // le nombre de pions d'un joueur est le le nombre de case totale qu'on a initialiser dans la 
        //la class grille a colonne*ligne et on devise sur 2
       


    }

    public Case  getType(){return this.type;}

    public String getNom(){return this.nom;}
}