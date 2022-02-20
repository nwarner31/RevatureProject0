package net.revature.nwarner.project0.database;

import net.revature.nwarner.project0.Driver;
import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.database.utility.DBConnection;
import net.revature.nwarner.project0.models.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LocationDAO {

    public static void addLocation(Location l) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO location (area, aisle, section_number, product_id, capacity, current_stock) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            ps.setString(parameterIndex++, l.getArea());
            ps.setString(parameterIndex++, l.getAisle());
            ps.setString(parameterIndex++, l.getSection());
            ps.setInt(parameterIndex++, l.getProductId());
            ps.setInt(parameterIndex++, l.getCapacity());
            ps.setInt(parameterIndex++, l.getCurrentStock());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                int locationId = rs.getInt(1);
                l.setId(locationId);
                Driver.logger.info(String.format("Location [%s] added", locationId));
            }

        } catch(Exception e) {
            Driver.logger.warn(e.getMessage());
        }
    }

    public static void updateProductLocation(Location l) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE location SET area = ?, aisle = ?, section_number = ? WHERE location_id = ?;");
            int parameterIndex = 1;
            ps.setString(parameterIndex++, l.getArea());
            ps.setString(parameterIndex++, l.getAisle());
            ps.setString(parameterIndex++, l.getSection());
            ps.setInt(parameterIndex++, l.getId());
            ps.executeUpdate();
            Driver.logger.info(String.format("Location [%s] moved", l.getId()));
        } catch(Exception e) {
            Driver.logger.warn(e.getMessage());
        }
    }

    public static void updateCapacity(Location l) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE location SET capacity = ? WHERE location_id = ?;");
            int parameterIndex = 1;
            ps.setInt(parameterIndex++, l.getCapacity());
            ps.setInt(parameterIndex++, l.getId());
            ps.executeUpdate();
            Driver.logger.info(String.format("Location [%s] capactiy updated", l.getId()));
        } catch(Exception e) {
            Driver.logger.warn(e.getMessage());
        }
    }

    public static void updateCurrentStock(Location l) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE location SET current_stock = ? WHERE location_id = ?;");
            int parameterIndex = 1;
            ps.setInt(parameterIndex++, l.getCurrentStock());
            ps.setInt(parameterIndex++, l.getId());
            ps.executeUpdate();
            Driver.logger.info(String.format("Location [%s] stock updated", l.getId()));
        } catch (Exception e) {
            Driver.logger.warn(e.getMessage());
        }
    }

    public static MyArrayList<Location> getLocations(int productId) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM location WHERE product_id = ?;");
            int parameterIndex = 1;
            ps.setInt(parameterIndex++, productId);
            ResultSet rs = ps.executeQuery();
            MyArrayList<Location> locations = new MyArrayList<>();
            while(rs.next()) {
                int locationId = rs.getInt("location_id");
                String area = rs.getString("area");
                String aisle = rs.getString("aisle");
                String section = rs.getString("section_number");
                int capacity = rs.getInt("capacity");
                int currentStock = rs.getInt("current_stock");
                Location location = new Location(locationId, area, aisle, section, productId, capacity, currentStock);
                locations.addItem(location);

            }
            return locations;
        } catch(Exception e) {
            Driver.logger.warn(e.getMessage());
        }
        return new MyArrayList<Location>();
    }
}
