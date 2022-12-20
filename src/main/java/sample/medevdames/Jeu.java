package sample.medevdames;

import java.util.List;

public class Jeu {
    public static int TAILLE_PLATEAU;
    public boolean jeuEnCours;
    public String messageFin;
    private Plateau plateauJeu;

    public void Jeu() {
        
    }

    public void runJeu() {

    }

    public void joueTour() {

    }

    /**
     * affiche le plateau dans la console
     * N pour noir
     * B pour blanc
     * préfixe X pour dame
     */
    public void affiche() {
        // On parcourt toutes les lignes du plateau
        for (int i = 0; i < TAILLE_PLATEAU; i++) {
          // On parcourt toutes les colonnes du plateau
          for (int j = 0; j < TAILLE_PLATEAU; j++) {
            // On cherche si il y a un pion blanc ou noir à ces coordonnées
            Pion pionBlanc = getPion(plateauJeu.getPionB(), i, j);
            Pion pionNoir = getPion(plateauJeu.getPionN(), i, j);
            // Si on a trouvé un pion blanc, on affiche le caractère correspondant
            if (pionBlanc != null) {
                if(pionBlanc.isDame()){
                    System.out.print("XB");
                }
                else {
                    System.out.print("B ");
                } 
            }
            // Si on a trouvé un pion noir, on affiche le caractère correspondant
            else if (pionNoir != null) {
              if(pionNoir.isDame()){
                    System.out.print("XN");
                }
                else {
                    System.out.print("N ");
                } 
            }
            // Sinon, on affiche un espace
            else {
              System.out.print("  ");
            }
          }
          // On passe à la ligne suivante
          System.out.println();
        }
    }
    
    /**
     * Méthode qui retourne le pion situé aux coordonnées x, y dans la liste de pions, ou null s'il n'y a pas de pion à ces coordonnées
     */
    private Pion getPion(List<Pion> pions, int x, int y) {
        for (Pion pion : pions) {
            if (pion.getPosX() == x && pion.getPosY() == y) {
                return pion;
            }
        }
        return null;
    }

    /**
     * 
     */
    public void resteDeuxCouleurs() {

    }
}
