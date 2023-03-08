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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MED KHALIL
 */
public class AuthentifierController implements Initializable {

    @FXML
    private AnchorPane authentifier;
    @FXML
    private Button authentifier_butt;
    @FXML
    private Label email_auth;
    @FXML
    private TextField email_field;
    @FXML
    private PasswordField mdp_field;
    @FXML
    private Label mdp_auth;
    @FXML
    private Button inscrire_butt;
    
    public static int currentID;
    public static String current_password;
    public static String current_nom;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // TODO
    inscrire_butt.setOnAction(event -> {
        try {
            // Fermer la fenêtre actuelle
            Stage stage = (Stage) inscrire_butt.getScene().getWindow();
            stage.close();

            // Afficher la fenêtre pour ajouter un utilisateur
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter_user.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });
}

    User user = new User();

    @FXML
    private void authentifier(ActionEvent event) throws SQLException, IOException {
        String adresseMail = email_field.getText();
        String motDePasse = mdp_field.getText();

        ServiceUser us = new ServiceUser();
        user = us.chercherParAdresseMailEtMotDePasse(adresseMail);
        if (user != null && user.getMot_de_passe().equals(motDePasse)) {
            // Les informations d'identification sont valides, vous pouvez rediriger l'utilisateur vers une autre vue
           if (user.getRole().equals("ADMIN")){
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/BackOffice.fxml"));
            Parent root = loader.load(); 
            currentID = user.getId();
            current_password = user.getMot_de_passe();
            current_nom = user.getNom();
        //    ModifierController controller = loader.getController();
            URL url = null;
            ResourceBundle rb = null;
        //    controller.initialize(url, rb);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
           }else{
               
                             FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/FrontOffice.fxml"));
            Parent root = loader.load(); 
            currentID = user.getId();
            current_password = user.getMot_de_passe();
            current_nom = user.getNom();
        //    ModifierController controller = loader.getController();
            URL url = null;
            ResourceBundle rb = null;
        //    controller.initialize(url, rb);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
           }
        
        } else {
            // Les informations d'identification sont invalides, afficher un message d'erreur à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Adresse e-mail ou mot de passe incorrect");
            alert.showAndWait();
        }

    }

    @FXML
    private void Oblier(ActionEvent event) {
        
                     try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierPassword.fxml"));
            Stage myWindow =  (Stage) email_auth.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }

}
