/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entity.Produit;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.ProduitService;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowProduitController implements Initializable {

    @FXML
    private ListView<Produit> listView;
   
    ObservableList<Produit> data;
    
    public static int idE ;
    
    ProduitService ds = new ProduitService();

   
    @FXML
    private Button partager;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ProduitService cs = new ProduitService();
            data = (ObservableList<Produit>) ds.getAllProduitObs();
            listView.setItems(data);
            listView.setCellFactory((ListView<Produit> param) -> new ListViewProduit());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    




    @FXML
    private void handleClose(ActionEvent event) {
    }




    }

    

