package models; /****************************************************************
 * Auteur:	    Eric Lefrançois                                 *
 * Groupe:	    HES_SO      Informatique & Télécommunications   *
 * Fichier:     Emetteur.java                                   *
 * Date :	      1er Octobre 2016    - Départ             		    *
 * Projet:	    Horloges synchronisées                          *
 ****************************************************************
*/


import views.VueEmetteur;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;


public class Emetteur extends Observable implements Runnable {

  private final static int LARGEUR = 100;		// largeur fenêtre de l'emetteur
  private final static int HAUTEUR = 100;		// hauteur fenêtre de l'emetteur

  private int dureeSeconde;                    // Durée sec. en msec.

  private int secondes = 0;						// Compteur de secondes
	
  private Thread thread;

// Constructeur
    public Emetteur (int dureeSeconde) {
        this.dureeSeconde = dureeSeconde;

        addObserver(new VueEmetteur(this));
        thread = new Thread(this);
        thread.start();
    }

    private void heureMettreAJour () {
        secondes = ++secondes % 60;
        setChanged();
        notifyObservers();
    }

    public int getSecondes() {
        return secondes;
    }

    public void run() {
        while(true){
            try {
                Thread.sleep(dureeSeconde);
            } catch(InterruptedException e){
                System.err.println(e.getMessage());
            }
            heureMettreAJour();
        }
    }
}
