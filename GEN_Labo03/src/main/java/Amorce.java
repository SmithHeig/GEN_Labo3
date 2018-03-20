import models.Emetteur;
import models.Pendule;

/****************************************************************
 * Auteur:	    Eric Lefrançois                                 *
 * Groupe:	    HES_SO  Informatique & Télécommunications       *
 * Fichier:     1er Octobre 2015-  DEPART		                *
 * Projet:	    Horloges synchronisées                          *
 ****************************************************************
 */

public class Amorce {
	public static void main(String argv[]) {
		
		Emetteur emetteur = new Emetteur(1000);    // Emetteur avec une seconde de 100msec
		// Création d'une pendule, avec une seconde valant 120msec (plus lente que l'emetteur
		
		emetteur.addObserver(new Pendule("H0", 1000, 100, 0));
		emetteur.addObserver(new Pendule("H1", 800, 300, 0));
		emetteur.addObserver(new Pendule("H2", 1200, 500, 0));
		emetteur.addObserver(new Pendule("H3", 1400, 700, 0));

		
		
	}
}
