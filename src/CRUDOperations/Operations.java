package CRUDOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import Connection.JDBC;
import Model.ProductModel;

public class Operations {
    public void addProducts(ProductModel products){
        try {
            String addQry = "INSERT INTO products (name, quantity, price) VALUES(?, ?, ?)";  //
            Connection conn = JDBC.getConnection(); //static method Class dara access kora jabe.object create kore access korte hoy na.
            PreparedStatement addStmt = conn.prepareStatement(addQry);

            String name = products.getName();
            int quantity = products.getQuantity();
            Double price = products.getPrice();

            addStmt.setString(1, name);
            addStmt.setInt(2, quantity);
            addStmt.setDouble(3, price);

            addStmt.executeUpdate();
        }
        catch (SQLException sqlx) {
            System.out.println("Connection Failed! \nProduct not Added!" + sqlx);
        }
        catch(NullPointerException npx){       
            System.out.println("Connection is null! \nProduct not Added!" + npx);
        }
    }

    public List<ProductModel> getProducts(){   //productmodel er object store kora jabe ei list a. list name products
        List<ProductModel> productsList = new ArrayList<>(); 
        try {
            String disQry = "SELECT * FROM products";
            Connection conn = JDBC.getConnection();

            PreparedStatement disStmt = conn.prepareStatement(disQry);    
            ResultSet list = disStmt.executeQuery(disQry);
            
            while(list.next() == true){
                int id = list.getInt("id");
                String name = list.getString("name");
                int quantity = list.getInt("quantity");
                Double price = list.getDouble("price");

                ProductModel products = new ProductModel(id, name, quantity, price);
                productsList.add(products);
            }
        }
        catch (SQLException sqlx) {
            System.out.println("Connection Failed! \n Cannot View Products" + sqlx);
        } 
        catch(NullPointerException npEx){  
            System.out.println("Connection is null! \nCannot View Products"+ npEx);
        }
        return productsList;
    }

    public void updateProducts(ProductModel products){
        try {
            String updateQry = "UPDATE products SET name = ?, quantity = ?, price = ? WHERE id = ?";
            Connection conn = JDBC.getConnection();

            PreparedStatement upstmt = conn.prepareStatement(updateQry);
            String name = products.getName();
            int quantity = products.getQuantity();
            Double price = products.getPrice();
            int id = products.getId();

            upstmt.setString(1, name);
            upstmt.setInt(2, quantity);
            upstmt.setDouble(3, price);
            upstmt.setInt(4, id);

            upstmt.executeUpdate();
        } 
        catch (SQLException sqlx) {
            System.out.println("Connection Failed! \nUpdate Failed" + sqlx);
        } 
        catch(NullPointerException npx){  
            System.out.println("Connection is null! \nUpdate Failed"+ npx);
        }
    }

    public void delProducts(int id){
        try {
            String delQry = "DELETE from products WHERE id = ?";
            Connection con = JDBC.getConnection();

            PreparedStatement delstmt = con.prepareStatement(delQry);
            delstmt.setInt(1, id);

            delstmt.executeUpdate();
        } 
        catch (SQLException sqlx) {
            System.out.println("Connection Failed! \nCannot Delete" + sqlx);
        } 
        catch(NullPointerException npx){  
            System.out.println("Connection is null!\n Cannot Delete"+ npx);
        }
    }

    public boolean idExists(int id){
        boolean exist = false;
        try{
            String findQry = "SELECT * from products WHERE id = ?";
            Connection con = JDBC.getConnection();
            PreparedStatement fstmt = con.prepareStatement(findQry);    

            fstmt.setInt(1, id);
            ResultSet rs = fstmt.executeQuery();
            exist = rs.next();

        } catch(SQLException sqx){
            System.out.println("Connection failed"+sqx);
        }
        return exist;
    }

}
