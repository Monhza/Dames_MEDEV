package sample.medevdames.Jeu;

import java.util.Scanner;

public class Dames {

    // constantes
    public static final int DIMENSION = 8;
    public static final char PION_BLANC = 'B';
    public static final char PION_NOIR = 'N';
    // variables d'instance
    private char[][] plateau;
    private char joueurActuel;
    private int statutJeu;

    // constantes pour les différents statuts du jeu
    public static final int EN_COURS = 0;
    public static final int TERMINE = 1;

    public Dames() {
        // initialiser le plateau
        plateau = new char[DIMENSION][DIMENSION];
        initialiserPlateau();

        // initialiser le joueur actuel
        joueurActuel = PION_BLANC;

        // initialiser le statut du jeu
        statutJeu = EN_COURS;
    }

    public void initialiserPlateau() {
        // remplir le plateau avec les pions blancs et noirs selon les règles du jeu de Dames
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if ((i + j) % 2 == 0) {
                    if (i < 3) {
                        plateau[i][j] = PION_NOIR;
                    } else if (i > 4) {
                        plateau[i][j] = PION_BLANC;
                    } else {
                        plateau[i][j] = ' ';
                    }
                } else {
                    plateau[i][j] = ' ';
                }
            }
        }
    }

    public void afficherPlateau() {
        // afficher le plateau de jeu à l'écran
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                System.out.print(plateau[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean deplacerPion(int x1, int y1, int x2, int y2) {
        // vérifier si le mouvement est valide selon les règles du jeu de Dames
        if (plateau[x1][y1] != joueurActuel || plateau[x2][y2] != ' ') {
            return false;
        }

        if (Math.abs(x1 - x2) != 1 || Math.abs(y1 - y2) != 1) {
            return false;
        }

        // mettre à jour le plateau
        plateau[x2][y2] = joueurActuel;
        plateau[x1][y1] = ' ';

        // changer le joueur actuel
        if (joueurActuel == PION_BLANC) {
            joueurActuel = PION_NOIR;
        } else {
            joueurActuel = PION_BLANC;
        }

        return true;
    }

    public int obtenirStatutJeu() {
        // vérifier si un joueur a gagné ou si la partie est terminée
        int nbPionsBlancs = 0;
        int nbPionsNoirs = 0;
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (plateau[i][j] == PION_BLANC) {
                    nbPionsBlancs++;
                } else if (plateau[i][j] == PION_NOIR) {
                    nbPionsNoirs++;
                }
            }
        }

        if (nbPionsBlancs == 0) {
            statutJeu = TERMINE;
            System.out.println("Les pions noirs ont gagné !");
        } else if (nbPionsNoirs == 0) {
            statutJeu = TERMINE;
            System.out.println("Les pions blancs ont gagné !");
        }

        return statutJeu;
    }

    public static void main(String[] args) {
        Dames jeu = new Dames();

        // boucle principale pour gérer le déroulement du jeu
        while (jeu.obtenirStatutJeu() == EN_COURS) {
            // afficher le plateau
            jeu.afficherPlateau();

            // demander au joueur actuel de saisir un mouvement
            Scanner scanner = new Scanner(System.in);
            System.out.print("Joueur " + jeu.joueurActuel + ", saisissez les coordonnées de départ et d'arrivée : ");
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            // effectuer le mouvement
            if (!jeu.deplacerPion(x1, y1, x2, y2)) {
                System.out.println("Mouvement non valide !");
            }
        }
    }
}

