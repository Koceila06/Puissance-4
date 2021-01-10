import java.io.Serializable ; 
import java.util.Scanner;
import java.util.Random ; 
/**
 * La classe Jeu_IA contient une methode Play
 * 
 */
public class Jeu_IA_ implements Serializable {
    /**
     *  La methode Play commence une partie de Puissance 4 
     * @param choix  correspond au type de la partie
     */

    public static void Play (int choix){
        Joueur j1 = new Joueur("Joueur1",Case.X,false);
        Joueur j2 = null;
        int L=0; 
        int C=0 ;
        int X=0;
        Scanner k = new Scanner(System.in) ;
        Grille tmp= null;
        // choix represente le type de partie jouer 
        // 1 : Joueur vs Joueur 
        // 2 : Joueur vs IA simple
        // 3 : Joueur vs IA Minmax
        // 4 : Joueur vs IA alphabeta

        if (choix == 1){Joueur humain = new Joueur("Joueur1",Case.O,false);
            j2 = humain ;}
        else {if (choix==2){Joueur ia = new Joueur("IA",Case.O,true);
                j2 = ia ;}
            else{if(choix==3){Joueur ia = new Joueur("IA_Max",Case.O,true);j2 = ia ;}
                else {if(choix==4){Joueur ia = new Joueur("IA_Alpha",Case.O,true);j2 = ia ;}}}

        } 
        // On Cree la grille est on l'a initialise a 6*7
        Grille grille = new Grille (6,7) ;
        Scanner s = new Scanner(System.in);
        Grille.Initialiser(grille);
        // Une nouvelle partie 
        Jeu partie = new Jeu(j1,j2,grille,4);
        boolean gagne ;
        //Une variable temporaire pour alterner les joueurs
        Joueur j=j1; 

        // Charger le Bon fichier de la partie en fonction du choix

        if (choix == 3){tmp  = Charge.load("Save_IA_max");}
        else{if (choix==4){ tmp  = Charge.load("Save_IA_Alpha");}
        }
        // si la charge elle n'est pas null. On demande au joueur, si il veut continuer la partie précédente.
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
                    // Cette double boucle affiche la taille et l'etat de la grille chargé 
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

            }} 

        do {
            Jeu.demandeEtJoue(grille,j);
            grille.Afficher(grille);
            gagne = Jeu.Gagnant(grille,j,4);
            // Sauvegarder dans le Bon fichier de la partie en fonction du choix
            if (choix == 3){Sauvegarde.Save(grille,"Save_IA_max");}
            else{if (choix==4){Sauvegarde.Save(grille,"Save_IA_Alpha");;}
            }
            //On alterne les joueur 
            if(j.equals(j1)){

                j=j2;
            } else { j= j1;  }

        } while(!gagne && !Jeu.Pleine(grille));
        // Si une personne gagne :
        if (gagne){
            System.out.println () ;
            for (int i = 0; i < 2 ; i ++){for(int c =0 ; c < 15 ; c++){System.out.print ("**") ; } System.out.println () ; 
            }
            System.out.println () ;
            //On verifie le joueur gagnant sachant qu'on a fait une alternance aprés la victoire du gagnant 
            
            if(j.equals(j1)){
                System.out.println("      VICTOIRE DE " + j2.getNom()+" ;)");} else 
            {System.out.println("      VICTOIRE DE " + j1.getNom()+" ;)");} 
            System.out.println () ;
            for (int i = 0; i < 2 ; i ++){for(int c =0 ; c < 15 ; c++){System.out.print ("**") ; } System.out.println () ; 
            } 
            // si partie est finie on demande de refaire une autre partie 
            System.out.println () ;
            System.out.println ("Voulez vous refaire une partie ?");
            System.out.println ("1- OUIIII");
            System.out.println ("2- NON");
            X = s.nextInt();
            // si la partie est finie on suprime la sauvegarde
            if (choix == 3){Sauvegarde.Save(null,"Save_IA_max");}
            else{if (choix==4){Sauvegarde.Save(null,"Save_IA_Alpha");}
            }
            if (X==1) {Partie.main(null);
            }else {System.out.println ("AU REVOIR :/ !! ");}
            System.out.println () ;
        }
        else { if (Jeu.Pleine(grille)) {System.out.println("Match Null");}
            // si partie est finie on demande de refaire une autre partie
            System.out.println () ;
            System.out.println ("Voulez vous refaire une partie ?");
            System.out.println ("1- OUIIII");
            System.out.println ("2- NON");
            X = s.nextInt();
            // si la partie est finie on suprime la sauvegarde
            if (choix == 3){Sauvegarde.Save(null,"Save_IA_max");}
            else{if (choix==4){Sauvegarde.Save(null,"Save_IA_Alpha");}
            }
            if (X==1) {Partie.main(null);
            }else {System.out.println ("AU REVOIR :/ !! ");}
            System.out.println () ;
        }} }

