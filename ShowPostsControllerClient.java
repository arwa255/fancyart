/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import GUI.AuthentifierController;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entites.Post;
import entites.Vote;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
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

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.PostService;
import services.ServiceVote;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowPostsControllerClient implements Initializable {

    @FXML
    private ListView<Post> listView;
   
    ObservableList<Post> data;
    
    public static int idEclient ;
    
    PostService ds = new PostService();
    @FXML
    private Button detail;
    
    ServiceVote sv = new ServiceVote();

    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            PostService cs = new PostService();
            data = (ObservableList<Post>) cs.getAllPostObs();   
            listView.setItems(data);
            listView.setCellFactory((ListView<Post> param) -> new ListViewPostClient());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowPostsControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    





    @FXML
    private void Details(ActionEvent event) {
       
               
            ObservableList<Post> e = listView.getSelectionModel().getSelectedItems();

            
          for (Post m : e) 
          idEclient=m.getid_post();
          
                  try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/ShowCommentClient.fxml"));
            Stage myWindow =  (Stage) detail.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           Logger.getLogger(ShowPostsControllerClient.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
            
        
           
          
    }

    @FXML
    private void like(ActionEvent event) throws SQLException {
        
        
         ObservableList<Post> e = listView.getSelectionModel().getSelectedItems();

          
          for (Post m : e) {
              
              Vote v = new Vote();
              v.setId_post(m.getid_post());
              v.setId_user(AuthentifierController.currentID);
              v.setType(1);
              sv.addVote(v);
              
          
          }
                  try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/ShowPostsClient.fxml"));
            Stage myWindow =  (Stage) detail.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           Logger.getLogger(ShowPostsControllerClient.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
    }

    @FXML
    private void deslike(ActionEvent event) throws SQLException {
        
        
         ObservableList<Post> e = listView.getSelectionModel().getSelectedItems();

          
          for (Post m : e) {
              
              Vote v = new Vote();
              v.setId_post(m.getid_post());
              v.setId_user(AuthentifierController.currentID);
              v.setType(2);
              sv.addVote(v);
          
          }
                  try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/ShowPostsClient.fxml"));
            Stage myWindow =  (Stage) detail.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           Logger.getLogger(ShowPostsControllerClient.class.getName()).log(Level.SEVERE,null,ex);
        }
    
    }

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        
       
        
       ObservableList<Post> e = listView.getSelectionModel().getSelectedItems();

                 for (Post m : e) {
              
               String file_name="C:\\Users\\DELL\\Desktop\\Pdf\\pdfpayment.pdf";
        Document document = new Document();
        
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        
        Paragraph p =new Paragraph("Description:"+m.getdescription());
        
         Image img =Image.getInstance("C:\\Users\\DELL\\Desktop\\Gestion_utilisateurs_3A11\\src\\GUI\\img\\"+m.getImage());
        document.add(p);
        document.add(img);
         document.close();
          
          }
        

    }

    }

    

