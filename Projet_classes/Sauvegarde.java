import java.io.FileOutputStream ;
import java.io.ObjectOutputStream;
import java.io.IOException;

 

class Sauvegarde {
    public static void Save(Grille g,String nom){
        try {
            FileOutputStream fichier = new FileOutputStream("C:\\Users\\salah\\Desktop\\projet\\Projet_classes"+nom+".txt");

 

            ObjectOutputStream oos = new ObjectOutputStream(fichier);

 

            oos.writeObject(g);
            
            oos.close();
        }

 

        catch (Exception e){
            e.printStackTrace();
        }
    }
}
 
