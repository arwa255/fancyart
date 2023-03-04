/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author amens
 */
public class ReclamationViewFrontController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Label type;
    @FXML
    private Label reclamation;
    @FXML
    private Label reponse;
    
    Reclamation recla = new Reclamation();
    ReclamationService Rs = new ReclamationService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void UpdateLivreur(ActionEvent event) {
        System.out.println(recla.getId_reclamation());
         ModifierReclamationController.getIdd(recla);
         try {
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReclamation.fxml"));
            Parent root = loader.load();
            reponse.getScene().setRoot(root);
        
        } catch (Exception e) {
        }
          
        
    }

    @FXML
    private void DeleteLivreur(ActionEvent event) {
        
         try {
            Rs.supprimer(recla);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationFront.fxml"));
            Parent root = loader.load();
            reponse.getScene().setRoot(root);
        
        } catch (Exception e) {
        }
        
    }
    
    
    
        public void SetReclamation(Reclamation R)
    {
        

      reclamation.setText(R.getMessage_rec());
      type.setText(R.getType());
        reponse.setText(R.getStatut());
      

      recla=R;

        
    }
        
    
}
