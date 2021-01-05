import java.util.ArrayList;
class IA{
    public static  Joueur inverse (Joueur j){ 
        if (j.getType()==Case.O) { 
            return new Joueur("a",Case.X,!j.get_IA()); }
        else { if (j.getType()== Case.X){return new Joueur("a",Case.X,!j.get_IA());}} return null;} 

    public static Noeud Const_Arbre(Noeud racine,Joueur j,int profendeur){


               
        if (profendeur!=1){
            //System.out.println();
            for(int i=0;i<7;i++){

                Grille copy =Grille.copy(racine.getGrille_Noeud());
                //Jouer renvoie true si elle a reussi a jouer
                // elle verifie que la colonne n'est pas pleine et recherche la premiere case vide et joue le coup
                if (Jeu.Jouer(copy,i,j)){   

                    //creer un noeud et initalise ses fils;
                    //si Ia appeler min de children sinon max de children 
                    Noeud node= new Noeud(copy);
                    Grille copy2 =Grille.copy(racine.getGrille_Noeud());
                    
                    if(Jeu.Gagnant(copy,j,4)&& profendeur==5){if(j.get_IA())node.setScore(101000);} else {
                    if (Jeu.Jouer(copy2,i,inverse(j))){
                    if(Jeu.Gagnant(copy2,inverse(j),4)&& profendeur==5){node.setScore(10000);}
                     } }
                    
                    
                    
                    
                    
                    
                    if (!Jeu.Gagnant(copy2,j,4) && !Jeu.Gagnant(copy,j,4) ){
                       

                        //on va jusqu'a la profendeur pour appeller la fonction d'evaluation
                        node=Const_Arbre(node,inverse(j),profendeur-1);

                        if( j.get_IA()){ 
                            node.setScore(Max(node.getFils()));               //  System.out.print("max"+node.getScore());

                        } else {   node.setScore(Min(node.getFils()));                //   System.out.print("min"+node.getScore());
                        }

                        
                        
                        
                    }
                    // ajoute a ses fils
                    racine.Add(node);

                }  // else {if (j.get_IA() && profendeur==4){Noeud node=new Noeud(copy,9999); racine.Add(node) ; } else { if(profendeur==4 && !j.get_IA()){Noeud node= new Noeud(copy,-9999);racine.Add(node); }  }}

            }}else {
            for(int i=0;i<7;i++){
                // arriver a la profendeur 
                Grille copy =Grille.copy(racine.getGrille_Noeud());
                //Jouer renvoie true si elle a reussi a jouer
                // elle verifie que la colonne n'est pas pleine et recherche la premiere case vide et joue le coup
                if (Jeu.Jouer(copy,i,j)){

                    Noeud node= new Noeud(copy, Evaluat(copy));            
                    //System.out.print(node.getScore()+"|");

                    racine.Add(node);

                }
                //profendeur == 0 ,appeler fonction d'evaluition pour lui mm

            }

        }
        return racine; 
    }


    public static  Arbre Const_Arbre_Du_Jeu(Grille grille,int profendeur,Joueur j ){
        Noeud root=new Noeud(grille,0);
        root=Const_Arbre(root,j,profendeur);
        return new Arbre(root);

    }

    public static int Chois_Colonne(Grille grille,Joueur j ){
        int Score=-99999999;
        int colonne_ajouer=1;
        Arbre arb = Const_Arbre_Du_Jeu(grille,5,j);
        Noeud racine = arb.getRoot();
        for(int i=0;i<racine.getFils().size();i++){
            System.out.print(racine.getFils().get(i).getScore()+" ");
            if( racine.getFils().get(i).getScore()>= Score){
                Score=racine.getFils().get(i).getScore();
                //System.out.println(racine.getFils().get(i).getScore());
                colonne_ajouer=i;

            }
            // if( rac){

            // }

        }

        return colonne_ajouer+1;

    }
    ///public static MinMax(){   

    //}
    public static int Max(ArrayList<Noeud> fils){
        int max=-999999999;

        for(Noeud node : fils){

            if (node.getScore()>max){max=node.getScore();}
        }

        return max;
    }

    public static int Min(ArrayList<Noeud> fils){

        int min=9999999;
        for(Noeud node : fils){

            if (node.getScore()<min){min=node.getScore();}
        }

        return min;
    }

    public static int Evaluat(Grille grille){
        int fourInLine = 1000;
        int threeInLine = 16;
        int twoInLine =6;
        int Xlines = 0;
        int Olines = 0;

        // Checking rows
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

        // Checking columns
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
                            } //else if(s==4){
                            //  Xlines = Xlines+ fourInLine*2;}
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

        // Checking diagonals to right
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

        // Checking diagonals to left
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

}
