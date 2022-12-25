package sample.medevdames;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe qui représente le plateau de jeu et ce qui se trouve dessus
 */
public class Plateau {

    public LinkedList<Pion> pionB;
    public LinkedList<Pion> pionN;
    private Joueur joueurB;
    private Joueur joueurN;
    private int ligneDamesB;
    private int ligneDamesN;

    /**
     * Constructeur du plateau
     */
    public Plateau() {
        this.joueurB = new Joueur(this);
        this.joueurN = new Joueur(this);
        this.ligneDamesN = 0;
        this.ligneDamesB = Jeu.TAILLE_PLATEAU - 1;

        this.initialisationPlateau();
    }

    /**
     * Initialisation du plateau
     * Permet de placer les pions en quinconce sur 4 lignes
     */
    private void initialisationPlateau() {
        this.pionB = new LinkedList<>();
        this.pionN = new LinkedList<>();

        // Initialisation des pions blancs
        // 4 lignes de pions
        for (int y = 0; y < 4 ; y++){
            // 5 pions par lignes sur une case sur deux
            for (int x = 0 ; x < Jeu.TAILLE_PLATEAU ; x += 2){
                this.pionB.add(new Pion(x + (y%2), y, true, this));
            }
        }

        // Initialisation des pions noirs
        for (int y = Jeu.TAILLE_PLATEAU - 1; y > Jeu.TAILLE_PLATEAU - 5 ; y--){
            // 5 pions par lignes sur une case sur deux
            for (int x = 0 ; x < Jeu.TAILLE_PLATEAU ; x += 2){
                this.pionN.add(new Pion(x + (y%2), y, false, this));
            }
        }
    }

    /**
     * Si une couleur manque sur le plateau, renvoie un pointeur sur le joueur qui n'a plus de pions
     * //FIXME à utiliser
     * @return
     */
    public Joueur plusDePions() {

        if (this.pionB.isEmpty()){
            return this.joueurB;
        }
        else if (this.pionN.isEmpty()){
            return this.joueurN;
        }

        return null;
    }

    /**
     * Mise à jour des dames
     * //FIXME à écrire
     */
    public void majDames() {

    }

    /**
     * Méthode appelée si un pion est mangé
     * @param pion : pion mangé
     */
    public void mortDuPion(Pion pion){
        pionB.remove(pion);
        pionN.remove(pion);
    }

    /**
     * Indique si une position est dans les limites du plateau
     * @param X
     * @param Y
     * @return
     */
    public boolean estDansPlateau(int X, int Y){
        boolean supDehors = X >= Jeu.TAILLE_PLATEAU || Y >= Jeu.TAILLE_PLATEAU;
        boolean infDehors = X < 0 || Y < 0;
        if (supDehors || infDehors){
            return false;
        }
        return true;
    }

    /**
     * Retourne si la case indiquée est sur le plateau (et pas en dehors du jeu) et vide (sans pion dessus)
     * @param X : pos X
     * @param Y : pos Y
     * @return true si la case est disponible
     */
    public boolean estDisponible(int X, int Y){

        // Vérifie si la position indiquée est dans la carte
        if (!estDansPlateau(X, Y)){
            return false;
        }

        // Vérifie qu'aucun pion n'est sur la case
        for (Pion pion : pionB){
            if (pion.getPosX() == X && pion.getPosY() == Y){
                return false;
            }
        }
        for (Pion pion : pionN){
            if (pion.getPosX() == X && pion.getPosY() == Y){
                return false;
            }
        }

        // Si toutes les conditions sont bonnes, alors la case est libre
        return true;
    }

    /**
     * Revoie un pion parmis les "pions adversaire" s'il est sur la case indiquée
     * @param isAdversaireBlanc : booleen, si true, alors les "pions adversaires" sont les blancs
     * @param X : pos X indiquée
     * @param Y : pos Y indiquée
     * @return null si aucun pion averse sur la case, true sinon
     */
    public Pion isPionAdverse(boolean isAdversaireBlanc, int X, int Y){

        List<Pion> pionsAdverses;

        // Vérifie si la position indiquée est dans la carte
        if (!estDansPlateau(X, Y)){
            return null;
        }

        // Vérifie si un pion est sur la case
        if (isAdversaireBlanc){
            pionsAdverses = pionB;
        } else {
            pionsAdverses = pionN;
        }

        // Cherche dans les pions de l'adversaire si l'un deux est aux coordonnées indiquées
        for (Pion pion : pionsAdverses){
            if (pion.getPosX() == X && pion.getPosY() == Y){
                return pion;
            }
        }

        return null;
    }

    /**
     * Revoie la liste des pions du joueur entré en paramètre
     * @param joueur : joueur dont on veut avoir la liste des pions
     * @return
     */
    public List<Pion> getPionsJoueur(Joueur joueur){
        if (joueur == joueurB){
            return pionB;
        } else {
            return pionN;
        }
    }

    public List<Pion> getPionB(){
        return pionB;
    }

    public List<Pion> getPionN(){
        return pionN;
    }

    public Joueur getJoueurB() {
        return joueurB;
    }

    public void setJoueurB(Joueur joueurB) {
        this.joueurB = joueurB;
    }

    public Joueur getJoueurN() {
        return joueurN;
    }

    public void setJoueurN(Joueur joueurN) {
        this.joueurN = joueurN;
    }
}
