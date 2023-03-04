/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.Reponse;
import static gui.ModifierReclamationController.id;
import services.ReponseService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import static javax.swing.JOptionPane.showMessageDialog;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author amens
 */
public class AjouterReponseController implements Initializable {

    ReponseService Rs = new ReponseService(); 
    Reclamation Rec = new Reclamation();
    ReclamationService Rt = new ReclamationService();
    
    @FXML
    private TextField messagerep;

    
    
         private static int id;
         public static int getIdReclamation(Reclamation R) {
        
          id = R.getId_reclamation();
          System.out.println(id);
   
        return id;
        
    }
         
         
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Rec=Rt.TrouverById(id);
    }    

    @FXML
    private void reclamer(ActionEvent event) {
               try {
                   Reponse R = new Reponse();

           R.setMessage_rep(messagerep.getText());
            R.setId_reclamation(Rec.getId_reclamation());
                   System.out.println(R.getId_reclamation());
           Rs.ajouter(R);
          
           if(messagerep.getText().isEmpty() )
           {
               showMessageDialog(null, "Remplir Vos Champs");
           }
           else
           {
            Rs.modifier(R);
            showMessageDialog(null, "Réponse Ajouté Avec Succès");   
           }
            
        }
       
        catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
    }
        
    }

    @FXML
    private void annuler(ActionEvent event) {
        
        try {
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationBack.fxml"));
            Parent root = loader.load();
            messagerep.getScene().setRoot(root);
        
        } catch (Exception e) {
        }
    }
    
}
