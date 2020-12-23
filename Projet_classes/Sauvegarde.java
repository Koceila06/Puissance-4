import java.io.FileOutputStream ;
import java.io.ObjectOutputStream;
import java.io.IOException;

 

class Sauvegarde {
    public static void Save(Grille g){
        try {
            FileOutputStream fichier = new FileOutputStream("C:\\TP7\\Save.txt");

 

            ObjectOutputStream oos = new ObjectOutputStream(fichier);

 

            oos.writeObject(g);
            
            oos.close();
        }

 

        catch (IOException e){
            e.printStackTrace();
        }
    }
}
 