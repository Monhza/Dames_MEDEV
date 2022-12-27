package sample.medevdames;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Classe coup
 * Un coup est une action possible par le joueur
 *
 * Deux utilisations sont faites de cette classe. On l'initialise d'abord dans chaque pion. Dans ce cas, l'instance de
 * Coup représente les caractéristiques actuelles du pion
 *
 * Dans l'autre cas, Une instance de Coup représente un coup possible pour ce pion
 */
public class Coup {

    public Pion pionImplique;
    private int X;
    private int Y;
    public Plateau plateau;
    public List<Pion> pionsManges;
    private int devant;

    /**
     * Constructeur de la classe.
     * Est appelé lorsqu'une instance est initialisée par un Pion (premier cas)
     * @param pionImplique
     * @param plateau
     */
    public Coup(Pion pionImplique, Plateau plateau) {
        this.pionImplique = pionImplique;
        this.plateau = plateau;

        this.X = pionImplique.getPosX();
        this.Y = pionImplique.getPosY();

        this.pionsManges = new ArrayList<>();

        if (pionImplique.estPionBlanc()){
            this.devant = 1;
        } else {
            this.devant = -1;
        }

    }

    /**
     * constructeur de copie
     * @param coup coup à copier
     */
    public Coup(Coup coup) {
        this.pionImplique = coup.pionImplique;
        this.X = coup.X;
        this.Y = coup.Y;
        this.plateau = coup.plateau;
        this.pionsManges = coup.pionsManges;
        this.devant = coup.devant;
    }

    /**
     * Constructeur secondaire. Appelée par la classe coup elle même.
     * Les instances de coups instanciées par cette méthode représentent les coups possibles à jouer pour un pion
     * (Deuxième cas)
     * @param coupPrecedent
     * @param X
     * @param Y
     */
    public Coup(Coup coupPrecedent, int X, int Y) {
        this(coupPrecedent);
        this.X = X;
        this.Y = Y;
    }

    /**
     * Constructeur secondaire. Appelée par la classe coup elle même.
     * Les instances de coups instanciées par cette méthode représentent les coups possibles à jouer pour un pion
     * (Deuxième cas)
     * @param coupPrecedent
     * @param X
     * @param Y
     * @param pionMange
     */
    public Coup(Coup coupPrecedent, int X, int Y, Pion pionMange) {
        this(coupPrecedent, X, Y);
        if (this.pionsManges == null){
            this.pionsManges = new ArrayList<>();
        }
        this.pionsManges.add(pionMange);
    }

    /**
     * Renvoie les coups possibles impliquant de manger un pion adverse pour le pion impliqué
     * @return
     */
    public List<Coup> coupsPrises(){

        List<Coup> listCoupsPrise = new ArrayList<>();

        int x = this.X;
        int y = this.Y;

        int[] direction = new int[]{0, 1, 2, 3};
        int[] facteur;
        // Les prises peuvent se faire en avant et en arrière. Pour les dames mais aussi pour les pions
        if (!pionImplique.isDame()) {
            // Le facteur indique la "portée" du pion
            facteur = new int[]{1};
        } else {
            // La dame a une portée infinie
            facteur = IntStream.range(1, Jeu.TAILLE_PLATEAU).toArray();
        }

        Pion pionAdverse = null;
        Coup nouveauCoup;
        int[] caseDapres;
        int[] deplacement;
        for (int dir : direction){
            for (int fac : facteur){
                deplacement = deplacementDiagonal(dir, fac);
                pionAdverse = plateau.isPionAdverse(!pionImplique.estPionBlanc(),
                        deplacement[0], deplacement[1]);

                // On vérifie s'il y a un pion sur la diagonale du pion considéré
                if (pionAdverse == null) {
                    continue;
                }

                // Si la case d'après le pion n'est pas vide, la prise est impossible
                caseDapres = deplacementDiagonal(dir, fac + 1);
                if (!plateau.estDisponible(caseDapres[0], caseDapres[1])){
                    break;
                }

                int newFacteur;
                int[] casePossible;
                // Si les conditions sont réunies, on donne la possibilité au joueur de faire le coup
                for (int facPosPrise : facteur){
                    newFacteur = facPosPrise + fac;
                    casePossible = deplacementDiagonal(dir, newFacteur);

                    if (!plateau.estDisponible(casePossible[0], casePossible[1])){
                        break;
                    }

                    nouveauCoup = new Coup(this,casePossible[0], casePossible[1]);
                    nouveauCoup.addPionMange(pionAdverse);

                    listCoupsPrise.add(nouveauCoup);
                }
            }
        }
        return listCoupsPrise;
    }

    /**
     * Renvoie les coups de déplacement possibles pour le pion impliqué
     * @return
     */
    public List<Coup> coupsDeplacement(){
        ArrayList<Coup> listeDepl = new ArrayList<>();

        int[] direction;
        int[] facteur;
        // Les deux premières directions sont celles qui vont "en avant", une dame peut aussi se déplacer en arrière
        if (!pionImplique.isDame()) {
            direction = new int[]{0, 1};
            // Le facteur indique la "portée" du pion
            facteur = new int[]{1};
        } else { // Dans le cas où le pion est une dame, on peut aller dans toutes les directions et aussi loin que l'on veut
            direction = new int[]{0, 1, 2, 3};
            facteur = IntStream.range(1, Jeu.TAILLE_PLATEAU).toArray();
        }

        int[] deplacement;
        for (int dir : direction){
            for (int fac : facteur){
                deplacement = deplacementDiagonal(dir, fac);
                if (plateau.estDisponible(deplacement[0], deplacement[1])){
                    listeDepl.add(new Coup(this, deplacement[0], deplacement[1]));
                } else {
                    // Dans le cas d'une dame, si un déplacement diagonal est bloqué par un obstacle, on ne peut pas aller plus loin
                    break;
                }
            }
        }
        return listeDepl;
    }

    /**
     * Renvoie une case sur la diagonale du pion impliqué
     * @param direction : De 0 à 3, c'est la direction (nord, est, sud, ouest)
     * @param facteur : Distance de la case au pion
     * @return
     */
    private int[] deplacementDiagonal(int direction, int facteur){
        int[] xAutour = new int[]{-1, 1, -1, 1};
        int[] yAutour = new int[]{1, 1, -1, -1};

        int x = this.X + xAutour[direction] * facteur;
        int y = this.Y + yAutour[direction] * this.devant * facteur;

        return new int[]{x,y};
    }

    /**
     * L'appel de cette méthode indique que l'on décide de jouer ce coup.
     * Donne au pion les caractéristiques proposée par cette instance de coup
     */
    public void jouerCoup(){
        this.pionImplique.setPosX(this.X);
        this.pionImplique.setPosY(this.Y);

        if (!pionsManges.isEmpty()) {
            for (Pion pion : pionsManges) {
                plateau.mortDuPion(pion);
            }
        }
    }

    /**
     * Affichage du déroulement de ce coup
     */
    public void affiche(){
        System.out.print("Pion " + pionImplique.getStr());
        if (!pionsManges.isEmpty()){
            System.out.print(" mange ");
            for (Pion pion : pionsManges){
                System.out.print(pion.getStr() + " ");
            }
            System.out.print("puis");
        }
        System.out.print(" se deplace en " + "( " + this.X + " ; " + this.Y + " )");
    }

    /**
     * Ajout d'un pion à la liste des pions qui sera mangés lors de ce coup (s'il est effectué)
     * @param pionMange
     */
    private void addPionMange(Pion pionMange){
        this.pionsManges.add(pionMange);
    }

    public Pion getPionJoueur(){
        return pionImplique;
    }
}
