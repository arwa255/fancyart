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
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import utils.MyDB;


/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class CommandeAdminController implements Initializable {

    @FXML
    private TableView<Commande> tableview;
    @FXML
    private TableColumn<?, ?> produit_id;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> montant;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private Button supp;
    @FXML
    private Button modif;
    @FXML
    private Button Ajouter;
    @FXML
    private Label labelid;
    @FXML
    private TextField inputRech;
    @FXML
    private ComboBox<Integer> inputProduit;
    @FXML
    private Button confirmer;
   public ObservableList<Commande> list;
    Connection connexion;
 private Label label;
    @FXML
    private TextField inputprix;
    @FXML
    private TextField inputmontant;
    @FXML
    private Hyperlink lignecommande;
    @FXML
    private Hyperlink produitgo;
      public CommandeAdminController() {
        connexion = MyDB.getInstance().getCon();;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             try {
            String req = "select * from produit";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
                
                Integer xx = rst.getInt("id");
                inputProduit.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        CommandeService pss = new CommandeService();
        ArrayList<Commande> c = new ArrayList<>();
  
        try {
            c = (ArrayList<Commande>) pss.AfficherAllCommande();
        } catch (SQLException ex) {
        }

        ObservableList<Commande> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);


 produit_id.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
           date.setCellValueFactory(new PropertyValueFactory<>("date_com"));

            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllCommande()
            );        
        
        
                FilteredList<Commande> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Commande>) Commandes -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Float.toString(Commandes.getPrix()).toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Commande> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }    
   public void resetTableData() throws SQLDataException, SQLException {
        CommandeService cs = new CommandeService();
         List<Commande> listrec = new ArrayList<>();
        listrec = cs.AfficherAllCommande();
        ObservableList<Commande> data = FXCollections.observableArrayList(listrec);
        tableview.setItems(data);
    }
    @FXML
    private void supp(ActionEvent event) throws SQLException {
         if (event.getSource() == supp) {
            Commande rec = new Commande();

            rec.setId_com(tableview.getSelectionModel().getSelectedItem().getId_com());
            CommandeService cs = new CommandeService();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete ");
      alert.setHeaderText("Are you sure want to delete this product");
      alert.setContentText(" ");

      // option != null.
             Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
           cs.SupprimerCommande(rec);
    resetTableData();
       
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
          
        
        
            
            
            

        }
        
        
    }

    @FXML
    private void Modif(ActionEvent event) {
        labelid.setText( Integer.toString(tableview.getSelectionModel().getSelectedItem().getId_com())); 
                inputProduit.setValue(tableview.getSelectionModel().getSelectedItem().getId_produit()); 
                inputprix.setText(Float.toString(tableview.getSelectionModel().getSelectedItem().getPrix())); 
                
                inputmontant.setText(Float.toString(tableview.getSelectionModel().getSelectedItem().getMontant())); 

        confirmer.setVisible(true);
        
        
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
         CommandeService commandeService = new CommandeService();
          ProduitService productService = new ProduitService();
   Date date = new Date(System.currentTimeMillis());
         
                 java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());
        if (inputProduit.getValue().equals("")
                
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        }
       
            else{
 Commande c = new Commande(inputProduit.getValue(),
         Float.parseFloat(inputprix.getText()),
        Float.parseFloat(inputmontant.getText())    ,sqlDate2);
 
 
                
        try {
            commandeService.ajouterCommande(c);
          resetTableData();

          
        } catch (SQLException ex) {
           
        }
        }  
        
        
    }

    @FXML
    private void confirmer(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
            CommandeService commandeService = new CommandeService();
          ProduitService productService = new ProduitService();
   Date date = new Date(System.currentTimeMillis());
         
                 java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());
        if (inputProduit.getValue().equals("")
                
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        }
       
            else{
 Commande c = new Commande(Integer.parseInt(labelid.getText()),inputProduit.getValue(),
       Float.parseFloat(inputprix.getText()),
        Float.parseFloat(inputmontant.getText()),   sqlDate2);
 
 
                
        try {
            commandeService.modifierCommande(c);
          resetTableData();

          
        } catch (SQLException ex) {
           
        }
        }  
        
        
        
        
    }

    @FXML
    private void lignecommande(ActionEvent event) throws IOException {
          Parent page1 = FXMLLoader.load(getClass().getResource("LigneCommandeGestion.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
         
        
    }

    @FXML
    private void produitgo(ActionEvent event) throws IOException {
          Parent page1 = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
         
    }

    
}
