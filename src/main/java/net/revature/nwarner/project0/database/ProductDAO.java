package net.revature.nwarner.project0.database;

import net.revature.nwarner.project0.Driver;
import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.database.utility.DBConnection;
import net.revature.nwarner.project0.models.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ProductDAO {

    public static Logger logger= LogManager.getLogger(ProductDAO.class);;
    Connection conn;
    public ProductDAO() {
        conn = DBConnection.getConnection();
    }

    public  ProductDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean addProduct(Product p) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO product (upc, product_name, department_number) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            ps.setString(parameterIndex++, p.getUpc());
            ps.setString(parameterIndex++, p.getProductName());
            ps.setString(parameterIndex++, p.getDepartmentNumber());
            int count = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int productId;
            if(rs.next()) {
                productId = rs.getInt(1);
                p.setProductId(productId);
                logger.info(String.format("Product [%s] added", productId));
            }
            if(count > 0) return true;
            else return false;

        }catch(Exception e) {
            logger.warn(e.getMessage());
            return false;
        }

    }

    public Boolean updateProduct(Product p) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET upc = ?, product_name = ?, department_number = ? WHERE product_id = ?;");
            int parameterIndex = 1;
            ps.setString(parameterIndex++, p.getUpc());
            ps.setString(parameterIndex++, p.getProductName());
            ps.setString(parameterIndex++, p.getDepartmentNumber());
            ps.setInt(parameterIndex++, p.getProductId());
            int count = ps.executeUpdate();
            if (count > 0) {
                logger.info(String.format("Product [%s] updated", p.getProductId()));
                return true;
            }
            else return false;
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return false;
        }
    }

    public Product searchProductsByUpc(String upc) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE upc = ?;");
            int parameterIndex = 1;
            ps.setString(parameterIndex++, upc);
            ResultSet rs = ps.executeQuery();
            MyArrayList<Product> products = buildResultArray(rs);
            return products.getItem(0);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return null;
    }

    public MyArrayList<Product> searchProductsByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE product_name LIKE ?;");
            int parameterIndex = 1;
            ps.setString(parameterIndex++, String.format("%%%s%%",name));
            ResultSet rs = ps.executeQuery();
            MyArrayList<Product> products = buildResultArray(rs);
            return products;

        } catch (Exception e) {
            logger.warn(e.getMessage());
            return new MyArrayList<>();
        }
    }

    public MyArrayList<Product> searchProductsByDepartment(String department) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE department_number = ?;");
            int parameterIndex = 1;
            ps.setString(parameterIndex++, department);
            ResultSet rs = ps.executeQuery();
            MyArrayList<Product> products = buildResultArray(rs);
            return products;

        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    private MyArrayList<Product> buildResultArray(ResultSet rs) throws SQLException {
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
