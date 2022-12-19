package sample.medevdames;

import java.util.ArrayList;
import java.util.List;

public class Pion {

    private int posX;
    private int posY;
    private boolean dame;

    public Pion(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public List<Pion> coupsPossibles(){

        return new ArrayList<>();
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
}
