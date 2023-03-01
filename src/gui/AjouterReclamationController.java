/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import static javax.swing.JOptionPane.showMessageDialog;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author amens
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField messagerec;
    
    ReclamationService Rs = new ReclamationService();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
     boolean checkifstringisnumber (String s){
        try {
    int i;        
    i = Integer.parseInt(s);
    return true;
            } 
        catch (NumberFormatException e) {
    System.out.println("Input String cannot be parsed to Integer.");
}
        return false;
    }
    
     
     
     
    @FXML
    private void reclamer(ActionEvent event) {
        
          try {
           Reclamation R = new Reclamation();

           R.setType(type.getText());
           R.setMessage_rec(messagerec.getText());
           R.setStatut("En Cours");
          
           if(type.getText().isEmpty() || messagerec.getText().isEmpty() )
           {
               showMessageDialog(null, "Remplir Vos Champs");
           }
           else if (this.checkifstringisnumber(type.getText())== true)
           {
              showMessageDialog(null, "Type Ne Contient pas des chiffre"); 
           }
           else
           {
            Rs.ajouter(R);
            showMessageDialog(null, "Reclamation Ajouté Avec Succès");   
           
           }
          
            
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
