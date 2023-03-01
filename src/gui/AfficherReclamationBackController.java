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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.ReclamationService;

    

/**
 * FXML Controller class
 *
 * @author amens
 */
public class AfficherReclamationBackController implements Initializable {
    
    
    
    @FXML
    private VBox Vboxlivraison;
    
     Reclamation R = new Reclamation();
     ReclamationService Rs = new ReclamationService();
     private List<Reclamation> Recla = new VirtualFlow.ArrayLinkedList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try { 
            Recla = Rs.recuperer();

        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         this.AfficherReclamation();
         
    }    
    
    
    
     public void AfficherReclamation(){
    for(int i=0 ; i<Recla.size() ; i++)
    {
        
       
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("ReclamationViewBack.fxml"));
        
        try {
            HBox hbox = fxmlloader.load();
            ReclamationViewBackController Cvb = fxmlloader.getController();
            Cvb.SetReclamation(Recla.get(i));
            Vboxlivraison.getChildren().add(hbox);
            
            
        }
        catch(IOException e)
                {
                    e.printStackTrace();
                }
       
    }
     }
     
}

