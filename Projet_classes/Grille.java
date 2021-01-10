import java.io.Serializable ;

/**
 * La classe Grille contient un tableau a deux dimension
 * 
 */
class Grille implements Serializable {
    //Le plateau vertical de jeu:
    private Case [] [] grille;
    //colonnes,lignes et nombre de pions :
    private int L;
    private int C;
    private int nb_pions;
    public Case [][] getGrille(){return this.grille;}

    /**
     * Un constructeur surchargé 
     * @param L Le nombre de lignes 
     * @param C Le nombre de colonnes
     */

    Grille(int l, int c  ){
        this.L=l;
        this.C=c;
        grille = new Case [l][c];
        this.nb_pions= c*l;

    }

    /**
     * Renvoi le nombre de ligne 
     */
    public int getL(){return this.L;}

    /**
     * Renvoi le nombre de colonne
     */
    public int getC(){return this.C;}

    /**
     * Initialise toutes les case a Vide
     * @param g La grille a modifier
     */
    public static void  Initialiser( Grille g){
        for(int i=0;i<g.grille.length;i++){
            for(int j=0;j<g.grille[0].length;j++){

                g.grille[i][j]=Case.VIDE;

            }
        }
    }

    /**
     *  Affiche la grille passée en parametre 
     * @param g La grille a afficher 
     */

    public static void Afficher(Grille g){
        //laisser d'espace entre les les lignes 
        System.out.println();
        // afficher avec deux boucles for each  
        System.out.print(" ");
        for(int i = 1; i<= g.grille[0].length*2+1;i++){
            System.out.print("_");

        }
        System.out.println();
        for(Case [] l :g.grille){
            System.out.print(" |");
            for(Case c : l){

                if(c.equals(Case.VIDE)){
                    System.out.print('.');

                } else { if (c.equals(Case.X)){
                        System.out.print('X');}

                    else { if (c.equals(Case.O)){
                            System.out.print('O');

                        }
                    }
                }
                System.out.print('|');

            }
            System.out.println();
        }
        System.out.print(" ");
        for(int i = 1; i<= g.grille[0].length*2+1;i++){
            System.out.print("-");
        }
        System.out.println();
        System.out.print(" ");
        for(int i = 1; i<= g.grille[0].length;i++){
            System.out.print("=" + i);

        }
        System.out.println("=");
    }

    /**
     * Changer une case de la grille
     */
    public static  void  setCase_grille(Grille g, int ligne ,int colonne,Case c ){
        g.grille [ligne][colonne]=c;

    }

    /**
     * Fait une copie profonde de la grille
     * @g La grille a copié 
     */
    public static Grille copy(Grille g){
        Grille g2 = new Grille(6,7);
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                if (g.getGrille()[i][j]==Case.X){g2.setCase_grille(g2,i,j,Case.X);} else {
                    if (g.getGrille()[i][j]==Case.O){g2.setCase_grille(g2,i,j,Case.O);}

                    else {g2.setCase_grille(g2,i,j,Case.VIDE);}
                }
            }
        }
        return g2;
    }

    /**
     * Verifie si la colonne est pleine
     * @param g la grille ou vérifier si la colonne est pleine
     * @param i Le numéro de la colonne a verifier
     */
    public static boolean ColonnePleine(Grille g,int i){
        return (!(g.getGrille()[0][i-1] == Case.VIDE));

    }
}

