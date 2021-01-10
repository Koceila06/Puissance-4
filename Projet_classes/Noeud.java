import java.util.ArrayList;
/**
 * La classe Noeud contient une grille , un score et une liste de ses fils
 * 
 */
class Noeud {
    private Grille grille;
    private int score;
    private ArrayList<Noeud> fils;
    /**
     * Un constructeur surchargé 
     * @param g La grille
     * @param scr Le score
     */
    Noeud(Grille g,int scr){
        this.grille=g;
        this.fils=new ArrayList();
        this.score=scr;
    }

    public boolean getFeuille(){return this.fils==null;}

    /**
     * Un constructeur 
     * @param g La grille
     */
    Noeud(Grille g){
        this.grille=g;
        this.fils=new ArrayList();
        this.score=-999999990;
    }

     /**
     * Renvoi la grille
     */
     public Grille getGrille_Noeud(){
        return this.grille;
    }

     /**
     * ajoute un fils
     */
    public void Add_Fils(Noeud node){
        this.fils.add(node);
    }

    /**
     * Renvoi la liste des fils 
     */
    public ArrayList<Noeud> getFils(){
        return this.fils;
    }

    /**
     * modifie le score
     */
    public void setScore(int a){
        this.score=a;
    }

    /**
     * Renvoi le score
     */
    public int getScore(){return this.score;}
}