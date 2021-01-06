import java.util.Scanner ;
/**
 * class Puissance4 : represente notre jeux. Elle propose une partie "jvj" ou "jvsIA" avec le choix de la difficulté. 
 */
class Puissance4 {
    private static Scanner s = new Scanner (System.in ) ;

    public static void main (String [] args){
        int choix=0 ;
        int diff=0;
        System.out.println ("Welcome to puissance4, choissir un mode de jeux") ; 
        System.out.println ("1- J vs J") ; 
        System.out.println ("2- J vs IA") ; 
        while (choix != 2 && choix != 1){
            choix= s.nextInt() ;}
        if (choix == 1) {Jeu.Play();}
        if (choix == 2) {
            System.out.println ("choisir le niveau de difficulté") ; 
            System.out.println ("1- IA Simple") ; 
            System.out.println ("2- MinMax") ; 
            while (diff != 2 && diff != 1){
                diff= s.nextInt();}
            if (diff == 1) Jeu_IA.Play() ;
            if (diff == 2) Jeu_IA_MIN.Play(); 
        }

    }


}