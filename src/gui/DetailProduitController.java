/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class DetailProduitController implements Initializable {

    @FXML
    private Label labelnomproduit;
    @FXML
    private Label labelnomprix;
    @FXML
    private Label labeldescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelnomprix.setText(CommandeGestionController.connectedProduit.getNom());
        labeldescription.setText(CommandeGestionController.connectedProduit.getDescription());
        labelnomprix.setText(Float.toString(CommandeGestionController.connectedProduit.getPrix()));
    }    
    
}
