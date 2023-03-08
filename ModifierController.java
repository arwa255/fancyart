/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.AuthentifierController.current_password;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MED KHALIL
 */
public class ModifierController implements Initializable {

    @FXML
    private Pane modifier_page;
    @FXML
    private Label nom_mod;
    @FXML
    private Label prenom_mod;
    @FXML
    private Label adresse_mod;
    @FXML
    private Label mail_mod;
    @FXML
    private TextField nom_field;
    @FXML
    private TextField prenom_field;
    @FXML
    private TextField adresse_field;
    @FXML
    private TextField mail_field;
    private PasswordField mdp_field;
    @FXML
    private Button mod_butt2;
    @FXML
    private Button mod_butt4;
    @FXML
    private Button mod_butt5;
    @FXML
    private Button mod_butt1;
    private PasswordField nouv_mdp_field;
    @FXML
    private Hyperlink archiver_cpt;

    /**
     * Initializes the controller class.
     */
    private ServiceUser serviceUser;
    @FXML
    private Hyperlink archiver_cpt1;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialisation du service d'accès aux données
        serviceUser = new ServiceUser();
         
    }

   @FXML
public void modifierNom(ActionEvent event) throws SQLException {
    String nom = nom_field.getText(); 
    ServiceUser us = new ServiceUser();
    us.modifierNom(AuthentifierController.currentID, nom); 
}


    


    @FXML
    public void modifierPrenom(ActionEvent event) throws SQLException {
        String prenom = prenom_field.getText();
        ServiceUser us = new ServiceUser();
        us.modifierPrenom(AuthentifierController.currentID, prenom);
    }

    @FXML
    public void modifierAdresse(ActionEvent event) throws SQLException {
        String adresse = adresse_field.getText();
        ServiceUser us = new ServiceUser();
        us.modifierAdresse(AuthentifierController.currentID, adresse);
    }

    public void modifierMotDePasse(ActionEvent event) throws SQLException {
        String ancienMotDePasse = mdp_field.getText();
        String nouveauMotDePasse = nouv_mdp_field.getText();
        ServiceUser us = new ServiceUser();
        try {
            if (mdp_field.getText().equals(current_password)) {
                us.modifierMotDePasse(AuthentifierController.currentID, ancienMotDePasse, nouveauMotDePasse);
                // Si la modification s'est bien passée, afficher un message de succès à l'utilisateur
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("Le mot de passe a été modifié avec succès !");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("errur de saisie");
                alert.setContentText("L'ancien mot de passe est invalide ! ");
                alert.showAndWait();
            }
        
    }

catch (SQLException e) {
        // Si une erreur s'est produite, afficher un message d'erreur à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}

    @FXML
    public void modifierAdresse_mail(ActionEvent event) throws SQLException {
    String adresse_mail = mail_field.getText();
    ServiceUser us = new ServiceUser();
    us.modifierAdresse_mail(AuthentifierController.currentID, adresse_mail);
   }

//  @FXML
//private void archiverCompte(ActionEvent event) {
//    // Récupérer l'utilisateur courant
//    User user = serviceUser.getUtilisateur(AuthentifierController.currentID);
//    
//    // Mettre à jour l'état du compte dans la base de données
//    user.setEtatCompte("Archivé");
//    serviceUser.modifierUtilisateur(user);
//
//    // Afficher un message de confirmation
//    Alert alert = new Alert(AlertType.INFORMATION);
//    alert.setTitle("Compte archivé");
//    alert.setHeaderText(null);
//    alert.setContentText("Votre compte a été archivé avec succès !");
//    alert.showAndWait();
//
//    // Rediriger l'utilisateur vers la page de connexion
//    try {
//        Parent root = FXMLLoader.load(getClass().getResource("authentifier.fxml"));
//        Stage stage = (Stage) archiver_cpt.getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    } catch (IOException ex) {
//        Logger.getLogger(ModifierProfileController.class.getName()).log(Level.SEVERE, null, ex);
//    }
//}

    @FXML
    private void back(ActionEvent event) {
        
                        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FrontOffice.fxml"));
            Stage myWindow =  (Stage) mail_field.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }





}
