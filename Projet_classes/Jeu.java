import java.util.Scanner;
import java.io.Serializable ;    
class Jeu implements Serializable {

    private  int Max;
    //Max est le nombre de coup a aligner pour gagner
    private Joueur j1;
    private Joueur j2;
    private Grille g;
    Jeu(Joueur j1,Joueur j2, Grille g,int max){
        this.j1=j1;
        this.j2=j2;
        this.g=g;
        this.Max=max;

    }

    public static boolean  Jouer(Grille g,int colonne,Joueur j){
        // on commence par verifeir que le num de colonne est valide 
        if (colonne >= g.getGrille()[0].length || colonne <0){ return false ;}
        //si la colonne est pleine ,le coup n'est pas valide :
        //on verfie que la premiere case de la ligne" celle qui se trouve en plus haut " 
        //n'est pas vide
        if (g.getGrille() [0][colonne]!= Case.VIDE){
            return false;

        }
        //on parcour la colonne en partant du bas (ligne.lenght-1) 
        //  jusq'a trouver une case VIDE
        int ligne = g.getGrille().length-1;
        while(g.getGrille()[ligne ][colonne]!=Case.VIDE){
            ligne--;

        }
        // on remplie la premier case vide 
        g.setCase_grille(g,ligne,colonne,j.getType());

        return true;
    }

    public static void demandeEtJoue(Grille g, Joueur j){
        Scanner s = new Scanner(System.in);
        boolean valide ;
        // On commence par d'abord lire le nombre et puis verifier sa validité
        do {
            System.out.println(j.getNom()+ " : Entrez un numéro d'une colonne ");
            int colonne = s.nextInt();
            // Pour les joueur ,la premiere case de la  grille du jeu commence par 1.
            //Mais les indince en java commence par 0 , donc on soustrait "1" du nombre entré.
            colonne--;
            valide=Jouer(g,colonne,j);
            if (!valide){System.out.println(" le coup n'est valide");}

        }
        while(!valide);
        // Le coup est valide : on affiche la grille!

    }

    public static Grille copy(Grille g){

        Grille g2 = new Grille(g.getL(),g.getC());
        for(int i=g.getGrille().length-1;i>=0;i--){

            for(int j=0;j<g.getGrille()[i].length;j++){
                if (g.getGrille()[i][j]==Case.X){g2.setCase_grille(g2,i,j,Case.X);} else {
                    if (g.getGrille()[i][j]==Case.O){g2.setCase_grille(g2,i,j,Case.O);}

                    else {g2.setCase_grille(g2,i,j,Case.VIDE);}

                }
            }

        }
        return g2;
    }

    public static int Compte(Grille g,int ligneDepart,int colonneDepart,int dirctLigne,int dirColonne){
        int compteur =0;
        int ligne=ligneDepart;
        int colonne=colonneDepart;
        // On part de la case de depart, et on parcours la grille dans la direction donnée
        //On implémente le compteur tant que on trouve des pions du meme joueur dans la direction donnée
        while(
        ligne >=0 && 
        ligne <g.getGrille().length 
        && colonne>=0 
        && colonne<g.getGrille()[ligne].length && g.getGrille()[ligne ][colonne] == g.getGrille()[ligneDepart][colonneDepart]){
            //On implémente le compteur et on avance dans la direction donnéé
            compteur++;
            ligne = ligne + dirctLigne;
            colonne= colonne + dirColonne;
            //

        }

        return compteur;
    }

    public static boolean Gagnant(Grille g, Joueur j,int Max){
        for(int ligne =0;ligne<g.getGrille().length;ligne++){
            for(int colonne =0;colonne < g.getGrille()[ligne].length;colonne++){
                Case courant= g.getGrille()[ligne][colonne];
                if (courant.equals(j.getType())){

                    if (
                    // Parcour en diagonal vers le haut  droite
                    (ligne >= 3 && colonne <= g.getGrille ()[ligne].length -4 && Compte(g,ligne,colonne,-1,1) >= Max ) || 
                        // Parcour horizontal vers la droite 
                    (colonne <= g.getGrille ()[ligne].length -4 && Compte(g,ligne,colonne,0,1) >= Max )||
                        // Parcour en diagonal vers le bas  droite
                    (ligne <= g.getGrille ().length - 4 && colonne <= g.getGrille ()[ligne].length -4 && Compte(g,ligne,colonne,1,1) >= Max )||
                        // Parcour vertical vers le bas
                    (ligne <= g.getGrille ().length - 4 && Compte(g,ligne,colonne,1,0) >= Max )){
                        return true;
                    }
                }

            }
        }
        return false;
    }
    // Une methode qui teste si la grille est pleine 
    public static boolean Pleine(Grille g){

        //Si on trouve une case VIDE sur la premiere ligne ,la grille n'est pas pleine
        //On utilise une boucle for each 
        for(Case courant :g.getGrille()[0]){
            if (courant.equals(Case.VIDE)){return false ;}

        }

        return true;

    }

    public static void Play (){

 
 

        Scanner s = new Scanner(System.in) ;
        int L , C ;
        do{
        System.out.println("Entrez le nombre de Ligne") ;
        L = s.nextInt() ;
        System.out.println("Entrez le nombre de Colonne") ;
        C = s.nextInt() ;
        if (L < 4) {System.out.println ("Ligne doit etre superieure a 4");
         System.out.println("Entrez le nombre de Ligne") ;
        L = s.nextInt() ;
        }
        if (C < 4) {System.out.println ("Colonne doit etre superieure a 4");
        System.out.println("Entrez le nombre de Colonne") ;
        C = s.nextInt() ;}
    }while(L < 4 || C < 4);
        Grille grille = new Grille(L,C);
        grille.Initialiser(grille);
        Joueur j1 = new Joueur("Koceila",Case.X);
        Joueur j2 = new Joueur("Hamza",Case.O);
       
        Jeu partie = new Jeu(j1,j2,grille,4);

        boolean gagne ;
        //Une variable temporaire pour alterner les joueurs
        Joueur j=j1; 
        Grille tmp  = Charge.load("SAVE");
        if (tmp != null){

            if (!(Pleine(tmp) || Gagnant(tmp,j1,4) || Gagnant(tmp,j2,4)) ){

                System.out.println("Voulez vous Continuer La Partie Précédente.");
                System.out.println("1. Continuer");
                System.out.println("2. Nouvelle partie");
                int nbr= s.nextInt();
                while( nbr!=1 && nbr != 2){

                    System.out.println("Entrer un nombre valide");
                    nbr=s.nextInt();
                }
                if (nbr==1) {
                    grille=tmp;
                    int cpt_j1=0;
                    int cpt_j2=0;
                    System.out.print(tmp.getL()+"*"+tmp.getC()+"  ");
                    for (int l = tmp.getGrille().length-1;l>=0; l--){
                        for(int c =0 ; c < tmp.getGrille()[l].length;c++ ) {
                            if (tmp.getGrille()[l][c]==Case.X){System.out.print("X");cpt_j1=cpt_j1+1;}
                            else {if (tmp.getGrille()[l][c]==Case.O)
                                {System.out.print("O");cpt_j2=cpt_j2+1;} }
                        }
                        System.out.print(" | ");
                    }
                    if (cpt_j1 >cpt_j2) {j=j2;} else {j=j1;}
                    grille.Afficher(grille);
                }

            }
        }

        do {

            demandeEtJoue(grille,j);

            grille.Afficher(grille);
            gagne = Gagnant(grille,j,4);
            Sauvegarde.Save(grille,"SAVE");
            //On alterne les joueur 
            if(j.equals(j1)){

                j=j2;
            } else { j= j1;  }

        } while(!gagne && !Pleine(grille));
        if (gagne){

            //On verifie le joueur gagnant sachant qu'on a fait une alternance aprés la victoire du gagnant 
            if(j.equals(j1)){System.out.println("  VICTOIRE DE " + j2.getNom());} else 
            {System.out.println("VICTOIRE DE " + j1.getNom());} }

        else { if (Pleine(grille)) {System.out.println("  MATCH NULL");} }
    }
}
