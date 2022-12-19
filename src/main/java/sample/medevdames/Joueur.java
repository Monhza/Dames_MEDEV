package sample.medevdames;

import java.util.ArrayList;
import java.util.List;

public class Joueur {

    private String petitNom;
    private String couleurPions;
    private Plateau plateauJeu;

    public Joueur(String petitNom) {
        this.petitNom = petitNom;
    }

    public boolean joueTour() {

        return true;
    }

    private void proposeMouvement() {

    }

    private void proposePrise() {

    }

    public List<Pion> getCoupsPossibles(){

        return new ArrayList<>();
    }

    public String getPetitNom() {
        return petitNom;
    }

    public void setPetitNom(String petitNom) {
        this.petitNom = petitNom;
    }

    public String getCouleurPions() {
        return couleurPions;
    }

    public void setCouleurPions(String couleurPions) {
        this.couleurPions = couleurPions;
    }

    public Plateau getPlateauJeu() {
        return plateauJeu;
    }

    public void setPlateauJeu(Plateau plateauJeu) {
        this.plateauJeu = plateauJeu;
    }
}
