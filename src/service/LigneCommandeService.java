/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Commande;
import entity.LigneCommande;
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
public class LigneCommandeService {
      Connection connexion;   
  public LigneCommandeService() {
        connexion = MyDB.getInstance().getConnection();
    }
 
  
 // @Override
  public void ajouterLigneCommande(LigneCommande e) throws SQLException {
        String req = "INSERT INTO `lignecommande` (`id_commande`,`quantité`,`prix_unitaire`) "
                + "VALUES (?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
    
        ps.setInt(1, e.getId_commande());
       ps.setInt(2, e.getQuantité());

        ps.setFloat(3, e.getPrix_unitaire());
     
        ps.executeUpdate();
    }

 // @Override
     public List<LigneCommande> AfficherAllLigneCommande() throws SQLException {
        List<LigneCommande> LigneCommandes = new ArrayList<>();
        String req = "select * from lignecommande ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            LigneCommande e = new LigneCommande(rst.getInt("id_ligne_commande")
                    , rst.getInt("id_commande")
                    , rst.getInt("quantité")
                    , rst.getFloat("prix_unitaire"));
                      

            LigneCommandes.add(e);
        }
        return LigneCommandes;
    }
     





     public void SupprimerLigneCommande(LigneCommande e) throws SQLException {

        String req = "DELETE FROM lignecommande WHERE id_ligne_commande =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId_ligne_commande());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

   


      public void modifierLigneCommande(LigneCommande e) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE lignecommande SET "
                + " id_commande ='"+e.getId_commande()+"'"
                 + ", quantité='"+e.getQuantité()+"'"
                
          
              
                + ", prix_unitaire='"+ e.getPrix_unitaire()+"' where id_ligne_commande   = "+e.getId_ligne_commande()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
      
    /*  public Produit findProduit(int id) throws SQLException{
    
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
      }   */
      
      
      
      
}
