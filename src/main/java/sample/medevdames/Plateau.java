package sample.medevdames;
import static sample.medevdames.TestDames.TAILLE_PLATEAU;

// Classe représentant le plateau de jeu
public class Plateau {
    public Piece[][] cases;

    public Plateau() {
        this.cases = new Piece[TAILLE_PLATEAU][TAILLE_PLATEAU];
        for (int i = 0; i < TAILLE_PLATEAU; i++) {
            for (int j = 0; j < TAILLE_PLATEAU; j++) {
                this.cases[i][j] = null;
            }
        }
    }

    public void placerPiece(Piece piece, Case position) {
        this.cases[position.x][position.y] = piece;
    }
}