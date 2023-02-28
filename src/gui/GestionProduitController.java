/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Produit;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ProduitService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class GestionProduitController implements Initializable {
public static Produit connectedproduit;
    @FXML
    private TableView<Produit> tableview;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> description;
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
 public ObservableList<Produit> list;
 private Label label;
    @FXML
    private Hyperlink Catégories;
    @FXML
    private Button pdf2;
    @FXML
    private Button afficherparprix;

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
    public void resetTableData() throws SQLDataException, SQLException {
        ProduitService cs = new ProduitService();
        List<Produit> listrec = new ArrayList<>();
        listrec = cs.listerProduits();
        ObservableList<Produit> data = FXCollections.observableArrayList(listrec);
        tableview.setItems(data);
    }
    @FXML
    private void supp(ActionEvent event) throws SQLException {
         if (event.getSource() == supp) {
            Produit rec = new Produit();

            rec.setId(tableview.getSelectionModel().getSelectedItem().getId());
            ProduitService cs = new ProduitService();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete ");
      alert.setHeaderText("Are you sure want to delete this product");
      alert.setContentText(" ");

      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
           cs.supprimerProduit2(rec);
    
       
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
          
           
                              TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Supprimé avec succés");
            tray.setMessage("Supprimé avec succés");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        
            
            
            resetTableData();

        }
        
    }

    @FXML
    private void Modif(ActionEvent event) throws IOException {
        
                 ProduitService ps = new ProduitService();
        Produit c = new Produit(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getNom(),
                 tableview.getSelectionModel().getSelectedItem().getPrix(),
                tableview.getSelectionModel().getSelectedItem().getDescription(),
               tableview.getSelectionModel().getSelectedItem().getId_categorie()
   
        );
               
               
        GestionProduitController.connectedproduit = c;
        
             Parent page1 = FXMLLoader.load(getClass().getResource("ModifierProduit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
        
        
        
    }

        

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
                Parent page1 = FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        
                       Parent page1 = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void Catégories(ActionEvent event) throws IOException {
        
                Parent page1 = FXMLLoader.load(getClass().getResource("GestionCategorie.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

  private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("..\\ListProduit.pdf"));
        d.open();
        
        PdfPTable pTable = new PdfPTable(3);
       

     
        
        tableview.getItems().forEach((t) -> {
       pTable.addCell(String.valueOf(t.getNom()));
         pTable.addCell(String.valueOf(t.getPrix()));
        pTable.addCell(String.valueOf(t.getDescription()));


       
        });
        
                        d.add(pTable);

        d.close();
        Desktop.getDesktop().open(new File("..\\ListProduit.pdf"));

    } 
    
    
    
    
    
    @FXML
    private void pdf2(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
         if (event.getSource() == pdf2) {
            
             printPDF();
            
   
        }
    
}

    @FXML
    private void afficherparprix(ActionEvent event) throws SQLException {
           ProduitService cs = new ProduitService();
        List<Produit> listrec = new ArrayList<>();
        listrec = cs.listerProduitsParPrix();
        ObservableList<Produit> data = FXCollections.observableArrayList(listrec);
        tableview.setItems(data);
        
        
    }
}