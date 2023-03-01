/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services; 
import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author DELL
 */
public class ReclamationService implements IService <Reclamation> {
Connection cnx;

    public ReclamationService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reclamation t) throws SQLException {
        
      /* PreparedStatement stmt = cnx.prepareStatement("INSERT INTO reclamation (message_rec) VALUES (?)");
       
       stmt.setString(1, t.getMessage_rec());
        Statement st = cnx.createStatement();
        stmt.executeUpdate();
*/
        String req = "INSERT INTO reclamation (message_rec,type,statut) VALUES("
                + "'" + t.getMessage_rec() + "','" + t.getType () + "','" + t.getStatut() + "'" + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
      
      
    }

    @Override
    public boolean modifier(Reclamation t) throws SQLException {
        boolean ok= false;
        try {
        String req = "UPDATE reclamation SET message_rec = ?,type = ? , statut = ?   where id_reclamation = ?";
        PreparedStatement vs = cnx.prepareStatement(req);
        vs.setString(1, t.getMessage_rec());
        vs.setString(2, t.getStatut());
        vs.setString(3, t.getType());
        vs.setInt(4, t.getId_reclamation());
        vs.executeUpdate();
        ok = true; 
        }
        catch (SQLException ex){
            System.out.println("error in delete" + ex);
        }
return ok;
    }

    @Override
    public boolean supprimer(Reclamation t) throws SQLException {
        
        boolean ok = false;
        try { 
         String req = " DELETE FROM reclamation where id_reclamation = ?   ";
         
            PreparedStatement vs = cnx.prepareStatement(req);
             vs.setInt(1, t.getId_reclamation());
            vs.executeUpdate();
            ok = true;
    }
        catch(SQLException ex) {
            System.out.println("EREUR()"+ex);
        }
        
        return ok;
    }

    @Override
    public List<Reclamation> recuperer() throws SQLException {
          List<Reclamation> Reclamation = new ArrayList<>();
        String s = "select * from reclamation ";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Reclamation R = new Reclamation();
            
            R.setId_reclamation(rs.getInt("id_reclamation"));
            R.setMessage_rec(rs.getString("message_rec"));
            R.setStatut(rs.getString("statut"));
             R.setType(rs.getString("type"));
            
            
            
            Reclamation.add(R);
            
        }
        return Reclamation;
    }
    
    

 public Reclamation TrouverById(int id) {
        Reclamation R = null;
        String Req = "select * from reclamation where id_reclamation = " + id + "";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(Req); //recherche
            while (res.next()) {

               R = new Reclamation (res.getInt("id_reclamation"), res.getString("message_rec"), res.getString("type"), res.getString("statut"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return R;
    }

 
 

 public Reclamation TrouverType(String type) {
        Reclamation R = null;
        String Req = "select * from reclamation where type like '%"+ type +"%'" ;
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(Req); //recherche
            while (res.next()) {

               R = new Reclamation (res.getInt("id_reclamation"), res.getString("message_rec"), res.getString("type"), res.getString("statut"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return R;
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}
