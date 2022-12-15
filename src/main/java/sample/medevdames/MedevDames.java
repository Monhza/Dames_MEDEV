/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package sample.medevdames;

/**
 * @author vince
 */
public class MedevDames {

    // Constantes globales
    public static final int TAILLE_PLATEAU = 8;
}

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //modif
    }

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