package menu;

/**
 *
 * @author ghassen jenana
 */
public class Statistique {
    
    int Cptdomaine ;
    String domaine ; 

    public int getCptdomaine() {
        return Cptdomaine;
    }

    public void setCptdomaine(int Cptdomaine) {
        this.Cptdomaine = Cptdomaine;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public Statistique(int Cptdomaine, String domaine) {
        this.Cptdomaine = Cptdomaine;
        this.domaine = domaine;
    }

   
    
    
    
    
}