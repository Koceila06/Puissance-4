import java.util.ArrayList;


/**
 * classe Resolution, elle contient l'algo alphabeta 
 * @author emmanuel adam
 * */
public class Alpha_Beta {

	
	/**fonction alphabeta, determine la valeur du noeud/ de la situation s
	 * pour recuperer la situation a prendre, balayer ensuite la liste des successeurs de s 
	 * et prendre la premiere situation ayant la meme estimation
	 * @param s situation, etat 
	 * @param alpha -9999 borne minimum
	 * @param beta 9999 borne maximum
	 * @return estimation de la situation s en fonction du jeu de l'adversaire*/
	public static int  alphaBeta(Noeud s ,Joueur j, int alpha, int beta) 
	{
		int  borne, val;
		if (s.getFeuille())
		{
			return  IA.Evaluat(s.getGrille_Noeud()) ;/** evalut */
		}
		
		if (j.get_IA())
		{
			ArrayList<Noeud> successeurs = s.getFils();
			 borne = alpha;
			for (Noeud suc:successeurs)
			{
				val = alphaBeta(suc,IA.inverse(j), borne, beta);
				suc.setScore(val);
				 if (val>borne) borne = val;
				 if (borne>=beta) return borne;
			}
		}
		else
		{
			ArrayList<Noeud> successeurs = s.getFils();
			borne = beta;
			for (Noeud suc:successeurs)
			{
				val = alphaBeta(suc,IA.inverse(j), alpha, borne);
				suc.setScore(val);
				 if (val<borne) borne = val;
				 if (borne<=alpha) return borne;
			}
		}
		return borne;			
	}
	
}