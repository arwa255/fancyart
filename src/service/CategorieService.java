/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Categorie;
import entity.Produit;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MyDB;

/**
 *
 * @author Administrateur
 */
public class CategorieService {
          Connection cn;   
  public CategorieService() {
        cn = MyDB.getInstance().getConnection();
    }
  // Method to add a new produit to the database
    public void ajouterCategorie(Categorie p) {
        try {
            String query = "INSERT INTO catégorie (type) VALUES (?)";
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, p.getType());
        
            ps.executeUpdate();
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       /*
    public void modifierOffre(Offre e) throws SQLException, NoSuchAlgorithmException {
       
        String req = "UPDATE offre_emploi SET "
                + " categorie_emploi_id ='"+e.getCategorie_emploi_id()+"'"
                + ", image='"+e.getImage()+"'"
                + ", description='"+e.getDescription()+"'"
             + ", type='"+e.getType()+"'"
                + ", profil_souhaite='"+e.getProfil_souhaite()+"'"
                + ", entreprise='"+e.getEntreprise()+"'"
                + ", position='"+e.getPosition()+"'"
                + ", date_exp='" + (java.sql.Date) (Date) e.getDate_exp()+"'"
                + ", updated_at='"+ (java.sql.Date) (Date) e.getUpdated_at()+"'"
                + ", fonctionnalites='"+e.getFonctionnalites()+"' where id  = "+e.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    } */
    // Method to update an existing produit in the database
    public void modifierCategorie(Categorie u) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE catégorie SET type='"+u.getType()+"' where id  ="+u.getId()+"";

        Statement stm = cn.createStatement();
        stm.executeUpdate(req);
    }   
    
    // Method to delete an existing produit from the database
    public void supprimerCategorie(int id) {
        try {
            String query = "DELETE FROM catégorie WHERE id=?";
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
     
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
      public void supprimerCategorie2(Categorie u) throws SQLException {

        String req = "DELETE FROM catégorie WHERE id=?";
        try {
            PreparedStatement ps = cn.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    
    
    // Method to retrieve a list of all produits from the database
      
         public List<Categorie> listerCategorie() throws SQLException {

        List<Categorie> categorie = new ArrayList<>();
         
       
        String req = "select * from catégorie";
        Statement stm = cn.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Categorie u = new Categorie(rst.getInt("id")
                    , rst.getString("type")  );
              
                
            
          
            categorie.add(u);
        }
        return categorie;
    }  
      
 
    
  }
