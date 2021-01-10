import java.util.Scanner ;
/**
 * class Puissance4 : represente notre jeux. Elle propose une partie "jvj" ou "jvsIA" avec le choix de la difficulté. 
 */
class Partie {
    private static Scanner s = new Scanner (System.in ) ;

    public static void main (String [] args){
        int diff=0;
        for (int i = 0; i < 2 ; i ++){for(int j =0 ; j < 30 ; j++){System.out.print ("**") ; } System.out.println () ; 
        }
        System.out.println () ;
        System.out.println ("      Welcome to puissance4, choissir un mode de jeux") ; 
        System.out.println ("                 1- Joueur vs Joueur") ; 
        System.out.println ("                 2- Joueur vs IA") ; 
        System.out.println () ;
        for (int i = 0; i < 2 ; i ++){for(int j =0 ; j < 30 ; j++){System.out.print ("**") ; } System.out.println () ; 
        }
        int choix=s.nextInt() ;
        System.out.println () ;
        while (choix != 2 && choix != 1){
            System.out.println("Entrer un nombre valide :/ !!");
            choix= s.nextInt() ;
            System.out.println () ;
        }
        if (choix == 1) {Jeu.Play(choix);}
        if (choix == 2) {
            for (int i = 0; i < 2 ; i ++){for(int j =0 ; j < 30 ; j++){System.out.print ("**") ; } System.out.println () ; 
            }
            System.out.println () ;
            System.out.println ("      choisir le niveau de difficulté") ; 
            System.out.println ("                 1- IA Simple") ; 
            System.out.println ("                 2- MinMax") ; 
            System.out.println ("                 3- Alphabeta") ;
            System.out.println () ;
            for (int i = 0; i < 2 ; i ++)
            {for(int j =0 ; j < 30 ; j++){System.out.print ("**") ; } 
                System.out.println () ; 
            }
            System.out.println () ;
            diff= s.nextInt();
            while (diff != 2 && diff != 1 && diff != 3){
                System.out.println("Entrer un nombre valide :/ !!");
                diff= s.nextInt();
                System.out.println () ;
            }
            if (diff == 1) Jeu.Play(diff+1) ;
            if (diff == 2) Jeu_IA_.Play(diff+1); 
            if (diff == 3) Jeu_IA_.Play(diff+1); 
        }

    }

}