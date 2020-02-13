package jeux.dominos;

import iia.jeux.alg.Heuristique;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;


public class HeuristiquesDominos{

	public static  Heuristique hblanc = new Heuristique(){
				
		public int eval(PlateauJeu p, Joueur j){
			PlateauDominos plateau = (PlateauDominos) p;
			if (plateau.isJoueurBlanc(j)) {
				if(plateau.nbCoupsBlanc() == 0) return Integer.MIN_VALUE;
				if(plateau.nbCoupsNoir() == 0) return Integer.MAX_VALUE;	
				int h = plateau.nbCoupsBlanc() - plateau.nbCoupsNoir();
				return h;
			}
			else {
				if(plateau.nbCoupsNoir() == 0) return Integer.MAX_VALUE;
				if(plateau.nbCoupsBlanc() == 0) return Integer.MIN_VALUE;				
				int h = plateau.nbCoupsBlanc() - plateau.nbCoupsNoir();
				return h;
			}
		}
	};

	public static  Heuristique hnoir = new Heuristique(){
	
		public int eval(PlateauJeu p, Joueur j){
			PlateauDominos plateau = (PlateauDominos) p;
			if (plateau.isJoueurNoir(j)) {
				if(plateau.nbCoupsNoir() == 0) return Integer.MIN_VALUE;
				if(plateau.nbCoupsBlanc() == 0) return Integer.MAX_VALUE;	
				int h = plateau.nbCoupsNoir() - plateau.nbCoupsBlanc();
				return h;
			}
			else {
				if(plateau.nbCoupsBlanc() == 0) return Integer.MAX_VALUE;
				if(plateau.nbCoupsNoir() == 0) return Integer.MIN_VALUE;				
				int h = plateau.nbCoupsNoir() - plateau.nbCoupsBlanc();
				return h;
			}
		}
	};

}
