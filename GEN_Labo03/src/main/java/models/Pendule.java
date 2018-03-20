package models; /********************************************************************
 * Auteur:	    Eric Lefrançois                                     *
 * Groupe:	    HES_SO/EIG  Informatique & Télécommunication        *
 * Fichier:     Pendule.java                                        *
 * Date :	    1er Octobre 2015-  DEPART                 		    *
 * Projet:	    Horloges synchronisées                              *
 ********************************************************************
*/


import views.VuePendule;

import java.awt.*;
import java.lang.Math;
import java.awt.event.*;
import java.util.Observable;
import javax.swing.*;




public class Pendule extends Observable implements Runnable{
//Classe qui décrit une montre avec un affichage des aiguilles
	
	private int dureeSeconde;       // Durée de la seconde en msec.
    private int minutes = 0;       	// Compteurs de la pendule
    private int secondes = 0;
    private int heures = 0;
    private Thread thread;

    //------------------------------------------------------------------------
    public Pendule (String nom, int valSeconde, int posX, int posY) {
        addObserver(new VuePendule(nom,posX,posY,this));

	    dureeSeconde = valSeconde;

		thread = new Thread(this);
		thread.start();
   }

    public void incrementerSecondes(){
    	secondes ++;
        if (secondes == 60) {   
        	secondes = 0;
        	incrementerMinutes();
        }
    }

    public void incrementerMinutes() {
      minutes = ++minutes % 60 ;
      if (minutes == 0) {
          heures = ++heures % 24;
      }
    }

	public void run() {
    	while(true){
    		try {
				Thread.sleep(dureeSeconde);
			} catch(InterruptedException e){
    			System.err.println(e.getMessage());
			}
			incrementerSecondes();
    		setChanged();
			notifyObservers();
		}
	}

	public int getSecondes(){return secondes;}

	public int getMinutes(){return minutes;}

	public int getHeures(){return heures;}

	public String toString(){
        return heures + ":" + minutes + ":" + secondes;
    }

}
