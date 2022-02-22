package net.revature.nwarner.project0.views;

import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.database.LocationDAO;
import net.revature.nwarner.project0.database.ProductDAO;
import net.revature.nwarner.project0.models.Location;
import net.revature.nwarner.project0.models.Product;
import net.revature.nwarner.project0.utility.InputGatherer;

public class StockerView implements RoleView{

    LocationDAO locDAO;
    ProductDAO prodDAO;

    public StockerView() {
        locDAO = new LocationDAO();
        prodDAO = new ProductDAO();
    }

    @Override
    public void run() {

        System.out.println("Welcome Stocker");
        while (true) {
            String upc = InputGatherer.getStringInput("Enter the UPC to search for.");
            if (InputGatherer.isLogout(upc)) return;

            Product product = prodDAO.searchProductsByUpc(upc);
            if(product == null) {
                System.out.println("Product not found");
                continue;
            }
            System.out.println(product);

            String toContinue = InputGatherer.getStringInput("Do you want to use this product?");
            if(InputGatherer.isNo(toContinue)) continue;

            MyArrayList<Location> locations = locDAO.getLocations(product.getProductId());
            if(locations.getSize() == 0) System.out.println("No product locations found");
            else {
                for(int index = 0; index < locations.getSize(); index++) {
                    System.out.println(String.format("[%s] %s", index, locations.getItem(index)));
                }

                int locationNumber = InputGatherer.getIntInput(locations.getSize() - 1, "Select a location");
                System.out.println(locations.getItem(locationNumber));
                int newStock = InputGatherer.getIntInput(String.format("Current stock: %s\nEnter new stock amount:", locations.getItem(locationNumber).getCurrentStock()));
                locations.getItem(locationNumber).setCurrentStock(newStock);
                locDAO.updateCurrentStock(locations.getItem(locationNumber));

            }
        }
    }

}
