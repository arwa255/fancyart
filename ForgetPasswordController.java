/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private TextField ancienPas;
    @FXML
    private TextField nouvau;
    @FXML
    private TextField confirmerPass;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void Changer(ActionEvent event) throws SQLException {
        
        ServiceUser su = new ServiceUser();
        
        User u = su.getUtilisateur(AuthentifierController.currentID);
        
        if (!(nouvau.getText().equals(confirmerPass.getText()))){
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("les deux mot de passe n'est pas identique");
            alert.showAndWait();
        }
        else if (!u.getMot_de_passe().equals(ancienPas.getText())){
        
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Vérifier votre password");
            alert.showAndWait();
        
        }else{
        
            u.setMot_de_passe(nouvau.getText());
            su.ModifierPassword(u);
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Success");
            alert.showAndWait();
        
                           
                  try {
            Parent root = FXMLLoader.load(getClass().getResource("authentifier.fxml"));
            Stage myWindow =  (Stage) nouvau.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
        }
    }
    
}
