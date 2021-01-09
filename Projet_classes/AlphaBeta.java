import java.util.ArrayList;

public class AlphaBeta {
    private static final int PROFONDEUR_EXPLORATION_ALPHA_BETA =7 ;
    public static int calculeColonneAJouer(Grille grille, Joueur joueur){
        ArrayList<Integer> colonnesAJouer = new ArrayList<Integer>();
        for(int i = 0; i <7; i++){
            Grille copy =Grille.copy(grille);
            //Jouer renvoie true si elle a reussi a jouer
            // elle verifie que la colonne n'est pas pleine et recherche la premiere case vide et joue le coup
            if (Jeu.Jouer(copy,i,joueur)){   
                //creer un noeud et initalise ses fils;
                //si Ia appeler min de children sinon max de children
                if(Jeu.Gagnant(copy,joueur,4)){;return i+1;                                 
                }
            }
            }
        for(int i = 0; i <7; i++){
            Grille copy2 =Grille.copy(grille);
            if (Jeu.Jouer(copy2,i,IA.inverse(joueur))){
                if(Jeu.Gagnant(copy2,IA.inverse(joueur),4)){System.out.print("ok3");return i+1; 
                }
            }
        }
        // On initialise les résultat avec la première colonne jouable pour éviter
        // que l'IA ne selectionne une colonne non jouable par défaut
        for(int i = 1; i <= 7; i++){

            if(!grille.ColonnePleine(grille,i)){
                colonnesAJouer.add(new Integer(i));
                break;
            }
        }
         //On parcours les 7 colonnes 
        int  valeurDeJeu = -99999;
        for(int i=0; i <7; i++){
            //On fait une copie de la grille pour ne pas modifier son contenue
            Grille copy = grille.copy(grille);
            if(Jeu.Jouer(copy,i,joueur)){
               //On appelle la recurence jusqu'a arriver a la profendeur donnee
                int  valeurDeJeuCourante = alphabeta(copy, joueur, PROFONDEUR_EXPLORATION_ALPHA_BETA);
                if (valeurDeJeuCourante == valeurDeJeu){
                    //On ajoute le coup jouable
                    colonnesAJouer.add(new Integer(i));
                }else if(valeurDeJeuCourante > valeurDeJeu){
                    colonnesAJouer.clear();
                    valeurDeJeu = valeurDeJeuCourante;
                    colonnesAJouer.add(new Integer(i));
                }
            }
        }
        int numeroDeColonneAJouer = (int) (Math.random() * colonnesAJouer.size());
        return colonnesAJouer.get(numeroDeColonneAJouer)+1;
    }
    
    private static int alphabeta(Grille grille, Joueur joueur, int profondeur){
        int  alpha =-99999;
        int  beta=99999;
        return min(grille, joueur, profondeur, alpha, beta);
    }
    /**
     * Applique la partie min, de minmax
     */
    private static  int  min(Grille grille, Joueur joueur,  int profondeur, double alpha, double beta){
        if(profondeur != 0){
            int  valeurDeJeu =99999;
            for(int i=0; i <7 ; i++){
                Grille copy = grille.copy(grille);
                if(Jeu.Jouer(copy,i,joueur)){
                    valeurDeJeu = Math.min(valeurDeJeu, max(copy, joueur, profondeur-1, alpha, beta));
                    if(alpha >= valeurDeJeu){
                        return valeurDeJeu; // Coupure alpha
                    }
                    beta = Math.min(beta, valeurDeJeu);
                }
            }
            return valeurDeJeu;
        }
        else{
            return IA.Evaluat(grille);
        }
    }
    /**
     * Applique la partie max de minmax
     */
    private static int  max(Grille grille, Joueur joueur, int profondeur, double alpha, double beta){
        if(profondeur != 0){
            int valeurDeJeu =-99999;
            for(int i=0; i < grille.getC(); i++){
                Grille copy = grille.copy(grille);
                if(Jeu.Jouer(copy,i,joueur)){

                    valeurDeJeu = Math.max(valeurDeJeu, min(copy, joueur, profondeur-1, alpha, beta));

                    if(valeurDeJeu >= beta){
                        return valeurDeJeu; // Coupure beta
                    }
                    alpha = Math.max(alpha, valeurDeJeu);

                }
            }
            return valeurDeJeu;
        }else{
            return IA.Evaluat(grille);
        }
    }
}