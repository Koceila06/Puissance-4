class Grille {
    //Le plateau vertical de jeu:
    private Case [] [] grille;
    public Case [][] getGrille(){return this.grille;}
    //colonnes,lignes et nombre de pions :
    private int L;
    private int C;


    private int nb_pions;


    Grille(int l, int c  ){
        this.L=l;
        this.C=c;
        grille = new Case [l][c];
        this.nb_pions= c*l;


    }


    public static void  Initialiser( Grille g){
        for(int i=0;i<g.grille.length;i++){
            for(int j=0;j<g.grille[0].length;j++){


                g.grille[i][j]=Case.VIDE;


            }


        }


    }


    public static void Afficher(Grille g){
        //laisser d'espace entre les les lignes 
        System.out.println();
        // afficher avec deux boucles for each  
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
        System.out.print('=');
        for(int i = 1; i<= g.grille[0].length;i++){
            System.out.print("=" + i);

        }
        System.out.println("==");
    }

    public static  void  setCase_grille(Grille g, int ligne ,int colonne,Case c ){
        g.grille [ligne][colonne]=c;

    }


    public static void main(String [] args){


        Grille g = new Grille(6,7);
        Initialiser(g);
        g.grille[1][2]=Case.O;
        g.grille[3][2]=Case.X;
        Afficher(g);


    }
}