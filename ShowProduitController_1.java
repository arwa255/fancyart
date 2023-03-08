/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entity.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import services.ProduitService;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowProduitController_1 implements Initializable {

    private ListView<Produit> listView;
   
    ObservableList<Produit> data;
    
    public static int idE ;
    
    ProduitService ds = new ProduitService();

   
    @FXML
    private TilePane tilepane_subscription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    




    @FXML
    private void show_subscription(ActionEvent event) throws FileNotFoundException, SQLException {
  //:subscription.setVisible(false);
       // list.setVisible(false);
        tilepane_subscription.setVisible(true);
        tilepane_subscription.setVisible(true);
        tilepane_subscription.getChildren().clear();
            
            List<Produit> items = new ArrayList<>();
            items=ds.listerProduits();

             for (Produit item : items) {
                
                Image image;

                 System.out.println("image"+item.getImgae());
                ImageView imageView = new ImageView(new Image("/GUI/img/"+item.getImgae()));
                imageView.setFitHeight(150);
                imageView.setFitWidth(150);
                Button btn = new Button("Delete");
                btn.setId(Integer.toString(item.getId_categorie()));
                btn.setStyle("-fx-background-color: black; -fx-background-radius: 20; -fx-font-family: \"Franklin Gothic Medium\";-fx-text-fill: #F9F7DD;");
                Label nbUsers = new Label(Integer.toString(1)+" Current Users");
                Label type = new Label(item.getDescription());
                Label price = new Label(Integer.toString((int) item.getPrix())+" DT");
                nbUsers.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                type.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                price.setStyle("-fx-font-family: \"Franklin Gothic Medium\";");
                VBox vBox = new VBox();
                vBox.getChildren().addAll(imageView,type,price,nbUsers);
                vBox.setAlignment(Pos.CENTER);
                tilepane_subscription.getChildren().add(vBox);
                vBox.setOnMouseClicked(e -> {
                    // Create a new stage to display the subscription details
                    Stage detailsStage = new Stage();
                    detailsStage.setTitle("Subscription Details");
                    
                    // Create text fields to display the subscription details
                    TextField typeField = new TextField(item.getDescription());
                    typeField.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 10,0,0,1 );");
                    typeField.setEditable(true);
                    TextField priceField = new TextField(Integer.toString((int) item.getPrix()));
                    priceField.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 10,0,0,1 );");
                    priceField.setEditable(true);
                    
                    
                    // Add the text fields to a grid pane
                    GridPane detailsPane = new GridPane();
                    detailsPane.setAlignment(Pos.CENTER);
                    detailsPane.setStyle("-fx-background-color: #F9F7DD;");
                    detailsPane.setVgap(0);
                    detailsPane.setHgap(0);
                    Label typeLabel = new Label("Type: ");
                    typeLabel.setFont(Font.font("Franklin Gothic Medium", FontWeight.NORMAL, 18));
                    detailsPane.add(typeLabel, 0, 0);
                    detailsPane.add(typeField, 1, 0);
                    Label priceLabel = new Label("Price: ");
                    priceLabel.setFont(Font.font("Franklin Gothic Medium", FontWeight.NORMAL, 18));
                    detailsPane.add(priceLabel, 0, 1);
                    detailsPane.add(priceField, 1, 1);
                    Button updateButton = new Button("Update");
                    updateButton.setStyle("-fx-background-color: black; -fx-background-radius: 20; -fx-font-family: \"Franklin Gothic Medium\"; -fx-text-fill: #F9F7DD;");
                    detailsPane.add(updateButton, 0, 2, 2, 1);
                    GridPane.setHalignment(updateButton, HPos.CENTER);
                   /* updateButton.setOnAction(e1 -> {
                        if(validateInputs2(typeField.getText(),priceField.getText())==true){
                            // Update the subscription with the new details
                            item.setSubscription_type(typeField.getText());
                            item.setSubscription_price(Integer.parseInt(priceField.getText()));
                            type.setText(item.getSubscription_type());
                            price.setText(Integer.toString(item.getSubscription_price())+" DT");
                            Ss.update(item);
                            // Close the window
                            detailsStage.close();
                        }
                    });
                    */
                    // Add the grid pane to the scene
                    Scene detailsScene = new Scene(detailsPane, 400, 300);
                    detailsStage.setScene(detailsScene);
                    
                    // Show the stage
                    detailsStage.show();
                });
                btn.setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Are you sure you want to Delete this item?");
                    Optional<ButtonType> result = alert.showAndWait();
                    
                    if (result.get() == ButtonType.OK){
                        ds.supprimerProduit(item.getId());
                        tilepane_subscription.getChildren().remove(vBox);
                    }
                });
                
                
                    



                     }
            
        
    }
    }
    