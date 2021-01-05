import java.io.FileInputStream ;
import java.io.ObjectInputStream ;

 

class Charge{
    public static Grille load( String nom){
        Grille tmp=null;
       
        try {
            FileInputStream ios= new FileInputStream ("C:\\TP7\\"+nom+".txt");
            ObjectInputStream fils= new ObjectInputStream(ios);
           
            tmp = (Grille) fils.readObject();
            fils.close();
          
            
        }
        catch(Exception e ) {
            System.out.print('x');

 

        }
        return tmp;

 

    }
}