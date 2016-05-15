/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import pi.entities.Membre;
import pi.entities.Projet;
import java.util.Date;

/**
 *
 * @author jappouni
 */
public class commentaireprojet {

    private int id;
    private String contenuCommentaireProjet ;
    private Date dateCommentaireProjet;
    private Membre membreId;
    private Projet projetId;
    private int projet_id;
    private int id_membre;
    private String username;

    public commentaireprojet(String contenuCommentaireProjet, int projet_id, int id_membre) {
        this.contenuCommentaireProjet = contenuCommentaireProjet;
        this.projet_id = projet_id;
        this.id_membre = id_membre;
    }

    public commentaireprojet(int id, String contenuCommentaireProjet, String username) {
        this.id = id;
        this.contenuCommentaireProjet = contenuCommentaireProjet;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public commentaireprojet(String contenuCommentaireProjet, Projet projetId, int id_membre) {
        this.contenuCommentaireProjet = contenuCommentaireProjet;
        this.projetId = projetId;
        this.id_membre = id_membre;
    }

    public commentaireprojet() {
    }

    public commentaireprojet(String contenuCommentaireProjet, Projet projetId) {
        this.contenuCommentaireProjet = contenuCommentaireProjet;
        this.projetId = projetId;
    }

    public commentaireprojet(String contenuCommentaireProjet, Membre membreId) {
        this.contenuCommentaireProjet = contenuCommentaireProjet;
        this.membreId = membreId;
    }
    
    public commentaireprojet(String contenuCommentaireProjet, Membre membreId, Projet projet) {
        this.contenuCommentaireProjet = contenuCommentaireProjet;
        this.membreId = membreId;
        this.projetId=projet;
    }

    public commentaireprojet(int id, String contenuCommentaireProjet, Date dateCommentaireProjet, Membre membreId, Projet projetId) {
        this.id = id;
        this.contenuCommentaireProjet = contenuCommentaireProjet;
        this.dateCommentaireProjet = dateCommentaireProjet;
        this.membreId = membreId;
        this.projetId = projetId;
    }

    public commentaireprojet(String contenuCommentaireProjet, Date dateCommentaireProjet, Membre membreId, Projet projetId) {
        this.contenuCommentaireProjet = contenuCommentaireProjet;
        this.dateCommentaireProjet = dateCommentaireProjet;
        this.membreId = membreId;
        this.projetId = projetId;
    }

    public commentaireprojet(int id, String contenuCommentaireProjet, Membre membreId) {
        this.id = id;
        this.contenuCommentaireProjet = contenuCommentaireProjet;
        this.membreId = membreId;
    }

    public commentaireprojet(String contenuCommentaireProjet) {
        this.contenuCommentaireProjet = contenuCommentaireProjet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenuCommentaireProjet() {
        return contenuCommentaireProjet;
    }

    public void setContenuCommentaireProjet(String contenuCommentaireProjet) {
        this.contenuCommentaireProjet = contenuCommentaireProjet;
    }

    public Date getDateCommentaireProjet() {
        return dateCommentaireProjet;
    }

    public void setDateCommentaireProjet(Date dateCommentaireProjet) {
        this.dateCommentaireProjet = dateCommentaireProjet;
    }

    public Membre getMembreId() {
        return membreId;
    }

    public void setMembreId(Membre membreId) {
        this.membreId = membreId;
    }

    public Projet getProjetId() {
        return projetId;
    }

    public void setProjetId(Projet projetId) {
        this.projetId = projetId;
    }

    @Override
    public String toString() {
        return "commentaireprojet{" + "id=" + id + ", contenuCommentaireProjet=" + contenuCommentaireProjet + ", dateCommentaireProjet=" + dateCommentaireProjet + ", membreId=" + membreId + ", projetId=" + projetId + '}';
    }
    public Membre getMembre() {
        return membreId;
    }

    /**
     * @param depot the depot to set
     */
    public void setMembre(Membre membreId) {
        this.membreId = membreId;
    }
    public Projet getProjet() {
        return projetId;
    }

    /**
     * @param depot the depot to set
     */
    public void setProjet(Projet projetId) {
        this.projetId = projetId;
    }

    
    
    
}
