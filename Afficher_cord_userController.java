/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import controller.AddPostController;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MED KHALIL
 */
public class Afficher_cord_userController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @FXML
private Label nom_label;
@FXML
private Label prenom_label;
@FXML
private Label adresse_label;
@FXML
private Label mail_label;

private ServiceUser serviceUser;
    @FXML
    private ImageView imv;

@Override
public void initialize(URL url, ResourceBundle rb) {
    // Initialisation du service d'accès aux données
    serviceUser = new ServiceUser();
    try {
        // Récupération de l'utilisateur
        System.out.println("hh"+AuthentifierController.currentID);
        User user = serviceUser.getUtilisateur(AuthentifierController.currentID);
        // Affichage des coordonnées dans les labels correspondants
        System.out.println("GUI.Afficher_cord_userController.initialize()"+user.toString());
        nom_label.setText(user.getNom());
        prenom_label.setText(user.getPrenom());
        adresse_label.setText(user.getAdresse());
        mail_label.setText(user.getAdresse_mail());
        imv.setImage(new Image("/GUI/img/"+user.getImage()));;
    } catch (SQLException ex) {
        Logger.getLogger(Afficher_cord_userController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @FXML
    private void ModifierCompt(ActionEvent event) {
        
                try {
            Parent root = FXMLLoader.load(getClass().getResource("modifier_user.fxml"));
            Stage myWindow =  (Stage) adresse_label.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    @FXML
    private void changerPassword(ActionEvent event) {
        
                 try {
            Parent root = FXMLLoader.load(getClass().getResource("ForgetPassword.fxml"));
            Stage myWindow =  (Stage) adresse_label.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    
}
