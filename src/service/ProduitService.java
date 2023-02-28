/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Produit;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tools.MyDB;


public class ProduitService {
       Connection cn;   
  public ProduitService() {
        cn = MyDB.getInstance().getConnection();
    }
 
    
    // Method to add a new produit to the database
    public void ajouterProduit(Produit p) {
        try {
            String query = "INSERT INTO produit (nom, prix, description,id_categorie) VALUES (?, ?, ?,?)";
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, p.getNom());
            ps.setFloat(2, p.getPrix());
            ps.setString(3, p.getDescription());
            ps.setInt(4, p.getId_categorie());
            ps.executeUpdate();
            System.out.println("Produit ajouté avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    // Method to update an existing produit in the database
  public void modifierProduit(Produit u) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE produit SET "
                + "nom ='"   +   u.getNom()+"'"
                + ", prix='"+  u.getPrix()+"'"
                + ", description='"+u.getDescription()+"'"
             
      
                + ", id_categorie ='"+   u.getId_categorie()+"' where id  = "+u.getId()+"";
        Statement stm = cn.createStatement();
        stm.executeUpdate(req);
    }   
    
    // Method to delete an existing produit from the database
    public void supprimerProduit(int id) {
        try {
            String query = "DELETE FROM produit WHERE id=?";
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Produit supprimé avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
      public void supprimerProduit2(Produit u) throws SQLException {

        String req = "DELETE FROM produit WHERE id=?";
        try {
            PreparedStatement ps = cn.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    
    
    // Method to retrieve a list of all produits from the database
      
         public List<Produit> listerProduits() throws SQLException {

        List<Produit> produits = new ArrayList<>();
         
       
        String req = "select * from produit";
        Statement stm = cn.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produit u = new Produit(rst.getInt("id")
                    , rst.getString("nom")
                    , rst.getFloat("prix")
                    , rst.getString("description")
                    , rst.getInt("id_categorie")
                
            
            );
            produits.add(u);
        }
        return produits;
    }  
         public List<Produit> listerProduitsParPrix() throws SQLException {

        List<Produit> produits = new ArrayList<>();
         
       
        String req = "select * from produit order by prix";
        Statement stm = cn.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produit u = new Produit(rst.getInt("id")
                    , rst.getString("nom")
                    , rst.getFloat("prix")
                    , rst.getString("description")
                    , rst.getInt("id_categorie")
                
            
            );
            produits.add(u);
        }
        return produits;
    }    
 
    
 
}