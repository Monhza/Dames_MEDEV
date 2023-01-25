package sample.medevdames;

import java.util.List;
import java.util.Scanner;

/**
 * Classe qui permet de gérer un jeu de dames
 */
public class Jeu {
    public static int TAILLE_PLATEAU = 10;
    public boolean jeuEnCours;
    //public String messageFin; //suppression de cet attribut qui complique les choses pour trouver et afficher la fin -vince
    private Plateau plateauJeu;

    /**
     * constructeur de Jeu
     * appelle le constructeur par défaut de plateau
     */
    public Jeu() {
        this.jeuEnCours = true;
        this.plateauJeu = new Plateau();
        this.initialiseJoueurs();
    }

    
    
    /**
     * à appeler dans main pour jouer
     * contient la boucle dans laquelle on enchaine les tours de jeu
     */
    public void runJeu() {
        while (true) {
            System.out.println("------------------------------------------------------------"); //on sépare les tours par des lignes pour y voir plus clair
            affiche();
            System.out.println("Tour du joueur blanc");
            System.out.println(plateauJeu.getJoueurB().getPetitNom() + " c'est a toi");
            System.out.println();
            // Si la méthode joue tour renvoie false, ça veut dire qu'aucun mouvement de la part du joueur n'est possible
            // C'est soit qu'il n'a plus de pions, soit qu'il est bloqué
            // Dans les deux cas, c'est le joueur adverse qui gagne
            if (!plateauJeu.getJoueurB().joueTour()){
                //FIXME si cette boucle se déclenche, il faut trouver la cause de fin et la donner dans messageFin
                ecrireMessageFin("blanc");
                break;
            }

            System.out.println("------------------------------------------------------------");
            affiche();
            System.out.println("Tour du joueur noir\n");
            System.out.println(plateauJeu.getJoueurN().getPetitNom() + " c'est a toi");
            System.out.println();
            if (!plateauJeu.getJoueurN().joueTour()){
                //FIXME si cette boucle se déclenche, il faut trouver la cause de fin et la donner dans messageFin
                ecrireMessageFin("noir");
                break;
            }
        }
    }

    /**
     * Méthode qui initialise les paramètres des joueurs
     * Dans cette version, ce sont les joueurs qui choisissent leur couleur
     * FIXME Trop top ton truc. T'as la foi dde faire l'affichage dasn une frame à part (voir JPanel de Java)
     */
    private void initialiseJoueurs(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Qui sont les joueurs ? ");
        System.out.print("Joueur Blanc : ");
        plateauJeu.getJoueurB().setPetitNom(scan.next());
        System.out.print("Joueur Noir : ");
        plateauJeu.getJoueurN().setPetitNom(scan.next());
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
            // On cherche s'il y a un pion blanc ou noir à ces coordonnées
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
     * C'est une méthode qui permet de vérifier qu'il y a encore les deux couleurs sur le plateau
     * Si non, elle renvoie false
     * @return true s'il reste des pions des deux couleurs
     */
    public boolean resteDeuxCouleurs() {
        return !plateauJeu.getPionB().isEmpty() && !plateauJeu.getPionN().isEmpty();
    }

    /**
     * Trouve la cause de la fin du jeu et affiche le message de fin
     */
    private void ecrireMessageFin(String couleur){
        if (resteDeuxCouleurs()){
            System.out.println("le joueur " + couleur + " a perdu car il n'a plus de coups valides !");
        }
        else {
            System.out.println("le joueur " + couleur + " a gagné car son adversaire n'a plus de pions !");
        }
    }
}
