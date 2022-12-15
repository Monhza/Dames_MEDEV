package sample.medevdames;
import static sample.medevdames.TestDames.TAILLE_PLATEAU;

// Classe représentant une case sur le plateau de jeu
public class Case {
    public int x;
    public int y;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean estValide() {
        return this.x >= 0 && this.x < TAILLE_PLATEAU && this.y >= 0 && this.y < TAILLE_PLATEAU;
    }
}