package sample.medevdames;

import java.util.List;

public class Jeu {
    public static int TAILLE_PLATEAU;
    public boolean jeuEnCours;
    public String messageFin;
    private Plateau plateauJeu;

    /**
     * constructeur de Jeu
     * appelle le constructeur par défaut de plateau
     */
    public Jeu() {
        this.jeuEnCours = true;
        this.messageFin = "c'est finiiiiiiiiiiiiiiii";
        this.plateauJeu = new Plateau();
    }

    
    
    /**
     * à appeler dans main pour jouer
     * contient la boucle dans laquelle on enchaine les tours de jeu
     */
    public void runJeu() {
        //on met un compteur de tours
        int tour = 0;
        while (tour < 100) { //limite arbitraire de tours
            tour += 1;
            System.out.println("------------------------------------------------------------"); //on sépare les tours par des lignes pour y voir plus clair
            System.out.println("début du tour " + tour);
            affiche();
            System.out.println("tour du joueur blanc");
            plateauJeu.getJoueurB().joueTour();
            System.out.println("tour du joueur noir");
            plateauJeu.getJoueurN().joueTour();
            //FIXME
        }
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
     * utile pour l'affichage
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
     * ça sert à quoi ?
     */
    public void resteDeuxCouleurs() {

    }
}
