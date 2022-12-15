package sample.medevdames;

import java.util.List;

// Classe représentant une partie de dames
public class Partie {
    public Plateau plateau;
    public String joueurCourant;
    public List<Piece> piecesBlanches;
    public List<Piece> piecesNoires;
    public List<Coup> mouvementsPossibles;

    public Partie() {
        this.plateau = new Plateau();
        this.joueurCourant = "blanc";
        this.piecesBlanches = new ArrayList<Piece>();
        this.piecesNoires = new ArrayList<Piece>();
        this.initialiser();
        this.mouvementsPossibles = new ArrayList<Coup>();
    }

    public void initialiser() {
        // Placer les pièces au début de la partie
        for (int i = 0; i < TAILLE_PLATEAU; i++) {
            for (int j = 0; j < TAILLE_PLATEAU; j++) {
                if ((i + j) % 2 == 0) {
                    if (i < 3) {
                        this.piecesNoires.add(new Piece("noir", new Case(i, j)));
                        this.plateau.placerPiece(this.piecesNoires.get(this.piecesNoires.size() - 1), new Case(i, j));
                    } else if (i > 4) {
                        this.piecesBlanches.add(new Piece("blanc", new Case(i, j