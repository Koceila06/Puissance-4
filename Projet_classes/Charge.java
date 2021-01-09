import java.io.FileInputStream ;
import java.io.ObjectInputStream ;
/**
 * Permet la Charge de Partie
 * @param nom Le nom du fichier a charger
 */
class Charge{
    public static Grille load( String nom){
        Grille tmp=null;
        try {
            //On précise le chemin ou le fichier sera sauvegardé
            // On a utiliser le paramettre pour pouvoir sauvegarder deux fichier au meme temps(Humain vs Humain ou Humain vs IA)
            //
            FileInputStream ios= new FileInputStream ("Téléchargements"+nom+".txt");
            ObjectInputStream fils= new ObjectInputStream(ios);
            tmp = (Grille) fils.readObject();
            fils.close();
        }
        catch(Exception e ) {
            System.out.print("erreur");
        }
        return tmp;
    }
}