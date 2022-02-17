package net.revature.nwarner.project0.database;

import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.database.utility.DBConnection;
import net.revature.nwarner.project0.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {

    public static boolean addProduct(Product p) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO product (upc, product_name, department_number) VALUES (?, ?, ?);");
            int parameterIndex = 1;
            ps.setString(parameterIndex++, p.getUpc());
            ps.setString(parameterIndex++, p.getProductName());
            ps.setString(parameterIndex++, p.getDepartmentNumber());
            int count = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int productId = rs.getInt(1);
            p.setProductId(productId);
            if(count > 0) return true;
            else return false;

        }catch(Exception e) {
            return false;
        }

    }

    public static Boolean updateProduct(Product p) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET upc = ?, product_name = ?, department_number = ? WHERE product_id = ?;");
            int parameterIndex = 1;
            ps.setString(parameterIndex++, p.getUpc());
            ps.setString(parameterIndex++, p.getProductName());
            ps.setString(parameterIndex++, p.getDepartmentNumber());
            ps.setInt(parameterIndex++, p.getProductId());
            int count = ps.executeUpdate();
            if (count > 0) return true;
            else return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static MyArrayList<Product> searchProductsByUpc(String upc) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE upc = ?;");
            int parameterIndex = 1;
            ps.setString(parameterIndex++, upc);
            ResultSet rs = ps.executeQuery();
            MyArrayList<Product> products = buildResultArray(rs);
            return products;
        } catch (Exception e) {

        }

        return null;
    }

    public static MyArrayList<Product> searchProductsByName(String name) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE product_name LIKE ?;");
            int parameterIndex = 1;
            ps.setString(parameterIndex++, String.format("%%%s%%",name));
            ResultSet rs = ps.executeQuery();
            MyArrayList<Product> products = buildResultArray(rs);
            return products;

        } catch (Exception e) {
            return null;
        }
    }

    public static MyArrayList<Product> searchProductsByDepartment(String department) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE department_number = ?");
            int parameterIndex = 1;
            ps.setString(parameterIndex++, department);
            ResultSet rs = ps.executeQuery();
            MyArrayList<Product> products = buildResultArray(rs);
            return products;

        } catch (Exception e) {

        }
        return null;
    }

    private static MyArrayList<Product> buildResultArray(ResultSet rs) throws SQLException {
        MyArrayList<Product> products = new MyArrayList<>();
        while (rs.next()) {
            int productId = rs.getInt("product_id");
            String productUpc = rs.getString("upc");
            String productName = rs.getString("product_name");
            String productDepartment = rs.getString("department_number");
            Product p = new Product(productId, productUpc, productName, productDepartment);
            products.addItem(p);
        }

        return products;
    }
}
