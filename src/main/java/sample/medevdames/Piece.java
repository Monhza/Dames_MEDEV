package sample.medevdames;

// Classe représentant une pièce de dames
public class Piece {
    public String couleur;
    public Case position;
    public boolean estDame;

    public Piece(String couleur, Case position) {
        this.couleur = couleur;
        this.position = position;
        this.estDame = false;
    }
}