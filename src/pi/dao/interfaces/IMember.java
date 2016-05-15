
package pi.dao.interfaces;

import java.util.List;
import pi.entities.Membre;
/**
 *
 * @author USER
 */
public interface IMember {
    void addMembre(Membre membre);
    void bannMembre(int id);
   int verfierlogin(String Username,String Password );
   int verfierPseudo(String Pseudo);
    void updatMembre(Membre membre);

    void removeMemberById(int id);
    List<Membre> findAll();
 Membre findMemberById(int id);
 
 int getSolde(int id);
   
    
    
}
