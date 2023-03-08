/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller ;



import entites.Post;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewPost extends ListCell<Post> {
    
    
     @Override
     public void updateItem(Post e, boolean empty)
    {
        super.updateItem(e,empty);
        
        if(e != null)
        {
            
            PosttItemController data = new PosttItemController();
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
            data.setInfo(e);

        }
    }
    
}
