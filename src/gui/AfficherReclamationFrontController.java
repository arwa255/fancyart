/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static javax.swing.JOptionPane.showMessageDialog;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author amens
 */
public class AfficherReclamationFrontController implements Initializable {

    @FXML
    private VBox Vboxlivraison;
    
     Reclamation R = new Reclamation();
     ReclamationService Rs = new ReclamationService();
     private List<Reclamation> Recla = new VirtualFlow.ArrayLinkedList<>();
    @FXML
    private TextField rechercher;

    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try { 
            Recla = Rs.recuperer();

        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       
         
               this.AfficherReclamation();
         
       
    }    

    
    
    
    @FXML
    private void AjouterReclamation(ActionEvent event) {
        
        
         try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
            Parent root = (Parent)loader.load();
             AjouterReclamationController controller = (AjouterReclamationController)loader.getController();
             Vboxlivraison.getScene().setRoot(root);
         
        
        } catch (Exception e) {
             System.out.println(e);
                
        }
        
    }
    
    
    
    public void AfficherReclamation(){
       
        
              for(int i=0 ; i<Recla.size() ; i++)
    {
       
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("ReclamationViewFront.fxml"));
     
            
        
        try {
            HBox hbox = fxmlloader.load();
            ReclamationViewFrontController Cvb = fxmlloader.getController();
            Cvb.SetReclamation(Recla.get(i));
            Vboxlivraison.getChildren().add(hbox);
               
               
            
            
        }
        catch(IOException e)
                {
                    e.printStackTrace();
                }
       
    }
        }
  



    @FXML
    private void ChercherButton(ActionEvent event) {
       
      
                   
            
            if(rechercher.getText().isEmpty())
            {
                 showMessageDialog(null, " Champs vide");
            }
            else
            {
                 R=Rs.TrouverType(rechercher.getText());
                 if (R==null)
                 {
                      showMessageDialog(null, " Type Pas Trouvé ");
                 }
                 else
                 {
                      showMessageDialog(null, " Type Existe");
                    
                 }
             
                
            }
           
    
               
            
        
    }
    
    
    
    
    
    
    
}
