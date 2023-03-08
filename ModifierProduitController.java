/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Categorie;
import entity.Produit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import services.ProduitService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.MyDB;

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
    private ComboBox<Categorie> Categorie;
    @FXML
    private Label imgpathttt;
    @FXML
    private TextField prix;
    @FXML
    private Label labelid;
  private Label label;
     Connection cn;  
    @FXML
    private ImageView imv;
    
        int c;
        int file =0;
        File pDir;
        File pfile;
        String lien;
    /**
     * Initializes the controller class.
     */
     
       public ModifierProduitController() {
        
      
       cn = MyDB.getInstance().getCon();        
         
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
            String req = "select * from catégorie";
         
            
            Statement stm = cn.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
         //   Users a = new Users(rst.getInt("id"));
                 Categorie u = new Categorie(rst.getInt("id")
                    , rst.getString("type") );
                
                Categorie.getItems().add(u);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
            pDir = new File("src/gui/img/produit" + c + ".jpg");
            lien = "produit" + c + ".jpg";

            
           labelid.setText(Integer.toString(GestionProduitController.connectedproduit.getId()));
        nom.setText(GestionProduitController.connectedproduit.getNom());
        description.setText(GestionProduitController.connectedproduit.getDescription());
        prix.setText(Float.toString(GestionProduitController.connectedproduit.getPrix()));
        imv.setImage(new Image("/gui/img/"+GestionProduitController.connectedproduit.getImgae()));;
      
        
            
        
        
        
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
                     description.getText(), Categorie.getValue().getId());
                 if(file !=0)  {
                    ccc.setImgae(lien);
                    copier(pfile, pDir);
                 }
                 else{
                 ccc.setImgae(GestionProduitController.connectedproduit.getImgae());
                 }
           

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
