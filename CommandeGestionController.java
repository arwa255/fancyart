/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commande;
import entity.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.CommandeService;
import services.ProduitService;

import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class CommandeGestionController implements Initializable {

    @FXML
    private TableView<Produit> tableview;
    @FXML
    private TableColumn<?, ?> prix;
    private Button supp;
    @FXML
    private Button Ajouter;
    @FXML
    private Label imgpathttt;
    @FXML
    private Label labelid;
    @FXML
    private TextField inputRech;
    private ComboBox<Integer> inputProduit;
    @FXML
    private Label labelprix;
    @FXML
    private Hyperlink Produits;
    
 public ObservableList<Produit> list;
 private Label label;

   Connection connexion;
    private TableColumn<?, ?> montant;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private Button Afficher;
  public static Produit connectedProduit;
    
      public CommandeGestionController() {
        connexion = MyDB.getInstance().getCon();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProduitService pss = new ProduitService();
        ArrayList<Produit> c = new ArrayList<>();
  
        try {
            c = (ArrayList<Produit>) pss.listerProduits();
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Produit> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);


 nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
   
            try {
            list = FXCollections.observableArrayList(
                    pss.listerProduits()
            );        
        
        
   FilteredList<Produit> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Produit>) Produits -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Produits.getNom().toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Produit> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }    

    
    


    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
          CommandeService commandeService = new CommandeService();
          ProduitService productService = new ProduitService();
   Date date = new Date(System.currentTimeMillis());
         
                 java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());
 

 Commande c = new Commande( tableview.getSelectionModel().getSelectedItem().getId(),   tableview.getSelectionModel().getSelectedItem().getPrix(),
        tableview.getSelectionModel().getSelectedItem().getPrix()
                    ,sqlDate2);
        try {
            commandeService.ajouterCommande(c);
          

          
        } catch (SQLException ex) {
           
        }
        
        
    }



    @FXML
    private void Produits(ActionEvent event) throws IOException {
                  Parent page1 = FXMLLoader.load(getClass().getResource("LigneCommandeGestion.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void Afficher(ActionEvent event) throws IOException {
                ProduitService ps = new ProduitService();
        Produit c = new Produit(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getNom(),
                 tableview.getSelectionModel().getSelectedItem().getPrix(),
                tableview.getSelectionModel().getSelectedItem().getDescription(),
               tableview.getSelectionModel().getSelectedItem().getId_categorie()
   
        );
               
               
        CommandeGestionController.connectedProduit = c;
        
             Parent page1 = FXMLLoader.load(getClass().getResource("DetailProduit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
        
        
    }



    
}
