/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entites.Post;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import services.ServiceVote;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class PosttItemControllerClient implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Text nomuser;
    @FXML
    private Text type;
    
        @FXML
    private Label deslike;
        
            @FXML
    private Label like;
    @FXML
    private HBox hbox;
    @FXML
    private ImageView imv;
    
    ServiceVote sv = new ServiceVote();
    
    String ImageUrl = "/GUI/img/";
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
   
    }  
    
    public PosttItemControllerClient(){
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/PosttItemClient.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
    }

    public HBox getBox() {
        return Hbox;
    }

    public HBox getHbox() {
        return hbox;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }
    
    
    
    
        public void setInfo(Post p) throws SQLException  {   

            
        type.setText(p.getdescription());
        nomuser.setText("Selim");
        imv.setImage(new Image(ImageUrl+p.getImage()));
        deslike.setText(String.valueOf(sv.NumdeLike(p.getid_post())));
        like.setText(String.valueOf(sv.NumLike(p.getid_post())));
     
}
        
}
