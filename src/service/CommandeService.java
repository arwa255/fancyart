/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Commande;
import entity.Produit;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tools.MyDB;

/**
 *
 * @author Administrateur
 */
public class CommandeService {
       Connection connexion;   
  public CommandeService() {
        connexion = MyDB.getInstance().getConnection();
    }
 
  
 // @Override
  public void ajouterCommande(Commande e) throws SQLException {
        String req = "INSERT INTO `commande` (`id_produit`,`prix`,`montant`,`date_com`) "
                + "VALUES (?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
    
        ps.setInt(1, e.getId_produit());
       ps.setFloat(2, e.getPrix());

        ps.setFloat(3, e.getMontant());
        ps.setDate(4,(java.sql.Date) (Date) e.getDate_com());
        ps.executeUpdate();
    }

 // @Override
     public List<Commande> AfficherAllCommande() throws SQLException {
        List<Commande> Commandes = new ArrayList<>();
        String req = "select * from commande ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Commande e = new Commande(rst.getInt("id_com")
                    , rst.getInt("id_produit")
                    , rst.getFloat("prix")
                    , rst.getFloat("montant")
                      
                   
                   
                    , rst.getDate("date_com"));
            Commandes.add(e);
        }
        return Commandes;
    }
     





     public void SupprimerCommande(Commande e) throws SQLException {

        String req = "DELETE FROM commande WHERE id_com =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId_com());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

   


      public void modifierCommande(Commande e) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE commande SET "
                + " id_produit='"+e.getId_produit()+"'"
                 + ", prix='"+e.getPrix()+"'"
                 + ", montant='"+  e.getMontant()+"'"
          
              
                + ", date_com='"+ (java.sql.Date) (Date) e.getDate_com()+"' where id_com  = "+e.getId_com()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
      
      public Produit findProduit(int id) throws SQLException{
    
                Produit c = new Produit();
           
        String requete="select * from produit where id="+id;
       
            Statement st = connexion.createStatement();
            ResultSet rst = st.executeQuery(requete);
            while(rst.next())
            {  
              
  Produit e = new Produit(rst.getInt("id")
                    , rst.getString("nom")
                    , rst.getFloat("prix")
                    , rst.getString("description")
                      , rst.getInt("id_categorie")
                  );
           c=e;
            
            } 
               return c;                 
      }   
      
      
      
      
}
