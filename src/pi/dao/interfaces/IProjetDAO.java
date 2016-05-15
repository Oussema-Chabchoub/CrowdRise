/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import pi.entities.Projet;


/**
 *
 * @author User
 */
public interface IProjetDAO {
    public void addProjet(Projet p);
    public void removeProjet(int id);
    public void updateProjet(Projet p,int id);
public  ObservableList<Projet> DisplayMyProjects(int id);
public void DisplayById(ObservableList<Projet> listeProjets,int id);
        Projet findProjetById(int id);
        void finance(int idu,int ipd , int fond);
}
