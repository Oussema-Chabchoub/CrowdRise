/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

/**
 *
 * @author Ghassen
 */
public class Solution {
    
    private int id;
    private String descriptionSolutionProbleme;
    private Membre membre;
    private Problem probleme;
    private int membre_id;
    private int probleme_id;
    private String username;
    private int rate;
    
    
    public Solution(String descriptionSolutionProbleme, int membre_id, int probleme_id) {
        this.descriptionSolutionProbleme = descriptionSolutionProbleme;
        this.membre_id = membre_id;
        this.probleme_id = probleme_id;
    }

    public Solution(int id, String descriptionSolutionProbleme, String username) {
        this.id = id;
        this.descriptionSolutionProbleme = descriptionSolutionProbleme;
        this.username = username;
    }

    public int getMembre_id() {
        return membre_id;
    }

    public void setMembre_id(int membre_id) {
        this.membre_id = membre_id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Solution() {
    }

    
    
    public int getProbleme_id() {
        return probleme_id;
    }

    public void setProbleme_id(int probleme_id) {
        this.probleme_id = probleme_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Solution(String descriptionSolutionProbleme, Membre membre, Problem probleme) {
        this.descriptionSolutionProbleme = descriptionSolutionProbleme;
        this.membre = membre;
        this.probleme = probleme;
    }

    public Solution(int id, String descriptionSolutionProbleme, Membre membre, Problem probleme) {
        this.id = id;
        this.descriptionSolutionProbleme = descriptionSolutionProbleme;
        this.membre = membre;
        this.probleme = probleme;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriptionSolutionProbleme() {
        return descriptionSolutionProbleme;
    }

    public void setDescriptionSolutionProbleme(String descriptionSolutionProbleme) {
        this.descriptionSolutionProbleme = descriptionSolutionProbleme;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Problem getProbleme() {
        return probleme;
    }

    public void setProbleme(Problem probleme) {
        this.probleme = probleme;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solution other = (Solution) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Solution{" + "id=" + id + ", descriptionSolutionProbleme=" + descriptionSolutionProbleme + ", membre=" + membre + ", probleme=" + probleme + '}';
    }
    
    
    
    
    
    
    
    
    
}
