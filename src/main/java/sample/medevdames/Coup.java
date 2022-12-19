package sample.medevdames;

import java.util.ArrayList;
import java.util.List;

public class Coup {

    public Pion pionImplique;
    public List<Pion> pionsAdverses;
    public List<Pion> pionsManges;
    private Coup coupPrecedent;
    public Boolean obligatoire;
    public Boolean etape;

    public Coup(Pion pionImplique, List<Pion> pionsAdverses) {
        this.pionImplique = pionImplique;
        this.pionsAdverses = pionsAdverses;
    }

    public Coup(Pion pionImplique, List<Pion> pionsAdverses, List<Pion> pionsManges) {
        this.pionImplique = pionImplique;
        this.pionsAdverses = pionsAdverses;
        this.pionsManges = pionsManges;
    }

    private List<Coup> coupsPossibles(){

        return new ArrayList<>();
    }

    private List<Coup> coupsPrises(){

        return new ArrayList<>();
    }

    private List<Coup> coupsDeplacement(){

        return new ArrayList<>();
    }
}
