/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entities.Reclamation;
import java.net.URL;
import java.util.List;
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
public class ReclamationViewBackController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Label type;
    @FXML
    private Label reclamation;

    
     Reclamation recla = new Reclamation();
     ReclamationService Rs = new ReclamationService();
     AjouterReponseController ajouterReponseController =new AjouterReponseController();
     
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void Delete(ActionEvent event) {
        try {
            Rs.supprimer(recla);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationBack.fxml"));
            Parent root = loader.load();
            reclamation.getScene().setRoot(root);
        
        } catch (Exception e) {
        }
    }

    
    
     public void SetReclamation(Reclamation R)
    {
        

      reclamation.setText(R.getMessage_rec());
      type.setText(R.getType());
      recla=R;

        
    }

    @FXML
    private void Repondre(ActionEvent event) {
        ajouterReponseController.getIdReclamation(recla);
         try {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReponse.fxml"));
            Parent root = loader.load();
            reclamation.getScene().setRoot(root);
        
        } catch (Exception e) {
        }
        
        
        
    }
}
