/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.BadWords;
import entities.Reclamation;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import static javax.swing.JOptionPane.showMessageDialog;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author amens
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField Texte_reclamation;
    @FXML
    private TextField Sujet;
    
    ReclamationService RS = new ReclamationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterRedclamation(ActionEvent event) {
        
        try {
             
           Reclamation r = new Reclamation();
           
            r.setMessage_rec(Texte_reclamation.getText());
            r.setStatut("En attent");
            r.setId_user(AuthentifierController.currentID);
            r.setType(Sujet.getText());
            
     if (BadWords.filterText(Texte_reclamation.getText())) {
         
         
           Alert alert = new Alert(Alert.AlertType.ERROR, "cette terme est interdit", ButtonType.OK);
           alert.showAndWait();
        
         }else{
         

                     RS.ajouter(r);
                     showMessageDialog(null, "Reclamation Ajoutée Avec Succès" );    
            
            } 
        }
            

        catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
    }
         
        
    }

    @FXML
    private void listeREC(ActionEvent event) {
         try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontOffice.fxml"));
            Parent root = (Parent)loader.load();
            FrontOfficeController controller = (FrontOfficeController)loader.getController();
            Sujet.getScene().setRoot(root);
         
        
        } catch (Exception e) {
             System.out.println(e);
                
        }
    }
    
}
