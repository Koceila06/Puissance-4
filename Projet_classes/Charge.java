import java.io.FileInputStream ;
import java.io.ObjectInputStream ;

 

class Charge{
    public static Grille load( ){
        Grille tmp=null;
        try {
            FileInputStream ios= new FileInputStream ("C:\\TP7\\Save.txt");
            ObjectInputStream fils= new ObjectInputStream(ios);
            tmp = (Grille) fils.readObject();
            fils.close();
            System.out.print(tmp.getL()+"*"+tmp.getC()+"  ");
            for (int l = tmp.getGrille().length-1;l>=0; l--){
              for(int c =0 ; c < tmp.getGrille()[l].length;c++ ) {
                  if (tmp.getGrille()[l][c]==Case.X){System.out.print("X");}
                  else {if (tmp.getGrille()[l][c]==Case.O)
                      {System.out.print("O");} }
              }
              System.out.print(" | ");
            }
            
        }
        catch(Exception e ) {
            e.getMessage();

 

        }
        return tmp;

 

    }
}