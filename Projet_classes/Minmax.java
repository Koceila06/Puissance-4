import java.util.ArrayList;
class Minmax{
    /**
     * Construit l'Arbre du Jeu et renvoie la racine
     * @param racine La racine de l'Arbre
     * @param j Le joueur a qui est le tour de jouer
     * @param profendeur La profendeur de l'arbre
     */
    public static Noeud Const_Arbre(Noeud racine,Joueur j,int profendeur){
       
        // Si on est pas arrivé a la derniere profendeur 
        if (profendeur!=1){
            //On parcour les 7 colonne possible
            for(int i=0;i<7;i++){

                //On fait une copie profonde pour ne pas modifier le contenue de la grille
                Grille copy =Grille.copy(racine.getGrille_Noeud());
                //Jouer renvoie true si elle a reussi a jouer
                // elle verifie que la colonne n'est pas pleine et recherche la premiere case vide et joue le coup
                if (Jeu.Jouer(copy,i,j)){   
                    //On crée un noeud avec la Grille modifié aprés avoir jouer 
                    Noeud node= new Noeud(copy);
                    //Une deuxieme copie pour verifier que l'adversaire ne gagnera pas du premier coup
                    Grille copy2 =Grille.copy(racine.getGrille_Noeud());
                    //On verifie si un des deux joueur peut gagner dés le premier coup
                    //Si c'est le cas on Arrete la création de l'arbre 
                    //On commence d'abord par verifier La machine ,si elle ne gagne pas on verifei l'Adversaire
                    //Profendeur 4 pour dire que c'est le premier coup a jouer 
                    if(Jeu.Gagnant(copy,j,4)&& profendeur==5){
                        //On met le score a une valeur Maximale pour le choisir
                        if(j.get_IA()==true)node.setScore(101000);                                 
                    } 
                    else {
                        if (Jeu.Jouer(copy2,i,inverse(j))){
                            // On met le score a une valeur Maximale pour le choisir
                            if(Jeu.Gagnant(copy2,inverse(j),4)&& profendeur==5){node.setScore(10000); 
                            }
                        }    
                    }                    
                    // Si aucun des deux joueurs ne peut gagner,On Crée L'arbre avec la reccurence 
                    if (Jeu.Gagnant(copy2,inverse(j),4)==false && Jeu.Gagnant(copy,j,4)==false ){
                        //on va jusqu'a la profendeur pour appeller la fonction d'evaluation
                        node=Const_Arbre(node,inverse(j),profendeur-1);
                        //On initalise le Score avec le Max de ses fils si c'est la machine 
                        //le Score de la profendeur sera remonter
                        if( j.get_IA()==true){ 
                            node.setScore(Max(node.getFils())); 

                        } else  {  
                            node.setScore(Min(node.getFils()));               

                        }

                    }
                    // Ajoute le Noeud avec son Score a la racine 
                    //Quand on arrive a la profendeur la racine sera egale au pere des Noeud evalué 
                    racine.Add_Fils(node);

                } else {
                    //si la colonne est pleine
                    if(profendeur==5)
                        racine.Add_Fils(new Noeud (Grille.copy(racine.getGrille_Noeud()),-90000));
                    //si la colonne est pleine a partir d'une certaine profendeur 
                    //donc c'est une feuille;on appelle la fonction d'evaluation
                    else {racine.Add_Fils(new Noeud (Grille.copy(racine.getGrille_Noeud()),Evaluat(racine.getGrille_Noeud())));}  } 

            }
        }
        else {
            // Arriver a la profendeur
            for(int i=0;i<7;i++){
                Grille copy =Grille.copy(racine.getGrille_Noeud());
                //Jouer renvoie true si elle a reussi a jouer
                // elle verifie que la colonne n'est pas pleine et recherche la premiere case vide et joue le coup
                if (Jeu.Jouer(copy,i,j)){
                    // On evalue les 7 possibilités
                    //Pour cela on lui passe la fonction d'evaluation qui renverrai son score
                    Noeud node= new Noeud(copy, Evaluat(copy));            
                    //ajoute le noeud evaluer aux fils du parent
                    racine.Add_Fils(node);

                }

            }
        }
        return racine; 
    }

    /**
     * Renvoie l'arbre du jeu
     * @param grille La grille initialle
     * @param profendeur La profendeur de l'arbre
     * @param joueur Le joueur a jouer
     */

    public static  Arbre Const_Arbre_Du_Jeu(Grille grille,int profendeur,Joueur joueur ){
        //On appelle la méthode précédente pour renvoyé la racinde de l'arbre
        Noeud root=new Noeud(grille,0);
        root=Const_Arbre(root,joueur,profendeur);
        //On retourne l'arbre du jeu
        return new Arbre(root);

    }

    /**
     * Renvoi la Colonne a jouer
     * @param grille La grille initialle
     * @Joueur le joueur a qui est le tour 
     * @return retourne la colonne a jouer
     */
    public static int Chois_Colonne(Grille grille,Joueur j ){
        //On initialise le score a une valeur qui ne sera jamais atteinte 
        int Score=-999999999;
        int colonne_ajouer=1;
        //On construit l'arbre du jeu
        Arbre arb = Const_Arbre_Du_Jeu(grille,5,j);
        //On récupere sa racine 
        Noeud racine = arb.getRoot();
        for(int i=0;i<racine.getFils().size();i++){
            //On affiche le score finale de chaque colonne pour verifier que le programme marche bien
            if( racine.getFils().get(i).getScore()>= Score){
                //On choisi le meilleur Score
                Score=racine.getFils().get(i).getScore();
                //On met a jour la colonne a jouer si le score est superiere au précédent

                colonne_ajouer=i;
            }
            // }

        } 

        return  colonne_ajouer+1;

    }

    /**
     * Prends une liste de noeud et renvoiecelui qui contient le score le plus elevé
     * @param fils La liste des fils 
     */
    public static int Max(ArrayList<Noeud> fils){
        int max=-999999999;

        for(Noeud node : fils){

            if (node.getScore()>max){max=node.getScore();}
        }

        return max;
    }

    /**
     * Prends une liste de noeud et renvoiecelui qui contient le score le moins elevé
     * @param fils La liste des fils 
     */
    public static int Min(ArrayList<Noeud> fils){

        int min=9999999;
        for(Noeud node : fils){

            if (node.getScore()<min){min=node.getScore();}
        }

        return min;
    }

    /**
     * La fonction d'evaluation
     * @param grille La grille a evaluer
     */

    public static int Evaluat(Grille grille){
        //On donne un score pour chaque alignement
        //On prend toujours La machine comme un "O" et le Joueur comme un "X"
        int fourInLine = 1000;
        int threeInLine = 16;
        int twoInLine =6;
        int Xlines = 0;
        int Olines = 0;

        // Verifier les lignes
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 3; j++){ 
                int s = 0;

                for(int count = 0; count < 4; count++){
                    if(grille.getGrille()[i][j + count] == grille.getGrille()[i][j + count + 1] && grille.getGrille()[i][j + count] != Case.VIDE){
                        s++;
                    }
                    else if( (grille.getGrille()[i][j + count] !=grille.getGrille()[i][j + count + 1] && grille.getGrille()[i][j + count] != Case.VIDE) || count == 2){
                        if(grille.getGrille()[i][j + count] == Case.X){
                            if(s == 1){
                                Xlines = Xlines + twoInLine;
                            }
                            else if(s == 2){
                                Xlines = Xlines + threeInLine;
                            }
                            else if(s == 3){
                                Xlines = Xlines + fourInLine;
                            }
                        }
                        else if(grille.getGrille()[i][j + count] == Case.O){
                            if(s == 1){
                                Olines = Olines + twoInLine;
                            }
                            else if(s == 2){
                                Olines = Olines + threeInLine;
                            }
                            else if(s == 3){
                                Olines = Olines + fourInLine;
                            }

                        }

                        s = 0;
                    }
                    if(grille.getGrille()[i][j + count] == Case.VIDE){
                        s = 0;
                    }
                }

            }
        }

        // Verifier colonne
        for(int j = 0; j < 7; j++){
            for(int i = 0; i < 3; i++){
                int s = 0;

                for(int count = 0; count < 3; count++){

                    if(grille.getGrille()[i + count][j] == grille.getGrille()[i + count + 1][j] && grille.getGrille()[i + count][j] != Case.VIDE){
                        s++;
                    }
                    else if((grille.getGrille()[i + count][j] != grille.getGrille()[i + count + 1][j] && grille.getGrille()[i + count][j] != Case.VIDE) || count == 2){
                        if(grille.getGrille()[i + count][j] == Case.X){
                            if(s == 1){
                                Xlines = Xlines + twoInLine;
                            }
                            else if(s == 2){
                                Xlines = Xlines + threeInLine;
                            }
                            else if(s == 3){
                                Xlines = Xlines + fourInLine;
                            } 
                        }
                        else if(grille.getGrille()[i + count][j] == Case.O){
                            if(s == 1){
                                Olines = Olines + twoInLine;
                            }
                            else if(s == 2){
                                Olines = Olines + threeInLine;
                            }
                            else if(s == 3){
                                Olines = Olines + fourInLine;
                            }                      
                        }

                        s = 0;
                    }
                    if(grille.getGrille()[i + count][j] == Case.VIDE){
                        s = 0;
                    }
                }
            }
        }

        // Verifier diagonale droite
        for(int i = 3; i < 6; i++){
            for(int j = 0; j < 3; j++){
                int s = 0;

                for(int count = 0; count < 3; count++){

                    if(grille.getGrille()[i - count][j + count] == grille.getGrille()[i - count - 1][j + count + 1] && grille.getGrille()[i - count][j + count] != Case.VIDE){
                        s++;
                    }
                    else if((grille.getGrille()[i - count][j + count] != grille.getGrille()[i - count - 1][j + count + 1] && grille.getGrille()[i - count][j + count] != Case.VIDE) || count == 2){
                        if(grille.getGrille()[i - count][j + count] == Case.X){
                            if(s == 1){
                                Xlines = Xlines + twoInLine;
                            }
                            else if(s == 2){
                                Xlines = Xlines + threeInLine;
                            }
                            else if(s == 3){
                                Xlines = Xlines + fourInLine;
                            } 
                        }
                        else if(grille.getGrille()[i - count][j + count] == Case.O){
                            if(s == 1){
                                Olines = Olines + twoInLine;
                            }
                            else if(s == 2){
                                Olines = Olines + threeInLine;
                            }
                            else if(s == 3){
                                Olines = Olines + fourInLine;
                            }                           

                        }

                        s = 0;
                    }
                    if(grille.getGrille()[i - count][j + count] == Case.VIDE){
                        s = 0;
                    }
                }
            }
        }

        // Verifier diagonale gauche
        for(int i = 3; i < 6; i++){
            for(int j = 3; j < 7; j++){
                int s = 0;

                for(int count = 0; count < 3; count++){

                    if(grille.getGrille()[i - count][j - count] == grille.getGrille()[i - count - 1][j - count -1] && grille.getGrille()[i - count][j - count] != Case.VIDE){
                        s++;
                    }
                    else if((grille.getGrille()[i - count][j - count] != grille.getGrille()[i - count - 1][j - count -1] && grille.getGrille()[i - count][j - count] != Case.VIDE)){
                        if(grille.getGrille()[i - count][j - count] == Case.X){
                            if(s == 1){
                                Xlines = Xlines + twoInLine;
                            }
                            else if(s == 2){
                                Xlines = Xlines + threeInLine;
                            }
                            else if(s==3){
                                Xlines = Xlines + fourInLine;
                            }
                        }
                        else if(grille.getGrille()[i - count][j - count] == Case.O){
                            if(s == 1){
                                Olines = Olines + twoInLine;
                            }
                            else if(s == 2){
                                Olines = Olines + threeInLine;
                            }
                            else if(s==3 ){
                                Olines = Olines + fourInLine;
                            }                         

                        }
                        s = 0;
                    }
                    if(grille.getGrille()[i - count][j - count] == Case.VIDE){
                        s = 0;
                    }
                }
            }
        }

        return Olines - Xlines;
    }
    /**
     * Inverse le Joueur courant 
     * @param j Le joueur a inverser
     */
    public static  Joueur inverse (Joueur j){ 
        if (j.getType()==Case.O) { 
            return new Joueur("a",Case.X); }
        else { if (j.getType()== Case.X){return new Joueur("o",Case.O);}} return null;} 

}
