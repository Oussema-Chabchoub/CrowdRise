
package pi.dao.classes;

import pi.dao.interfaces.IStatistiqueDAO;
import pi.technique.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import menu.Statistique;

/**
 *
 * @author Yasmina
 */
public class StatistiqueDAO implements IStatistiqueDAO {

    private Connection c;

    ObservableList<Statistique> list;

    public StatistiqueDAO() {
        c = DataSource.getInstance().getConnection();
    }
    private static IStatistiqueDAO istat;

    public static IStatistiqueDAO getInstance() {
        if (istat == null) {
            istat = new StatistiqueDAO();
        }
        return istat;
    }

    @Override
    public ObservableList<Statistique> ByDomainEnvironement() {

        list = FXCollections.observableArrayList();

        try {
            String sql1 = "select count(domaineProbleme)as nb,domaineProbleme from probleme group by domaineProbleme";
            ResultSet res = c.createStatement().executeQuery(sql1);

            while (res.next()) {
                list.add(new Statistique(res.getInt("nb"), res.getString("domaineProbleme")));
                
            }

        } catch (Exception e) {
            
        }
        return list;
    }


    @Override
    public ObservableList<Statistique> ByDomainHealth() {
        try {
            String sql1 = "select domaineProbleme from probleme where domaineProbleme=Health";
            ResultSet res = c.createStatement().executeQuery(sql1);
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ObservableList<Statistique> ByDomainService() {
        try {
            String sql1 = "select domaineProbleme from probleme where domaineProbleme=Service";
            ResultSet res = c.createStatement().executeQuery(sql1);
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ObservableList<Statistique> ByDomainBusiness() {
        try {
            String sql1 = "select domaineProbleme from probleme where domaineProbleme=Business";
            ResultSet res = c.createStatement().executeQuery(sql1);
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}