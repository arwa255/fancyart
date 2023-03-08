/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import services.ServiceUser;


/**
 * FXML Controller class
 * 
 * @author MED KHALIL
 */
public class Afficher_userController implements Initializable {

    @FXML
    private Pane afficher_user;
    @FXML
    private TableView<User> tab_aff;
    @FXML
    private TableColumn<User, Integer> id_aff;
    @FXML
    private TableColumn<User, String> nom_aff;
    @FXML
    private TableColumn<User, String> prenom_aff;
    @FXML
    private TableColumn<User, String> adresse_aff;
    @FXML
    private TableColumn<User, String> mdp_aff;
    @FXML
    private TableColumn<User, String> mail_aff;
    @FXML
    private TableColumn<User, String> role_aff;
    @FXML
    private Button aff_butt;
    @FXML
    private Button supp_butt;
    @FXML
    private Button trier_butt;
    @FXML
    private ChoiceBox<String> filtrer_role;
    @FXML
    private Button count_butt;
    @FXML
    private TextField chercher_id;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id_aff.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_aff.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_aff.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresse_aff.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        mdp_aff.setCellValueFactory(new PropertyValueFactory<>("Mot_de_passe"));
        mail_aff.setCellValueFactory(new PropertyValueFactory<>("adresse_mail"));
        role_aff.setCellValueFactory(new PropertyValueFactory<>("role"));
        
         ObservableList<String> roles = FXCollections.observableArrayList("Tous le rôles","admin", "client");
        filtrer_role.setItems(roles);
        filtrer_role.getSelectionModel().selectFirst();
    }
    
    
    private ObservableList<User>User;
    
    @FXML
    public void afficherUser(ActionEvent event) throws SQLException {
        ServiceUser us = new ServiceUser();
        User = FXCollections.observableArrayList(us.afficher());
        tab_aff.setItems(User);
        tab_aff.setOpacity(1);
    }
    
    @FXML
    public void supprimerUser(ActionEvent event) throws SQLException {
    int selectedIndex = tab_aff.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        User userASupprimer = tab_aff.getItems().get(selectedIndex);
        ServiceUser us = new ServiceUser();
        us.supprimer(userASupprimer);
        tab_aff.getItems().remove(selectedIndex);
    } else {
        // Aucun utilisateur sélectionné
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aucune sélection");
        alert.setHeaderText("Aucun utilisateur sélectionné");
        alert.setContentText("Veuillez sélectionner un utilisateur dans la liste.");
        alert.showAndWait();
    }
}
     @FXML
public void trierParNom(ActionEvent event) throws SQLException {
    ServiceUser serviceUser = new ServiceUser();
    List<User> users = serviceUser.trierParNom();
    ObservableList<User> list = FXCollections.observableArrayList(users);
    tab_aff.setItems(list);
    id_aff.setCellValueFactory(new PropertyValueFactory<>("id"));
    nom_aff.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenom_aff.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    adresse_aff.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    mdp_aff.setCellValueFactory(new PropertyValueFactory<>("mot_de_passe"));
    mail_aff.setCellValueFactory(new PropertyValueFactory<>("adresse_mail"));
    role_aff.setCellValueFactory(new PropertyValueFactory<>("role"));
}
 @FXML
private void filterByRoleAction(ActionEvent event) throws SQLException {
    ServiceUser serviceUser = new ServiceUser();
    String selectedRole = filtrer_role.getSelectionModel().getSelectedItem();
    if(selectedRole.equals("Tous les rôles")) {
        User = FXCollections.observableArrayList(serviceUser.afficher());
        tab_aff.setItems(User);
    } else {
        // Afficher uniquement les utilisateurs avec le rôle sélectionné
        List<User> filteredUsers = serviceUser.filterUsersByRole(selectedRole);
        ObservableList<User> data = FXCollections.observableArrayList(filteredUsers);
        tab_aff.setItems(data);
    }
}
    @FXML
    public void countUtilisateurs() throws SQLException {
    ServiceUser serviceUser = new ServiceUser();
    int count = serviceUser.countUsers();
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Nombre d'utilisateurs");
    alert.setHeaderText(null);
    alert.setContentText("Il y a " + count + " utilisateurs dans la base de données.");
    alert.showAndWait();
}
@FXML
public void chercherParId(ActionEvent event) throws SQLException {
    String input = chercher_id.getText();
    ServiceUser us = new ServiceUser();
    ObservableList<User> userList = FXCollections.observableArrayList();
    if (input.isEmpty()) {
        userList.addAll(us.afficher());
    } else {
        int id = Integer.parseInt(input);
        User user = us.chercherParId(id);
        if (user != null) {
            userList.add(user);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("L'utilisateur n'a pas été trouvé");
            alert.showAndWait();
        }
    }
    tab_aff.getItems().clear();
    tab_aff.setItems(userList);
}









}

  
    

