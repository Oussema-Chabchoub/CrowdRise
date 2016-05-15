
package pi.entities;

import java.sql.Date;


public class Projet {
    private int id;
    private String titreProjet;
    private String domaineProjet;
    private String descriptionProjet;
    private float budgetProjet;
    private float collecteEnCours;
    private String etatProjet;
    private String imageProjet;
    private String videoProjet;
    private int nbrInteresses;
    private Membre membre;
    private int id_membre; 
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//
    public Projet(int id, String titreProjet, String domaineProjet, String descriptionProjet, float budgetProjet, float collecteEnCours, String username) {
        this.id = id;
        this.titreProjet = titreProjet;
        this.domaineProjet = domaineProjet;
        this.descriptionProjet = descriptionProjet;
        this.budgetProjet = budgetProjet;
        this.collecteEnCours = collecteEnCours;
        this.username = username;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }
    private Date dateProject;

    public Projet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Projet(String titreProjet, String domaineProjet, String descriptionProjet, float budgetProjet,String imageProjet ,String videoProjet,int id_membre) {
        this.titreProjet = titreProjet;
        this.domaineProjet = domaineProjet;
        this.descriptionProjet = descriptionProjet;
        this.budgetProjet = budgetProjet;
        this.imageProjet=imageProjet;
        this.videoProjet = videoProjet;
        this.id_membre = id_membre;
    }

   

    
   

    public int getId() {
        return id;
    }

    public Date getDateProject() {
        return dateProject;
    }

    public void setDateProject(Date dateProject) {
        this.dateProject = dateProject;
    }

    public Projet(int id, String titreProjet, String domaineProjet, String descriptionProjet, float budgetProjet, float collecteEnCours) {
        this.id = id;
        this.titreProjet = titreProjet;
        this.domaineProjet = domaineProjet;
        this.descriptionProjet = descriptionProjet;
        this.budgetProjet = budgetProjet;
        this.collecteEnCours = collecteEnCours;
    }

    

    public Projet(String titreProjet, String domaineProjet, String descriptionProjet, float budgetProjet, String imageProjet, String videoProjet) {
        this.titreProjet = titreProjet;
        this.domaineProjet = domaineProjet;
        this.descriptionProjet = descriptionProjet;
        this.budgetProjet = budgetProjet;
        this.imageProjet = imageProjet;
        this.videoProjet = videoProjet;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitreProjet() {
        return titreProjet;
    }

    public void setTitreProjet(String titreProjet) {
        this.titreProjet = titreProjet;
    }

    public String getDomaineProjet() {
        return domaineProjet;
    }

    public void setDomaineProjet(String domaineProjet) {
        this.domaineProjet = domaineProjet;
    }

    public String getDescriptionProjet() {
        return descriptionProjet;
    }

    public void setDescriptionProjet(String descriptionProjet) {
        this.descriptionProjet = descriptionProjet;
    }

    public float getBudgetProjet() {
        return budgetProjet;
    }

    public void setBudgetProjet(float budgetProjet) {
        this.budgetProjet = budgetProjet;
    }

    public float getCollecteEnCours() {
        return collecteEnCours;
    }

  
    public void setCollecteEnCours(float collecteEnCours) {
        this.collecteEnCours = collecteEnCours;
    }

    public String getEtatProjet() {
        return etatProjet;
    }

    public void setEtatProjet(String etatProjet) {
        this.etatProjet = etatProjet;
    }

    public String getImageProjet() {
        return imageProjet;
    }

    public void setImageProjet(String imageProjet) {
        this.imageProjet = imageProjet;
    }

    public String getVideoProjet() {
        return videoProjet;
    }

    public void setVideoProjet(String videoProjet) {
        this.videoProjet = videoProjet;
    }

    public int getNbrInteresses() {
        return nbrInteresses;
    }

    public void setNbrInteresses(int nbrInteresses) {
        this.nbrInteresses = nbrInteresses;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Projet(String titreProjet, String domaineProjet, String descriptionProjet, float budgetProjet, float collecteEnCours) {
        this.titreProjet = titreProjet;
        this.domaineProjet = domaineProjet;
        this.descriptionProjet = descriptionProjet;
        this.budgetProjet = budgetProjet;
        this.collecteEnCours = collecteEnCours;
    }
    
    
    
    
}
