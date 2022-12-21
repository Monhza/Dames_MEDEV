package sample.medevdames;

import java.util.ArrayList;
import java.util.List;

public class Joueur {

    private String petitNom;
    private String couleurPions;

    /**
     * constructeur de Joueur
     * @param petitNom nom du joueur
     * @param couleurPions "blanc" ou "noir"
     */
    public Joueur(String petitNom, String couleurPions) {
        //on appelera ça dans le constructeur du plateau dans lequel l'assignation de couleurs sera gérée
        this.petitNom = petitNom;
        this.couleurPions = couleurPions;
    }
 
    //?
    public boolean joueTour() {
        return true;
    }

    //FIXME on propose des mouvements possibles aux joueurs du coup ?
    private void proposeMouvement() {

    }

    private void proposePrise() {

    }

    //FIXME renvoie une liste de pions ?
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
}
