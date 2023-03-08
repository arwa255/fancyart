/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import entities.Reponse;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.ReclamationService;
import services.ReponseService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ReclamationViewFrontController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Label reclamation;
    @FXML
    private Label reponse;
    @FXML
    private Label Sujet;
    

    Reclamation Re = new Reclamation();
    ReclamationService RSE = new ReclamationService();
        
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void Update (ActionEvent event) {
        
        ModifierReclamationController.getIdd(Re);
        
        System.out.println("GUI.ReclamationViewFrontController.Update()"+Re);
                
         try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierReclamation.fxml"));
            Stage myWindow =  (Stage) reclamation.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(ReclamationViewFrontController.class.getName()).log(Level.SEVERE,null,ex);
        }
        

        
    }

    @FXML
    private void Delete(ActionEvent event) {
        
           try {
               System.out.println(Re);
            RSE.supprimer(Re);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontOffice.fxml"));
            Parent root = (Parent)loader.load();
            FrontOfficeController controller = (FrontOfficeController)loader.getController(); 
            reponse.getScene().setRoot(root);
        } catch (Exception e) {
        
    }
    
    }
    
    
     public void SetReclamation(Reclamation R)
    {
       
        reclamation.setText(R.getMessage_rec());
        reponse.setText("En Cours");
        Sujet.setText(R.getMessage_rec());
        Re=R;
        
    }
    
    
    
}
    
