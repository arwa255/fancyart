/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Categorie;
import entity.Commande;
import entity.LigneCommande;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.fxml.Initializable;
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
import javafx.util.Duration;
import service.CategorieService;
import service.CommandeService;
import service.LigneCommandeService;
import tools.MyDB;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class LigneCommandeGestionController implements Initializable {

    @FXML
    private Button supp;
    @FXML
    private Button modif;
    @FXML
    private Button Ajouter;
    @FXML
    private Label imgpathttt;
    @FXML
    private Label labelid;
    @FXML
    private TextField inputRech;
    @FXML
    private Hyperlink Retour;
    @FXML
    private TableView<LigneCommande> tableview;
    @FXML
    private TableColumn<?, ?> id_commande;
    @FXML
    private TableColumn<?, ?> quantité;
    @FXML
    private TableColumn<?, ?> prix_unitaire;
    @FXML
    private Hyperlink Produits;
    @FXML
    private Button confirmer;
 public ObservableList<LigneCommande> list;
 private Label label;
    @FXML
    private ComboBox<Integer> Commandeinput;
     Connection cn;  
    @FXML
    private TextField inputquantité;
    @FXML
    private TextField inputprix_unitaire;
     CommandeService CS = new CommandeService();
  public LigneCommandeGestionController() {
        
      
 cn = MyDB.getInstance().getConnection();         
         
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                try {
            String req = "select * from commande";
         
            
            Statement stm = cn.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {                
                Integer xx = rst.getInt("id_com");
                Commandeinput.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        LigneCommandeService pss = new LigneCommandeService();
        ArrayList<LigneCommande> c = new ArrayList<>();
  
        try {
            c = (ArrayList<LigneCommande>) pss.AfficherAllLigneCommande();
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        
        ObservableList<LigneCommande> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
           
        
        
 id_commande.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
  quantité.setCellValueFactory(new PropertyValueFactory<>("quantité"));

   prix_unitaire.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire"));


   
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllLigneCommande()
            );        
        
        
   FilteredList<LigneCommande> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super LigneCommande>) LigneCommandes -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Integer.toString(LigneCommandes.getId_commande()).toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<LigneCommande> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    

    @FXML
    private void supp(ActionEvent event) throws SQLException {
          if (event.getSource() == supp) {
            LigneCommande rec = new LigneCommande();

            rec.setId_ligne_commande(tableview.getSelectionModel().getSelectedItem().getId_ligne_commande());
            LigneCommandeService cs = new LigneCommandeService();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete ");
      alert.setHeaderText("Are you sure want to delete this LigneCommande");
      alert.setContentText(" ");

      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
           cs.SupprimerLigneCommande(rec);
    
       
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
          
           
         
        
            
            resetTableData();

        }
        
    }
     public void resetTableData() throws SQLDataException, SQLException {
        LigneCommandeService cs = new LigneCommandeService();
        List<LigneCommande> listrec = new ArrayList<>();
        listrec = cs.AfficherAllLigneCommande();
        ObservableList<LigneCommande> data = FXCollections.observableArrayList(listrec);
        tableview.setItems(data);
    }

    @FXML
    private void Modif(ActionEvent event) throws SQLException {
                       labelid.setText( Integer.toString(tableview.getSelectionModel().getSelectedItem().getId_ligne_commande())); 

                Commandeinput.setValue(tableview.getSelectionModel().getSelectedItem().getId_commande()); 
        inputquantité.setText( Integer.toString(tableview.getSelectionModel().getSelectedItem().getQuantité())); 
                inputprix_unitaire.setText( Float.toString(tableview.getSelectionModel().getSelectedItem().getPrix_unitaire())); 

        /*List<Commande> listcommands = CS.AfficherAllCommande();
        for (Commande c : listcommands){
            
        }*/
        
        
        confirmer.setVisible(true);
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
         LigneCommandeService productService = new LigneCommandeService();

        if (inputquantité.getText().equals("") || inputprix_unitaire.getText().equals("")) {
               
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
    
        
        
        else {

            LigneCommande ccc = new LigneCommande( Commandeinput.getValue(),
                    Integer.parseInt(inputquantité.getText()),Integer.parseInt((inputprix_unitaire.getText())));
                   
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
                 productService.ajouterLigneCommande(ccc);

          
      
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
      

          

        }; 
        
        
           resetTableData();  
        
        
        
          /*List<Commande> listcommands = CS.AfficherAllCommande();
        for (Commande c : listcommands){
            
        }*/ 
        
    }

    @FXML
    private void Retour(ActionEvent event) {
    }

    @FXML
    private void Produits(ActionEvent event) {
    }

    @FXML
    private void confirmer(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
            LigneCommandeService productService = new LigneCommandeService();

        if (inputquantité.getText().equals("") || inputprix_unitaire.getText().equals("")) {
               
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
    
        
        
        else {

            LigneCommande ccc = new LigneCommande( Integer.parseInt(labelid.getText()),Commandeinput.getValue(),
                    Integer.parseInt(inputquantité.getText()),Integer.parseInt((inputprix_unitaire.getText())));
                   
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
                 productService.modifierLigneCommande(ccc);

          
      
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
      

          

        }; 
        
        
           resetTableData();  
        
        
    }
    
}
