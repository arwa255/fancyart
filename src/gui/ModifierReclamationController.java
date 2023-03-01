/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import entities.Reclamation;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import static javax.swing.JOptionPane.showMessageDialog;
import services.ReclamationService;
/**
 * FXML Controller class
 *
 * @author amens
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField messagerec;

    public static int id;
    Reclamation Rec = new Reclamation();
    ReclamationService Rs = new ReclamationService();
    
    
        public static int getIdd(Reclamation r) {
        
          id = r.getId_reclamation();
         
        return id;
        
    }
        
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         Rec=Rs.TrouverById(id);
         type.setText(Rec.getType());
         messagerec.setText(Rec.getMessage_rec());
        
        
    }    

    @FXML
    private void updaterec(ActionEvent event) {
        
              try {
           Reclamation R = new Reclamation();

           R.setType(type.getText());
           R.setMessage_rec(messagerec.getText());
           R.setStatut("En Cours");
            Rs.modifier(R);
            
            showMessageDialog(null, "Reclamation Modifier Avec Succès");
        }
       
        catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
    }
              
        
    }

    @FXML
    private void annuler(ActionEvent event) {
        
         try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationFront.fxml"));
            Parent root = (Parent)loader.load();
              AfficherReclamationFrontController controller = (AfficherReclamationFrontController)loader.getController();
            type.getScene().setRoot(root);
         
        
        } catch (Exception e) {
             System.out.println(e);
                
        }
        
    }
    
}
