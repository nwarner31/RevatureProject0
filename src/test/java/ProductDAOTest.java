
import net.revature.nwarner.project0.database.ProductDAO;
import net.revature.nwarner.project0.models.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductDAOTest {

    ProductDAO testProductDAO;
    Connection mockConn;
    ResultSet mockResultSet;
    PreparedStatement mockPrepared;

    @BeforeAll
    public void setup() {
        mockConn = Mockito.mock(Connection.class);
        testProductDAO = new ProductDAO(mockConn);
    }

    @Test
    public void addProductTestGood() throws SQLException {
        mockPrepared = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        Mockito.when(mockConn.prepareStatement("INSERT INTO product (upc, product_name, department_number) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS)).thenReturn(mockPrepared);
        Mockito.when(mockPrepared.executeUpdate()).thenReturn(1);
        Mockito.when(mockPrepared.getGeneratedKeys()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        Product p = new Product(1, "12345", "Paw Patrol Plush", "7");

        boolean returnValue = testProductDAO.addProduct(p);
        assertTrue(returnValue);
    }

    @Test
    public void updateProductTestGood() throws SQLException {
        mockPrepared = Mockito.mock(PreparedStatement.class);
        Mockito.when(mockConn.prepareStatement("UPDATE product SET upc = ?, product_name = ?, department_number = ? WHERE product_id = ?;")).thenReturn(mockPrepared);
        Mockito.when(mockPrepared.executeUpdate()).thenReturn(1);

        Product p = new Product(1, "12345", "Paw Patrol Plush", "7");

        boolean returnValue = testProductDAO.updateProduct(p);
        assertTrue(returnValue);
    }

    @Test
    public void searchProductsByUpcTestGood() throws SQLException {
        Product mockProduct = new Product(1, "12345", "Paw Patrol Plush", "7");
        mockResultSet = Mockito.mock(ResultSet.class);
        mockPrepared = Mockito.mock(PreparedStatement.class);

        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getInt("product_id")).thenReturn(1);
        Mockito.when(mockResultSet.getString("upc")).thenReturn("12345");
        Mockito.when(mockResultSet.getString("product_name")).thenReturn("Paw Patrol Plush");
        Mockito.when(mockResultSet.getString("department_number")).thenReturn("7");
        Mockito.when(mockConn.prepareStatement("SELECT * FROM product WHERE upc = ?;")).thenReturn(mockPrepared);
        Mockito.when(mockPrepared.executeQuery()).thenReturn(mockResultSet);

        Product p = testProductDAO.searchProductsByUpc("12345");

        assertEquals(mockProduct, p);
    }

    @Test
    public void searchProductsByNameTestGood() throws SQLException {
        Product mockProduct = new Product(1, "12345", "Paw Patrol Plush", "7");
        mockResultSet = Mockito.mock(ResultSet.class);
        mockPrepared = Mockito.mock(PreparedStatement.class);

        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getInt("product_id")).thenReturn(1);
        Mockito.when(mockResultSet.getString("upc")).thenReturn("12345");
        Mockito.when(mockResultSet.getString("product_name")).thenReturn("Paw Patrol Plush");
        Mockito.when(mockResultSet.getString("department_number")).thenReturn("7");
        Mockito.when(mockConn.prepareStatement("SELECT * FROM product WHERE product_name LIKE ?;")).thenReturn(mockPrepared);
        Mockito.when(mockPrepared.executeQuery()).thenReturn(mockResultSet);

        Product p = testProductDAO.searchProductsByName("Paw Patrol").getItem(0);

        assertEquals(mockProduct, p);
    }

    @Test
    public void searchProductsByDepartmentTestGood() throws SQLException {
        Product mockProduct = new Product(1, "12345", "Paw Patrol Plush", "7");
        mockResultSet = Mockito.mock(ResultSet.class);
        mockPrepared = Mockito.mock(PreparedStatement.class);

        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getInt("product_id")).thenReturn(1);
        Mockito.when(mockResultSet.getString("upc")).thenReturn("12345");
        Mockito.when(mockResultSet.getString("product_name")).thenReturn("Paw Patrol Plush");
        Mockito.when(mockResultSet.getString("department_number")).thenReturn("7");
        Mockito.when(mockConn.prepareStatement("SELECT * FROM product WHERE department_number = ?;")).thenReturn(mockPrepared);
        Mockito.when(mockPrepared.executeQuery()).thenReturn(mockResultSet);

        Product p = testProductDAO.searchProductsByDepartment("7").getItem(0);

        assertEquals(mockProduct, p);
    }


}
