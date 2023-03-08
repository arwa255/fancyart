/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entity.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ProduittemController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Text nom;
    @FXML
    private Text prix;
    @FXML
    private HBox hbox;
    @FXML
    private ImageView imv;
    
    String ImageUrl = "/GUI/img/";
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
   
    }  
    
    public ProduittemController(){
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/ProduitItem.fxml"));
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
    
    
    
    
        public void setInfo(Produit p)  {   

            
        nom.setText(p.getNom());
        prix.setText(String.valueOf(p.getDescription()));
        
            System.err.println("FFFF"+ImageUrl+p.getImgae() );
        imv.setImage(new Image(ImageUrl+p.getImgae()));;
     
     
}
        
}
