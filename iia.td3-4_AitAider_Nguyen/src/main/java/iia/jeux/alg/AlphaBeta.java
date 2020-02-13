/**
 * 
 */

package iia.jeux.alg;

import java.util.ArrayList;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class AlphaBeta implements AlgoJeu {

    /** La profondeur de recherche par défaut
     */
    private final static int PROFMAXDEFAUT = 10;

   
    // -------------------------------------------
    // Attributs
    // -------------------------------------------
 
    /**  La profondeur de recherche utilisée pour l'algorithme
     */
    private int profMax = PROFMAXDEFAUT;

     /**  L'heuristique utilisée par l'algorithme
      */
   private Heuristique h;
   
   private int nombre_noeuds = 0;

    /** Le joueur Min
     *  (l'adversaire) */
    private Joueur joueurMin;

    /** Le joueur Max
     * (celui dont l'algorithme de recherche adopte le point de vue) */
    private Joueur joueurMax;

    /**  Le nombre de noeuds développé par l'algorithme
     * (intéressant pour se faire une idée du nombre de noeuds développés) */
       private int nbnoeuds;

    /** Le nombre de feuilles évaluées par l'algorithme
     */
    private int nbfeuilles;


  // -------------------------------------------
  // Constructeurs
  // -------------------------------------------
    public AlphaBeta(Heuristique h, Joueur joueurMax, Joueur joueurMin) {
        this(h,joueurMax,joueurMin,PROFMAXDEFAUT);
    }

    public AlphaBeta(Heuristique h, Joueur joueurMax, Joueur joueurMin, int profMaxi) {
        this.h = h;
        this.joueurMin = joueurMin;
        this.joueurMax = joueurMax;
        profMax = profMaxi;
//		System.out.println("Initialisation d'un MiniMax de profondeur " + profMax);
    }

   // -------------------------------------------
  // Méthodes de l'interface AlgoJeu
  // -------------------------------------------
   public CoupJeu meilleurCoup(PlateauJeu p) {
	   
       /* A vous de compléter le corps de ce fichier */ 
	   CoupJeu meilleurCoup = null;
	   
	   ArrayList<CoupJeu> coupsPossibles = p.coupsPossibles(joueurMax);
	   
	   nombre_noeuds += coupsPossibles.size();

	   if (coupsPossibles.size() > 0){
		   //apply the first coup
		   PlateauJeu s = p.copy();
		   CoupJeu c = coupsPossibles.get(0);
		   s.joue(joueurMax, c);

		   int alpha = Integer.MIN_VALUE;
		   int beta = Integer.MAX_VALUE;
		   
		   meilleurCoup = c;
		   //for the rest
		   for( int i = 1; i < coupsPossibles.size(); i++) {
			   c = coupsPossibles.get(i);
			   s = p.copy();
			   s.joue(joueurMax, c);
			   int minmax_value = minMax(s, profMax - 1, alpha, beta);
			   if (minmax_value > alpha) {
				   meilleurCoup = c;
				   alpha = minmax_value;
			   }
		   }
	   }
	   
	   return meilleurCoup;
    }
  // -------------------------------------------
  // Méthodes publiques
  // -------------------------------------------
    public String toString() {
        return "MiniMax(ProfMax="+profMax+")";
    }



  // -------------------------------------------
  // Méthodes internes
  // -------------------------------------------

    //A vous de jouer pour implanter Minimax
    private int maxMin(PlateauJeu p, int profondeur, int alpha, int beta) {
    	ArrayList<CoupJeu> coupsPossibles = p.coupsPossibles(joueurMax);
    
    	nombre_noeuds += coupsPossibles.size();
    
    	if (profondeur == 0 || coupsPossibles.size() == 0) {
    		return h.eval(p, joueurMax);
    	}
    	else {
    		for( int i = 0; i<coupsPossibles.size(); i++) {
    			CoupJeu c = coupsPossibles.get(i);
    			PlateauJeu successeur = p.copy();
    			successeur.joue(joueurMax, c);
    			alpha = Math.max(alpha, minMax(successeur, profondeur-1, alpha, beta));
    			if (alpha >= beta) {
    				return beta;
    			}
    		}
    		return alpha;
    	}
    }
    
    
    private int minMax(PlateauJeu p, int profondeur, int alpha, int beta) {
    	ArrayList<CoupJeu> coupsPossibles = p.coupsPossibles(joueurMin);
    	
    	nombre_noeuds += coupsPossibles.size();
    	
    	if (profondeur == 0 || coupsPossibles.size() == 0) {
    		return h.eval(p, joueurMin);
    	}
    	else {
    		for( int i = 0; i< coupsPossibles.size(); i++) {
    			CoupJeu c = coupsPossibles.get(i);
    			PlateauJeu successeur = p.copy();
    			successeur.joue(joueurMin, c);
    			beta = Math.min(beta, maxMin(successeur, profondeur-1, alpha, beta));
    			if (alpha >= beta) {
    				return alpha;
    			}
    		}
    		return beta;
    	}
    }
    	
    

}
