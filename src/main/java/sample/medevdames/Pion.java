package sample.medevdames;

import java.util.List;

/**
 * Pion présent sur le plateau
 */
public class Pion {

    private int posX;
    private int posY;
    private boolean pionBlanc;
    private boolean dame;
    private Coup coup;
    private Plateau plateau;

    /**
     * Constructeur
     * @param posX
     * @param posY
     * @param pionBlanc
     * @param plateau
     */
    public Pion(int posX, int posY, boolean pionBlanc, Plateau plateau) {
        this.posX = posX;
        this.posY = posY;
        this.pionBlanc = pionBlanc;
        this.dame = false;
        this.plateau = plateau;
    }

    /**
     * Renvoie les coups de "prise" possibles pour ce pion
     * @return
     */
    public List<Coup> getCoupsPrises(){
        this.coup = new Coup(this, plateau);
        return this.coup.coupsPrises();
    }

    /**
     * Renvoie les coups de déplacement possibles pour ce pion
     * @return
     */
    public List<Coup> getCoupsDeplacement(){
        this.coup = new Coup(this, plateau);
        return this.coup.coupsDeplacement();
    }

    /**
     * Renvoie la chaine de caractères indiquant les coordonnées du pion
     * @return
     */
    public String getStr(){
        return "( " + this.posX + " ; " + this.posY + " )" ;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    public boolean estPionBlanc(){
        return this.pionBlanc;
    }

    public boolean isDame() {
        return dame;
    }

    public void setDame(boolean dame) {
        this.dame = dame;
    }
}
