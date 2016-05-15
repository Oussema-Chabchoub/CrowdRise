package pi.entities;

import java.sql.Date;


public class Membre {

    public Membre() {
    }

    public Membre(String nomMembre, String prenomMembre, String adresseMailMembre) {
        this.nomMembre = nomMembre;
        this.prenomMembre = prenomMembre;
        this.adresseMailMembre = adresseMailMembre;
    }

    public Membre(int id, String nomMembre, String prenomMembre, String adresseMailMembre, String adresseMembre, int numTelMembre, int ribMembre, String secteurActiveMembre) {
        this.id = id;
        this.nomMembre = nomMembre;
        this.prenomMembre = prenomMembre;
        this.adresseMailMembre = adresseMailMembre;
        this.adresseMembre = adresseMembre;
        this.numTelMembre = numTelMembre;
        this.ribMembre = ribMembre;
        this.secteurActiveMembre = secteurActiveMembre;
    }

    public Membre(String nomMembre, String prenomMembre, String nomUtilisateurMembre, String adresseMailMembre, String motDePasseMembre, String paysMembre, int numTelMembre, int ribMembre, String competenceMembre) {
        this.nomMembre = nomMembre;
        this.prenomMembre = prenomMembre;
        this.nomUtilisateurMembre = nomUtilisateurMembre;
        this.adresseMailMembre = adresseMailMembre;
        this.motDePasseMembre = motDePasseMembre;
        this.paysMembre = paysMembre;
        this.numTelMembre = numTelMembre;
        this.ribMembre = ribMembre;
        this.competenceMembre = competenceMembre;
    }

    public Membre(String nomMembre, String prenomMembre, String nomUtilisateurMembre, String adresseMailMembre, String motDePasseMembre, String adresseMembre, String paysMembre, int numTelMembre, String motivationMembre, int ribMembre, String competenceMembre, String secteurActiveMembre) {
        this.nomMembre = nomMembre;
        this.prenomMembre = prenomMembre;
        this.nomUtilisateurMembre = nomUtilisateurMembre;
        this.adresseMailMembre = adresseMailMembre;
        this.motDePasseMembre = motDePasseMembre;
        this.adresseMembre = adresseMembre;
        this.paysMembre = paysMembre;
        this.numTelMembre = numTelMembre;
        this.motivationMembre = motivationMembre;
        this.ribMembre = ribMembre;
        this.competenceMembre = competenceMembre;
        this.secteurActiveMembre = secteurActiveMembre;
    }

    public Membre(String imageMembre, String nomMembre, String prenomMembre, String nomUtilisateurMembre, String adresseMailMembre, String motDePasseMembre, Date dateNaissanceMembre, String paysMembre, int numTelMembre, int ribMembre, String competenceMembre) {
        this.imageMembre = imageMembre;
        this.nomMembre = nomMembre;
        this.prenomMembre = prenomMembre;
        this.nomUtilisateurMembre = nomUtilisateurMembre;
        this.adresseMailMembre = adresseMailMembre;
        this.motDePasseMembre = motDePasseMembre;
        this.dateNaissanceMembre = dateNaissanceMembre;
        this.paysMembre = paysMembre;
        this.numTelMembre = numTelMembre;
        this.ribMembre = ribMembre;
        this.competenceMembre = competenceMembre;
    }

    public Membre( String imageMembre, String nomMembre, String prenomMembre, String nomUtilisateurMembre, String adresseMailMembre, String motDePasseMembre, String paysMembre, int numTelMembre, int ribMembre, String competenceMembre) {
        
        this.imageMembre = imageMembre;
        this.nomMembre = nomMembre;
        this.prenomMembre = prenomMembre;
        this.nomUtilisateurMembre = nomUtilisateurMembre;
        this.adresseMailMembre = adresseMailMembre;
        this.motDePasseMembre = motDePasseMembre;
        this.paysMembre = paysMembre;
        this.numTelMembre = numTelMembre;
        this.ribMembre = ribMembre;
        this.competenceMembre = competenceMembre;
    }
    
  private int id;

private String imageMembre;
private String nomMembre;
private int solde;
private String prenomMembre;

private String nomUtilisateurMembre;

private String adresseMailMembre;

private String motDePasseMembre;

private String villeNaissanceMembre;

private Date dateNaissanceMembre;

private String nationaliteMembre;

private String adresseMembre;

private String villeMembre;

private String paysMembre;

private int numTelMembre;

private String motivationMembre;

private String situationProMembre;

private int etatCompteMembre;

private String typeCompteMembre;

private int ribMembre;

private String competenceMembre;

private String cvMembre;

    public Membre(int id,String nomMembre, String prenomMembre, String adresseMailMembre, int numTelMembre, int ribMembre) {
        this.id=id;
        this.nomMembre = nomMembre;
        this.prenomMembre = prenomMembre;
        this.adresseMailMembre = adresseMailMembre;
        this.numTelMembre = numTelMembre;
        this.ribMembre = ribMembre;
    }
    

private String secteurActiveMembre;

private String secteurGeoMembre;

    public String getImageMembre() {
        return imageMembre;
    }

    public void setImageMembre(String imageMembre) {
        this.imageMembre = imageMembre;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

  
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomMembre() {
        return nomMembre;
    }

    public void setNomMembre(String nomMembre) {
        this.nomMembre = nomMembre;
    }

    public String getPrenomMembre() {
        return prenomMembre;
    }

    public void setPrenomMembre(String prenomMembre) {
        this.prenomMembre = prenomMembre;
    }

    public String getNomUtilisateurMembre() {
        return nomUtilisateurMembre;
    }

    public void setNomUtilisateurMembre(String nomUtilisateurMembre) {
        this.nomUtilisateurMembre = nomUtilisateurMembre;
    }

    public String getAdresseMailMembre() {
        return adresseMailMembre;
    }

    public void setAdresseMailMembre(String adresseMailMembre) {
        this.adresseMailMembre = adresseMailMembre;
    }

    public String getMotDePasseMembre() {
        return motDePasseMembre;
    }

    public void setMotDePasseMembre(String motDePasseMembre) {
        this.motDePasseMembre = motDePasseMembre;
    }

    public String getVilleNaissanceMembre() {
        return villeNaissanceMembre;
    }

    public void setVilleNaissanceMembre(String villeNaissanceMembre) {
        this.villeNaissanceMembre = villeNaissanceMembre;
    }

    public Date getDateNaissanceMembre() {
        return dateNaissanceMembre;
    }

    public void setDateNaissanceMembre(Date dateNaissanceMembre) {
        this.dateNaissanceMembre = dateNaissanceMembre;
    }

    public String getNationaliteMembre() {
        return nationaliteMembre;
    }

    public void setNationaliteMembre(String nationaliteMembre) {
        this.nationaliteMembre = nationaliteMembre;
    }

    public String getAdresseMembre() {
        return adresseMembre;
    }

    public void setAdresseMembre(String adresseMembre) {
        this.adresseMembre = adresseMembre;
    }

    public String getVilleMembre() {
        return villeMembre;
    }

    public void setVilleMembre(String villeMembre) {
        this.villeMembre = villeMembre;
    }

    public String getPaysMembre() {
        return paysMembre;
    }

    public void setPaysMembre(String paysMembre) {
        this.paysMembre = paysMembre;
    }

    public int getNumTelMembre() {
        return numTelMembre;
    }

    public void setNumTelMembre(int numTelMembre) {
        this.numTelMembre = numTelMembre;
    }

    public String getMotivationMembre() {
        return motivationMembre;
    }

    public void setMotivationMembre(String motivationMembre) {
        this.motivationMembre = motivationMembre;
    }

    public String getSituationProMembre() {
        return situationProMembre;
    }

    public void setSituationProMembre(String situationProMembre) {
        this.situationProMembre = situationProMembre;
    }

    public int getEtatCompteMembre() {
        return etatCompteMembre;
    }

    public void setEtatCompteMembre(int etatCompteMembre) {
        this.etatCompteMembre = etatCompteMembre;
    }

    public String getTypeCompteMembre() {
        return typeCompteMembre;
    }

    public void setTypeCompteMembre(String typeCompteMembre) {
        this.typeCompteMembre = typeCompteMembre;
    }

    public int getRibMembre() {
        return ribMembre;
    }

    public void setRibMembre(int ribMembre) {
        this.ribMembre = ribMembre;
    }

    public String getCompetenceMembre() {
        return competenceMembre;
    }

    public void setCompetenceMembre(String competenceMembre) {
        this.competenceMembre = competenceMembre;
    }

    public String getCvMembre() {
        return cvMembre;
    }

    public void setCvMembre(String cvMembre) {
        this.cvMembre = cvMembre;
    }

    public String getSecteurActiveMembre() {
        return secteurActiveMembre;
    }

    public void setSecteurActiveMembre(String secteurActiveMembre) {
        this.secteurActiveMembre = secteurActiveMembre;
    }

    public String getSecteurGeoMembre() {
        return secteurGeoMembre;
    }

    public void setSecteurGeoMembre(String secteurGeoMembre) {
        this.secteurGeoMembre = secteurGeoMembre;
    }

    
}