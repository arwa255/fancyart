/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class FrontOfficeController implements Initializable {

    @FXML
    private BorderPane bp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
      private void loadPage(String page)
    {
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FrontOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bp.setCenter(root);
    }
    
    

    @FXML
    private void GoClient(ActionEvent event) {
         loadPage("afficher_cord");
    }

    @FXML
    private void GoAdmins(ActionEvent event) {
    }

    @FXML
    private void GoRelais(ActionEvent event) {
    }

    @FXML
    private void GoReclamation(ActionEvent event) {
         loadPage("AfficherReclamationFront");
    }

    @FXML
    private void GoOffreDemande(ActionEvent event) {
    }

    @FXML
    private void GoEvenement(ActionEvent event) {
    }

    @FXML
    private void GoOpportunité(ActionEvent event) {
         loadPage("ShowProduit_1");
    }

    @FXML
    private void GoVehicule(ActionEvent event) {
         loadPage("ShowPosts");

    }

    @FXML
    private void GoLivreur(ActionEvent event) {
                 loadPage("ShowPostsClient");

    }

    @FXML
    private void GoColis(ActionEvent event) {
        
         loadPage("CommandeGestion");
    }

    @FXML
    private void GoSignOut(ActionEvent event) {
                             try {
            Parent root = FXMLLoader.load(getClass().getResource("authentifier.fxml"));
            Stage myWindow =  (Stage) bp.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
}
