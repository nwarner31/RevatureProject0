package net.revature.nwarner.project0.views;

import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.database.ProductDAO;
import net.revature.nwarner.project0.database.LocationDAO;
import net.revature.nwarner.project0.models.Location;
import net.revature.nwarner.project0.models.Product;
import net.revature.nwarner.project0.utility.InputGatherer;

public class FloorView implements RoleView{

    @Override
    public void run() {
        System.out.println("Welcome Floor Employee");

        while (true) {
            String action = InputGatherer.getStringInput("Do you want to search by name or by UPC?");

            switch (action) {
                case "name":
                    searchName();
                    break;
                case "upc":
                    searchUpc();
                    break;
                case "logout":
                    return;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

    private void searchName() {
        String name = InputGatherer.getStringInput("Enter the name to search for: ");
        MyArrayList<Product> products = ProductDAO.searchProductsByName(name);
        if(products.getSize() == 0) {
            System.out.println("No products found");
            return;
        }
        for (int index = 0; index < products.getSize(); index++) {
            System.out.println(String.format("[%s] %s", index, products.getItem(index)));
        }
        int index = InputGatherer.getIntInput(products.getSize() - 1 ,"Select a product to view locations");
        MyArrayList<Location> locations = LocationDAO.getLocations(products.getItem(index).getProductId());
        if(locations.getSize() == 0) {
            System.out.println(products.getItem(index));
            System.out.println("This product has no current locations");
        } else {
            System.out.println(products.getItem(index));
            for(int x = 0; x < locations.getSize(); x++) {
                System.out.println(locations.getItem(x));
            }
        }
    }

    private void searchUpc() {
        String upc = InputGatherer.getStringInput("Enter the UPC to search for:");

        Product product = ProductDAO.searchProductsByUpc(upc);
        if(product != null) {
            System.out.println(product);
            MyArrayList<Location> locations = LocationDAO.getLocations(product.getProductId());
            if(locations.getSize() == 0) {
                System.out.println("No product locations found");
            } else {
                for(int index = 0; index < locations.getSize(); index++) {
                    System.out.println(locations.getItem(index));
                }
            }
        } else {
            System.out.println("Product not found");
        }
    }
}
