/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.interfaces;
import pi.entities.commentaireprojet;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author jappouni
 */
public interface ICommentDao {
void addComment(commentaireprojet commentaireprojet);
void updateComment(commentaireprojet commentaireprojet,int id);
void removeCommentById(int id);
List<commentaireprojet> DisplayAllComments(ObservableList<commentaireprojet> listeComment);

    commentaireprojet findCommentById(int id);
        ObservableList<commentaireprojet> DisplayById(int id);

}
