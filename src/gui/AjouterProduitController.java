/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;
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
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
public class AjouterProduitController implements Initializable {

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
    private Label label;
     Connection cn;  
  public AjouterProduitController() {
        
      
 cn = MyDB.getInstance().getConnection();         
         
    }
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
            String req = "select * from catégorie";
         
            
            Statement stm = cn.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {                
                Integer xx = rst.getInt("id");
                Categorie.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        


        
        
    }    

    @FXML
    private void insert(ActionEvent event) throws IOException, MessagingException {
           ProduitService productService = new ProduitService();

        if (nom.getText().equals("") || description.getText().equals("") || prix.getText().equals("")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
   
        else if(nom.getText().length() >20  ){
                 Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("le nom ne doit pas depasser 30 caracteres ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
         else if(nom.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")  ){
             Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("le nom ne doit pas contenir un caractére spéciaux ");
            a.setHeaderText(null);
            a.showAndWait();
                 
                 } 
        else {

            Produit ccc = new Produit( nom.getText(),Float.parseFloat(prix.getText()), 
                     description.getText(), Categorie.getValue());
                   
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
                 productService.ajouterProduit(ccc);
      TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Ajouté avec succés");
            tray.setMessage("Ajouté avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
 
            sendMail("trabelsi.nourdine@esprit.tn", "Produit ajouté avec succées", 
                    "Ajouté avec succées");
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
  public static void sendMail(String recipient,String Subject,String Text) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "isitapp11@gmail.com";
        String password = "cdjgkltnjmzbrlhj";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            
        Message message = prepareMessage(session, myAccountEmail, recipient,Subject,Text);

        javax.mail.Transport.send(message);
        System.out.println("Message sent successfully");
    }  
   
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient,String Subject,String Text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(Subject);
            message.setText(Text);
            return message;
        } catch (MessagingException ex) {
          
        }
        return null;} 
    @FXML
    private void prec(ActionEvent event) throws IOException {
          Parent page1 = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }
    
    
    
    
    
}
