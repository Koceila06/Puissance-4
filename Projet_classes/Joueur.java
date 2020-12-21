class Joueur{
    private String nom;
    private Case type;
    private int nb_pions;
    Joueur(String nom, int pions,Case t){
        this.type=t;
        this.nom=nom;
        // le nombre de pions d'un joueur est le le nombre de case totale qu'on a initialiser dans la 
        //la class grille a colonne*ligne et on devise sur 2
        this.nb_pions=pions;


    }

    public Case  getType(){return this.type;}

    public String getNom(){return this.nom;}
}