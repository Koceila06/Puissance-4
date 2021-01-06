import java.io.Serializable ; 
import java.util.Scanner;
import java.util.Random ;
 
public class Jeu_IA implements Serializable {

    public static boolean Pleine(Grille g){
        //Si on trouve une case VIDE sur la premiere ligne ,la grille n'est pas pleine
        //On utilise une boucle for each 
        Case[] tmp = null ;
        for (int i = 0; i< g.getGrille()[0].length ; i++ )
        {  tmp [i] = g.getGrille()[0][i] ;
        }
        for(Case courant :tmp){
            if (courant.equals(Case.VIDE)){return false ;}
        }
        return true;

    }

    public static void demandeEtJoue(Grille g, Joueur j){
        Scanner s = new Scanner(System.in);
        boolean valide ;
        int colonne ;
        Random r = new Random () ;
        // On commence par d'abord lire le nombre et puis verifier sa validité
        do {
            System.out.println(j.getNom()+ " : entrez le numéro de colonne");
            if (j.getNom().equals("IA")){colonne =r.nextInt(9)+1;}

            else {colonne = s.nextInt();}
            // Pour les joueur ,la premiere case de la  grille du jeu commence par 1.
            //Mais les indince en java commence par 0 , donc on soustrait "1" du nombre entré.
            colonne--;
            valide=Jeu.Jouer(g,colonne,j);
            if (!valide){System.out.println(" le coup n'est valide");}
        }
        while(!valide);}

    // Le coup est valide : on affiche la grille!

    public static void Play ( ){
           Scanner k = new Scanner(System.in) ;
        int L , C ;
        do{
        System.out.println("Entrez le nombre de Ligne") ;
        L = k.nextInt() ;
        System.out.println("Entrez le nombre de Colonne") ;
        C = k.nextInt() ;
        if (L < 4) {System.out.println ("Ligne doit etre superieure a 4");
         System.out.println("Entrez le nombre de Ligne") ;
        L = k.nextInt() ;
        }
        if (C < 4) {System.out.println ("Colonne doit etre superieure a 4");
        System.out.println("Entrez le nombre de Colonne") ;
        C = k.nextInt() ;}
    }while(L < 4 || C < 4);
        Grille grille = new Grille(L,C);
        Joueur j1 = new Joueur("Humain",Case.X);
        Joueur j2 = new Joueur("IA",Case.O);
        Grille.Initialiser(grille);

        Scanner s = new Scanner(System.in);
        Jeu partie = new Jeu(j1,j2,grille,4);

        boolean gagne ;
        //Une variable temporaire pour alterner les joueurs
        Joueur j=j1; 
        Grille tmp  = Charge.load("Save_IA1");
        if (tmp != null) {
        if (!(Pleine(tmp) || Jeu.Gagnant(tmp,j1,4) || Jeu.Gagnant(tmp,j2,4)) ){

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
                            {System.out.print("O");cpt_j2=cpt_j2+1;}}
                    }
                    System.out.print(" | ");
                }
                if (cpt_j1 >cpt_j2) {j=j2;} else {j=j1;}
                grille.Afficher(grille);
            }

        }}

        do {
            demandeEtJoue(grille,j);
            grille.Afficher(grille);
            gagne = Jeu.Gagnant(grille,j,4);
            Sauvegarde.Save(grille,"Save_IA1");
            //On alterne les joueur 
            if(j.equals(j1)){

                j=j2;
            }else {j= j1;  }
        }while(!gagne && !Jeu.Pleine(grille));
        if (gagne){

            //On verifie le joueur gagnant sachant qu'on a fait une alternance aprés la victoire du gagnant 
            if(j.equals(j1)){System.out.println("  VICTOIRE DE " + j2.getNom());}else 
            {System.out.println("VICTOIRE DE " + j1.getNom());}}

        else {if (Jeu.Pleine(grille)) {System.out.println("  MATCH NULL");}}
    }
}


