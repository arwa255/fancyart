/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.ServiceUser;


/**
 * FXML Controller class
 *
 * @author MED KHALIL
 */
public class Ajouter_userController implements Initializable {

    @FXML
    private Pane ajouter_user;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label mdp;
    @FXML
    private Label adresse;
    @FXML
    private Label mail;
    @FXML
    private TextField nom_aj;
    @FXML
    private TextField prenom_aj;
    @FXML
    private TextField adresse_aj;
    @FXML
    private TextField mail_aj;
    @FXML
    private Button ajouter_butt;
    @FXML
    private PasswordField mdp_aj;
    
    
        int c;
        int file;
        File pDir;
        File pfile;
        String lien;

    public static String MDP;
    public static String MAIL;
    @FXML
    private ImageView imv;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
            pDir = new File("src/GUI/img/user" + c + ".jpg");
            lien = "user" + c + ".jpg";
    }   
    
    
public void loadPage(String pageName) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pageName));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ajouter_user.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.err.println(String.format("Error loading %s: %s", pageName, ex.getMessage()));
    }
}


    @FXML
 public void ajouterUtilisateur() throws IOException {

    User user = new User();
    user.setNom(nom_aj.getText());
    user.setPrenom(prenom_aj.getText());
    user.setAdresse(adresse_aj.getText());

  ;
    user.setMot_de_passe(mdp.getText());
    user.setAdresse_mail(mail_aj.getText());
     user.setImage(lien);
    user.setRole("Client");
             copier(pfile, pDir);

    if (nom_aj.getText().isEmpty() || prenom_aj.getText().isEmpty() || adresse_aj.getText().isEmpty() || mdp_aj.getText().isEmpty() || mail_aj.getText().isEmpty()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }

    ServiceUser us = new ServiceUser();
    try {
        if (true) {
            if (mail_aj.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {

                // Vérifier si l'utilisateur existe déjà dans la base de données
                if (us.isUserExists(user.getAdresse_mail())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Un utilisateur avec cette adresse email existe déjà !");
                    alert.showAndWait();
                } else {
                    us.ajouter(user);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText(null);
                    alert.setContentText("Utilisateur ajouté avec succès !");
                    alert.showAndWait();

                    loadPage("authentifier.fxml");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Adresse mail incorrecte");
                alert.setHeaderText(null);
                alert.setContentText("L'adresse mail est incorrecte !");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mot de passe incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Le nouveau mot de passe doit contenir au moins 8 caractères, dont une lettre majuscule, un chiffre et un caractère spécial.");
            alert.showAndWait();
        }
    } catch (SQLException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        alert.showAndWait();
    }
}

    @FXML
    private void Upload(ActionEvent event) throws MalformedURLException {
        
                    FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imv.setImage(image);
    }
    }

        
             public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }



}
