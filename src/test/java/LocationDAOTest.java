
import net.revature.nwarner.project0.database.LocationDAO;
import net.revature.nwarner.project0.models.Location;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;



import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocationDAOTest {

    LocationDAO testLocationDAO;
    Connection mockConn;
    ResultSet mockResultSet;
    PreparedStatement mockPrepared;



    @BeforeAll
    public void setup() {
        mockConn = Mockito.mock(Connection.class);
        testLocationDAO = new LocationDAO(mockConn);

    }

    @Test
    public void addLocationTestGood() throws SQLException {
            mockPrepared = Mockito.mock(PreparedStatement.class);
            Mockito.when(mockConn.prepareStatement("INSERT INTO location (area, aisle, section_number, product_id, capacity, current_stock) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)).thenReturn(mockPrepared);

            mockResultSet = Mockito.mock(ResultSet.class);
            Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);

            Mockito.when(mockPrepared.getGeneratedKeys()).thenReturn(mockResultSet);

            Location mockLocation = new Location("L", "4", "9", 1, 10);

            testLocationDAO.addLocation(mockLocation);

            Mockito.verify(mockResultSet).next();
    }

    @Test
    public void updateProductLocationTestGood() throws SQLException {
        mockPrepared = Mockito.mock(PreparedStatement.class);
        Mockito.when(mockConn.prepareStatement("UPDATE location SET area = ?, aisle = ?, section_number = ? WHERE location_id = ?;")).thenReturn(mockPrepared);

        Location l = new Location(1,"L","4","9",1,10, 5);

        testLocationDAO.updateProductLocation(l);

        Mockito.verify(mockPrepared).executeUpdate();
    }

    @Test
    public void updateCapacityTestGood() throws SQLException {
        mockPrepared = Mockito.mock(PreparedStatement.class);
        Mockito.when(mockConn.prepareStatement("UPDATE location SET capacity = ? WHERE location_id = ?;")).thenReturn(mockPrepared);

        Location l = new Location(1,"L","4","9",1,10, 5);

        testLocationDAO.updateCapacity(l);

        Mockito.verify(mockPrepared).executeUpdate();
    }

    @Test
    public void updateCurrentStockTestGood() throws SQLException {
        mockPrepared = Mockito.mock(PreparedStatement.class);
        Mockito.when(mockConn.prepareStatement("UPDATE location SET current_stock = ? WHERE location_id = ?;")).thenReturn(mockPrepared);

        Location l = new Location(1,"L","4","9",1,10, 5);

        testLocationDAO.updateCurrentStock(l);

        Mockito.verify(mockPrepared).executeUpdate();
    }


    @Test
    public void getLocationsTestGood() throws SQLException {

        Location mockLocation = new Location("L","4","9",1,10);

        mockResultSet = Mockito.mock(ResultSet.class);
        mockPrepared = Mockito.mock(PreparedStatement.class);

        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getInt("location_id")).thenReturn(1);
        Mockito.when(mockResultSet.getString("area")).thenReturn("L");
        Mockito.when(mockResultSet.getString("aisle")).thenReturn("4");
        Mockito.when(mockResultSet.getString("section_number")).thenReturn("9");
        Mockito.when(mockResultSet.getInt("product_id")).thenReturn(1);
        Mockito.when(mockResultSet.getInt("capacity")).thenReturn(10);
        Mockito.when(mockResultSet.getInt("current_stock")).thenReturn(0);
        Mockito.when(mockConn.prepareStatement("SELECT * FROM location WHERE product_id = ?;")).thenReturn(mockPrepared);
        Mockito.when(mockPrepared.executeQuery()).thenReturn(mockResultSet);


        Location l = testLocationDAO.getLocations(1).getItem(0);
        assertEquals(mockLocation, l);
    }


}
