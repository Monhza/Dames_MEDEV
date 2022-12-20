package sample.medevdames;

import java.util.LinkedList;

public class Plateau {

    private LinkedList<Pion> pionB;
    private LinkedList<Pion> pionN;
    private Joueur joueurB;
    private Joueur joueurN;
    private int ligneDamesB;

    public LinkedList<Pion> getPionB() {
        return pionB;
    }

    public void setPionB(LinkedList<Pion> pionB) {
        this.pionB = pionB;
    }

    public LinkedList<Pion> getPionN() {
        return pionN;
    }

    public void setPionN(LinkedList<Pion> pionN) {
        this.pionN = pionN;
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

    public int getLigneDamesB() {
        return ligneDamesB;
    }

    public void setLigneDamesB(int ligneDamesB) {
        this.ligneDamesB = ligneDamesB;
    }

    public int getLigneDamesN() {
        return ligneDamesN;
    }

    public void setLigneDamesN(int ligneDamesN) {
        this.ligneDamesN = ligneDamesN;
    }
    private int ligneDamesN;

    public void Plateau(Joueur joueurB, Joueur joueurN) {

    }

    private void initialisationPlateau() {

    }

    public LinkedList<Coup> coupsPossibles(Joueur joueur) {
        return null;
    }

    public Joueur plusDePions() {
        return null;
    }

    public void majDames() {

    }
}
