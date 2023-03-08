/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import entities.Reclamation;
import entities.Reponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import services.ReclamationService;
import services.ReponseService;
/**
 * FXML Controller class
 *
 * @author amens
 */
public class AfficherReclamationBackController implements Initializable {
    
   private List<Reclamation> Rep = new VirtualFlow.ArrayLinkedList<>();
   private List<Integer> ReclamationIDs = new VirtualFlow.ArrayLinkedList<>();
   private List<Integer> ReponseIDs = new VirtualFlow.ArrayLinkedList<>();
   private List<Integer> ReclamationNonTraiteeIDList = new ArrayList<>();
   
   
   ReclamationService RS = new ReclamationService();
   ReponseService RepS = new ReponseService();
    @FXML
    private VBox Vboxrec;
    @FXML
    private TextField rechercher;
    @FXML
    private Label totlaatt;
    @FXML
    private Label total;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           // TODO
           total.setText(String.valueOf(RS.NumTotal()));
           totlaatt.setText(String.valueOf(RS.NumEnAttent("en attent")));

       } catch (SQLException ex) {
           Logger.getLogger(AfficherReclamationBackController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        ReclamationIDs=RS.GetALLReclamationID();
        ReponseIDs=RepS.GetALLReponseID();
                
                
                
        for (int i=0 ; i<ReclamationIDs.size() ; i++)
       {
         boolean var=true;  
           for(int j=0 ; j<ReponseIDs.size() && var==true; j++ )
           {
               if(ReclamationIDs.get(i) != ReponseIDs.get(j))
                var=true;
               else
                var=false;
           }
           if (var==true)
           ReclamationNonTraiteeIDList.add(ReclamationIDs.get(i)); 
       
           
       }
        
     try
       {
        Rep=RS.AllReclamationNonTraiteColis(ReclamationNonTraiteeIDList);
       }
        catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

        
          this.AFFICHERLESRECLAMATION();
          
          
  
     
     
     
        
          
    }



public void AFFICHERLESRECLAMATION(){
    for(int i=0 ; i<Rep.size() ; i++)
    {
       
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("reponseview.fxml"));
       
         try {
            
            HBox hbox = fxmlloader.load();
            ReponseviewController rvc = fxmlloader.getController();
            rvc.SetReponse(Rep.get(i));
            
            Vboxrec.getChildren().add(hbox);  
        }
        catch(IOException e)
                {
                    e.printStackTrace();
                }
       
    }

}

    @FXML
    private void rechercher(ActionEvent event) {
    }





    
    
}
