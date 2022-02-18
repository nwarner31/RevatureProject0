package net.revature.nwarner.project0.views;

import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.database.LocationDAO;
import net.revature.nwarner.project0.database.ProductDAO;
import net.revature.nwarner.project0.models.Location;
import net.revature.nwarner.project0.models.Product;
import net.revature.nwarner.project0.utility.InputGatherer;

import java.util.Scanner;

public class StockerView implements RoleView{

    private final Scanner input;

    public StockerView() {
        input = new Scanner(System.in);
    }

    @Override
    public void run() {

        System.out.println("Welcome Stocker");
        while (true) {
            String upc = InputGatherer.getStringInputNotEmpty("Enter the UPC to search for.");
            if (InputGatherer.isLogout(upc)) return;

            Product product = ProductDAO.searchProductsByUpc(upc);
            if(product == null) {
                System.out.println("Product not found");
                continue;
            }
            System.out.println(product);

            String toContinue = InputGatherer.getStringInputNotEmpty("Do you want to use this product?");
            if(InputGatherer.isNo(toContinue)) continue;


            MyArrayList<Location> locations = LocationDAO.getLocations(product.getProductId());
            if(locations.getSize() == 0) System.out.println("No product locations found");
            else {
                for(int index = 0; index < locations.getSize(); index++) {
                    System.out.println(String.format("[%s] %s", index, locations.getItem(index)));
                }

                int locationNumber = InputGatherer.getIntInput("Select a location");
                System.out.println(locations.getItem(locationNumber));
                int newStock = InputGatherer.getIntInput(String.format("Current stock: %s", locations.getItem(locationNumber).getCurrentStock()));
                locations.getItem(locationNumber).setCurrentStock(newStock);
                LocationDAO.updateCurrentStock(locations.getItem(locationNumber));


            }
        }
    }

}
