package models; /****************************************************************
 * Auteur:	    Eric Lefrançois                                 *
 * Groupe:	    HES_SO      Informatique & Télécommunications   *
 * Fichier:     Emetteur.java                                   *
 * Date :	      1er Octobre 2016    - Départ             		    *
 * Projet:	    Horloges synchronisées                          *
 ****************************************************************
*/


import javax.swing.*;
import java.awt.*;
import java.util.Observable;


public class Emetteur extends JFrame implements Runnable {

  private final static int LARGEUR = 100;		// largeur fenêtre de l'emetteur
  private final static int HAUTEUR = 100;		// hauteur fenêtre de l'emetteur

  private int dureeSeconde;                    // Durée sec. en msec.

  private int secondes = 0;						// Compteur de secondes

  private JLabel champAffichage = new JLabel("00");
  private Font fonte = new Font ("TimeRoman",  Font.BOLD, 80);
	
  private Thread thread;

// Constructeur
    public Emetteur (int dureeSeconde) {
        this.dureeSeconde = dureeSeconde;
        getContentPane().add("North", champAffichage); 
        champAffichage.setSize(LARGEUR, HAUTEUR);
        champAffichage.setFont (fonte);
        setTitle("Emetteur");

        pack();
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        thread = new Thread(this);
        thread.start();
    }

    private void heureMettreAJour () {
        secondes = ++secondes % 60;
        champAffichage.setText (String.valueOf(secondes));
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
