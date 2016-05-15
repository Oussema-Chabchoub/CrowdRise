/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import java.io.File;
import java.sql.Date;

/**
 *
 * @author Ghassen
 */
public class Problem {
    
private int id;
private String titre;
private String domaineProbleme;
private String descProbleme;
private int nbrInteresse;
private String profilDemande;
private String membre;
private int solutionProbleme;
private int noteSolutionProbleme;
private Date dateAjout;
private String email;
private String etat;
private int views;
private int likes;
private String imageFile;
private int membre_id;
private String username;

    public Problem(int id, String titre, String domaineProbleme, String descProbleme, String profilDemande, String username) {
        this.id = id;
        this.titre = titre;
        this.domaineProbleme = domaineProbleme;
        this.descProbleme = descProbleme;
        this.profilDemande = profilDemande;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMembre_id() {
        return membre_id;
    }

    public void setMembre_id(int membre_id) {
        this.membre_id = membre_id;
    }

    public Problem(String titre, String domaineProbleme, String descProbleme, String profilDemande, int membre_id) {
        this.titre = titre;
        this.domaineProbleme = domaineProbleme;
        this.descProbleme = descProbleme;
        this.profilDemande = profilDemande;
        this.membre_id = membre_id;
    }
    
    public Problem()
    {}
    public Problem(String descProbleme ,String titre)
    {
          this.descProbleme=descProbleme;
          this.titre=titre;
    }
    
    
    public Problem(String domaineProbleme , String profilDemande , String titre , Date dateAjout , String email)
    {this.domaineProbleme=domaineProbleme;
    this.profilDemande=profilDemande ; 
    this.titre=titre;
    this.dateAjout=dateAjout;
    this.email=email;
    
    
    }

    public Problem(int id, String titre, String domaineProbleme, String descProbleme, String profilDemande) {
        this.id = id;
        this.titre = titre;
        this.domaineProbleme = domaineProbleme;
        this.descProbleme = descProbleme;
        this.profilDemande = profilDemande;
    }

    public Problem(String titre, String descProbleme, String imageFile) {
        this.titre = titre;
        this.descProbleme = descProbleme;
        this.imageFile = imageFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomaineProbleme() {
        return domaineProbleme;
    }

    public void setDomaineProbleme(String domaineProbleme) {
        this.domaineProbleme = domaineProbleme;
    }

    public String getDescProbleme() {
        return descProbleme;
    }

    public void setDescProbleme(String descProbleme) {
        this.descProbleme = descProbleme;
    }

    public int getNbrInteresse() {
        return nbrInteresse;
    }

    public void setNbrInteresse(int nbrInteresse) {
        this.nbrInteresse = nbrInteresse;
    }

    public String getProfilDemande() {
        return profilDemande;
    }

    public void setProfilDemande(String profilDemande) {
        this.profilDemande = profilDemande;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMembre() {
        return membre;
    }

    public void setMembre(String membre) {
        this.membre = membre;
    }

    public int getSolutionProbleme() {
        return solutionProbleme;
    }

    public void setSolutionProbleme(int solutionProbleme) {
        this.solutionProbleme = solutionProbleme;
    }

    public int getNoteSolutionProbleme() {
        return noteSolutionProbleme;
    }

    public void setNoteSolutionProbleme(int noteSolutionProbleme) {
        this.noteSolutionProbleme = noteSolutionProbleme;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    

    @Override
    public String toString() {
        return "Problem{" + "id=" + id + ", domaineProbleme=" + domaineProbleme + ", descProbleme=" + descProbleme + ", nbrInteresse=" + nbrInteresse + ", profilDemande=" + profilDemande + ", titre=" + titre + ", membre=" + membre + ", solutionProbleme=" + solutionProbleme + ", noteSolutionProbleme=" + noteSolutionProbleme + ", dateAjout=" + dateAjout + ", email=" + email + ", etat=" + etat + ", views=" + views + ", likes=" + likes + ", imageFile=" + imageFile + '}';
    }

    

    public Problem(String titre, String domaineProbleme, String descProbleme, String profilDemande) {
        this.titre = titre;
        this.domaineProbleme = domaineProbleme;
        this.descProbleme = descProbleme;
        this.profilDemande = profilDemande;
    }

   

   
   
    
    
    
}