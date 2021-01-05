
import java.io.Serializable ; 
import java.util.Scanner;
import java.util.Random ;
 
public class Jeu_IA_MIN implements Serializable {

    
   
    public static void demandeEtJoue2(Grille g, Joueur j){
        Scanner s = new Scanner(System.in);
        boolean valide ;
        int colonne ;
        Random r = new Random () ;
        // On commence par d'abord lire le nombre et puis verifier sa validité
        do {
            System.out.println(j.getNom()+ " : entrez le numéro de colonne");
           // r.nextInt(8+1-1)+1
            if (j.getNom().equals("IA")){colonne = IA.Chois_Colonne(g,j); System.out.println(colonne);}
            else {colonne = s.nextInt();}
            // Pour les joueur ,la premiere case de la  grille du jeu commence par 1.
            //Mais les indince en java commence par 0 , donc on soustrait "1" du nombre entré.
            colonne--;
            valide=Jeu.Jouer(g,colonne,j);
            if (!valide){System.out.println(" le coup n'est valide");}

        }
        while(!valide);}

    // Le coup est valide : on affiche la grille!

    public static void main (String [] args){
        Grille grille = new Grille(6,7);
        Joueur j1 = new Joueur("Humain",Case.X,false);
        Joueur j2 = new Joueur("IA",Case.O,true);
        Grille.Initialiser(grille);

        Scanner s = new Scanner(System.in);
        Jeu partie = new Jeu(j1,j2,grille,4);

        boolean gagne ;
        //Une variable temporaire pour alterner les joueurs
        Joueur j=j2; ;
        Grille tmp  = Charge.load("Save_IA");
        if (tmp != null){

        if (!(Jeu.Pleine(tmp) || Jeu.Gagnant(tmp,j1,4) || Jeu.Gagnant(tmp,j2,4)) ){

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
            demandeEtJoue2(grille,j);
            grille.Afficher(grille);
            gagne = Jeu.Gagnant(grille,j,4);
            Sauvegarde.Save(grille,"Save_IA");
            //On alterne les joueur 
            if(j.equals(j1)){

                j=j2;
            } else { j= j1;  }

        } while(!gagne && !Jeu.Pleine(grille));
        if (gagne){

            //On verifie le joueur gagnant sachant qu'on a fait une alternance aprés la victoire du gagnant 
            if(j.equals(j1)){System.out.println("VICTOIRE DE " + j2.getNom());} else 
            {System.out.println("VICTOIRE DE " + j1.getNom());} }

        else { if (Jeu.Pleine(grille)) {System.out.println("NULLLLL");} }
    }
}

