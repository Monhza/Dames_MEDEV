package sample.medevdames;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Représente un joueur
 */
public class Joueur {

    private String petitNom;
    private Plateau plateau;

    /**
     * constructeur de Joueur
     * @param plateau plateau de jeu
     */
    public Joueur(Plateau plateau) {
        this.plateau = plateau;
    }

    /**
     * Méthode appelée à chaque tour de jeu d'un joueur
     * Le retour renvoie true si une action du joueur est possible, false sinon
     * Le joueur ne peux pas jouer soir car il n'a plus de pions sur le plateau,
     * soit car tous ses pions restants sont bloqués
     * @return boolean
     */
    public boolean joueTour() {

        List<Pion> listePionsJoueur = plateau.getPionsJoueur(this);

        List<Coup> coupsPossibles = new ArrayList<>();
        boolean coupOblige = false;

        // On regarde d'abord si le joueur peut prendre un pion adverse
        // Si tel est le cas, ce coup est obligatoire.
        for (Pion pion : listePionsJoueur){
            coupsPossibles.addAll(pion.getCoupsPrises());
        }

        if (!coupsPossibles.isEmpty()){
            coupOblige = true;
            System.out.println("Le joueur peut prendre un pion adverse");
        }
        else {
            for (Pion pion : listePionsJoueur){
                coupsPossibles.addAll(pion.getCoupsDeplacement());
            }
        }

        // Pas de coups possibles
        if (coupsPossibles.isEmpty()){
            return false;
        }

        Coup coupJoue = proposeCoup(coupsPossibles);
        coupJoue.jouerCoup();

        // Si le joueur a fait une prise et qu'il est encore possible de faire une prise avec le même pion
        // il peut rejouer
        if (coupOblige){
            coupsPossibles = coupJoue.getPionJoueur().getCoupsPrises();

            while(!coupsPossibles.isEmpty()){
                coupJoue = proposeCoup(coupsPossibles);
                coupJoue.jouerCoup();

                coupsPossibles = coupJoue.getPionJoueur().getCoupsPrises();
            }
        }

        return true;
    }

    /**
     * Propose au joueur, dans la console, les coups qu'il peut effectuer, avec leur description
     * @param coupsPossibles
     * @return
     */
    private Coup proposeCoup(List<Coup> coupsPossibles) {
        Scanner scan = new Scanner(System.in);
        String input;

        System.out.println("Vous pouvez jouer les coups : ");
        int index = 0;
        for (Coup coup : coupsPossibles){
            System.out.print("   " + index + " : ");
            coup.affiche();
            System.out.println();
            index++;
        }

        System.out.print("Quel coup voulez vous jouer ? ");
        input = scan.nextLine();

        return coupsPossibles.get(Integer.parseInt(input));
    }

    public String getPetitNom() {
        return petitNom;
    }

    public void setPetitNom(String petitNom) {
        this.petitNom = petitNom;
    }
}
