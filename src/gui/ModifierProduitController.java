/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Produit;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ProduitService;
import tools.MyDB;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class ModifierProduitController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private Button A;
    @FXML
    private TextField description;
    @FXML
    private TextField nom;
    @FXML
    private Hyperlink prec;
    @FXML
    private ComboBox<Integer> Categorie;
    @FXML
    private ImageView imgajoutincours;
    @FXML
    private Label imgpathttt;
    @FXML
    private TextField prix;
    @FXML
    private Label labelid;
  private Label label;
     Connection cn;  
    /**
     * Initializes the controller class.
     */
     
       public ModifierProduitController() {
        
      
 cn = MyDB.getInstance().getConnection();         
         
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
            String req = "select * from catégorie";
         
            
            Statement stm = cn.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                Integer xx = rst.getInt("id");
                Categorie.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
            
           labelid.setText(Integer.toString(GestionProduitController.connectedproduit.getId()));
        nom.setText(GestionProduitController.connectedproduit.getNom());
        description.setText(GestionProduitController.connectedproduit.getDescription());
        prix.setText(Float.toString(GestionProduitController.connectedproduit.getPrix()));
        Categorie.setValue(GestionProduitController.connectedproduit.getId_categorie());
        
            
        
        
        
    }    

    @FXML
    private void insert(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {
           ProduitService productService = new ProduitService();

        if (nom.getText().equals("") || description.getText().equals("") || prix.getText().equals("")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
    
        
        
        else {

            Produit ccc = new Produit( Integer.parseInt(labelid.getText()),nom.getText(),Float.parseFloat(prix.getText()), 
                     description.getText(), Categorie.getValue());
                   
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
                 productService.modifierProduit(ccc);
       TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Modifié avec succés");
            tray.setMessage("Modifié avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
  Parent page1 = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
      
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
      

          

        }; 
        
        
        
    }

    @FXML
    private void prec(ActionEvent event) {
    }
    
}
