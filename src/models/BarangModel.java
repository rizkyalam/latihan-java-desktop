/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import configs.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author rizkyalam
 */
public class BarangModel extends Database {
    public BarangModel() {
        super();
    }
    
    public ArrayList<ArrayList<Object>> all(int userId) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM barang WHERE user_id = "+ Integer.toString(userId));
        
        ArrayList<ArrayList<Object>> listData = new ArrayList<>();
        
        int i = 0;
        
        while (result.next()){
            int id = result.getInt(1);
            String nama = result.getString(3);
            int stok = result.getInt(4);
            
            listData.add(new ArrayList());
            
            listData.get(i).add(id);
            listData.get(i).add(nama);
            listData.get(i).add(stok);
            
            i++;
        }
        
        return listData;
    }
    
    public void insert(
        int userId,
        String namaBarang,
        int stok
    ) throws SQLException {
         String query = "INSERT INTO barang (user_id, nama, stok) VALUES (?, ?, ?)";

        PreparedStatement statement = this.conn.prepareStatement(query);
        statement.setInt(1, userId);
        statement.setString(2, namaBarang);
        statement.setInt(3, stok);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(null, "Barang berhasil di tambahkan");
        }
    }
    
    public ArrayList<Integer> getAllId(int userId) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("SELECT id FROM barang WHERE user_id = "+ Integer.toString(userId));
        
        ArrayList<Integer> listId = new ArrayList();
        
        while (result.next()){
            int id = result.getInt("id");
            
            listId.add(id);
        }
        
        return listId;
    }
    
    public ArrayList<Object> getById(int id, int userId) throws SQLException {
        String idStr = Integer.toString(id);
        String userIdStr = Integer.toString(userId);
        
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(
            "SELECT nama, stok FROM barang WHERE user_id = "+ userIdStr +" and id = "+ idStr
        );

        String name = null;
        int stock = 0;

        if(result.next()){
            name = result.getString("nama");
            stock = result.getInt("stok");
        }
        
        ArrayList<Object> newResultList = new ArrayList<>();
        newResultList.add(name);
        newResultList.add(stock);

        return newResultList;
    }
    
    public void update(
        int id, 
        int userId,
        String name,
        int stock
    ) throws SQLException {
        String sql = "UPDATE barang SET nama=?, stok=? WHERE id=? and user_id=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, stock);
        statement.setInt(3, id);
        statement.setInt(4, userId);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Data barang dengan ID = "+ id +" berhasil di ubah");
        }
    }
    
    public void deleteById(int id, int userId) throws SQLException {
        String sql = "DELETE FROM barang WHERE id=? and user_id=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setInt(2, userId);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(null, "Data barang dengan ID = "+ id +" berhasil di hapus");
        }
    }
}
