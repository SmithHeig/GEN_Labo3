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
import java.util.Observer;
import javax.swing.*;




public class Pendule extends Observable implements Runnable, Observer{
//Classe qui décrit une montre avec un affichage des aiguilles
	
	private int dureeSeconde;       // Durée de la seconde en msec.
    private int minutes = 0;       	// Compteurs de la pendule
    private int secondes = 0;
    private int heures = 0;
    private Thread thread;
    private boolean wait;

    //------------------------------------------------------------------------
    public Pendule (String nom, int valSeconde, int posX, int posY) {
        addObserver(new VuePendule(nom,posX,posY,this));

	    dureeSeconde = valSeconde;
	    wait = false;

		thread = new Thread(this);
		thread.start();
   }

    public void incrementerSecondes(){
        if(wait){
            System.out.println(secondes);
        }
    	secondes ++;
    	if(secondes == 59){
            wait = true;
        }
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
    	while(true) {
            try {
                Thread.sleep(dureeSeconde);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }

            if (!wait) {
                incrementerSecondes();

                setChanged();
                notifyObservers();
            }
        }
	}

	public int getSecondes(){return secondes;}

	public int getMinutes(){return minutes;}

	public int getHeures(){return heures;}

	public String toString(){
        return heures + ":" + minutes + ":" + secondes;
    }

    public void update(Observable o, Object arg) {
        Boolean update = (Boolean) arg;
        if(update.booleanValue() == true){
            secondes = 59;
            wait = false;
        }
    }
}
