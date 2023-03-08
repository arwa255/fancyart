/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import GUI.AuthentifierController;
import entites.BadWords;
import entites.Commentaire;
import entites.Post;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CommentaireService;
import services.PostService;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowCommentControllerClient implements Initializable {

    @FXML
    private ListView<Commentaire> listView;
   
    ObservableList<Commentaire> data;
    
    public static int idE ;
    
    PostService ds = new PostService();

    CommentaireService cs = new CommentaireService();
   
    @FXML
    private TextField contenu;
    @FXML
    private Label description;
    
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("kkkkkkkk");
        Post p = ds.findpostById(ShowPostsControllerClient.idEclient);
      description.setText(p.getdescription());
        
        
        CommentaireService cs = new CommentaireService();
        try {
            data = (ObservableList<Commentaire>) cs.getAllCommentObs(ShowPostsControllerClient.idEclient);
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowCommentControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        listView.setItems(data);
        listView.setCellFactory((ListView<Commentaire> param) -> new ListViewCommentClient());
        
        
        // TODO
    }    




    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void commenter(ActionEvent event) {
         
        long millis=System.currentTimeMillis();  
        java.sql.Date d=new java.sql.Date(millis);  
         BadWords.loadConfigs();

         if (BadWords.filterText(contenu.getText())) {
         
         
           Alert alert = new Alert(Alert.AlertType.ERROR, "cette terme est interdit", ButtonType.OK);
           alert.showAndWait();
        
         }
         
         else{
        Commentaire c = new Commentaire(contenu.getText(),  d, ShowPostsControllerClient.idEclient);
        c.setIduser(AuthentifierController.currentID);
        cs.ajouterCommentaire(c);
        
                          try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/ShowCommentClient.fxml"));
            Stage myWindow =  (Stage) contenu.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
         }
          
    }

    }

    

