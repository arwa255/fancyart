/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller ;



import entites.Post;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewPostClient extends ListCell<Post> {
    
    
     @Override
     public void updateItem(Post e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            PosttItemControllerClient data = new PosttItemControllerClient();
            try {
                data.setInfo(e);
            } catch (SQLException ex) {
                Logger.getLogger(ListViewPostClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
