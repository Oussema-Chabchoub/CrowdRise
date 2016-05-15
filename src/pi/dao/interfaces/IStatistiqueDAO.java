package pi.dao.interfaces;

import java.sql.ResultSet;
import javafx.collections.ObservableList;
import menu.Statistique ;

/**
 *
 * @author Yasmina
 */
public interface IStatistiqueDAO {

    ObservableList<Statistique> ByDomainEnvironement();
     ObservableList<Statistique> ByDomainHealth();
      ObservableList<Statistique> ByDomainService();
       ObservableList<Statistique> ByDomainBusiness();
    

}