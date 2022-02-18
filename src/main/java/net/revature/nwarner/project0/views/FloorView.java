package net.revature.nwarner.project0.views;

import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.database.ProductDAO;
import net.revature.nwarner.project0.database.LocationDAO;
import net.revature.nwarner.project0.models.Location;
import net.revature.nwarner.project0.models.Product;

import java.util.Scanner;

public class FloorView implements RoleView{

    private final Scanner input;

    public FloorView() { this.input = new Scanner(System.in); }

    @Override
    public void run() {
        System.out.println("Welcome Floor Employee");

        while (true) {
            System.out.println("Do you want to search by name or by UPC?");
            String answer = input.nextLine();
            switch (answer) {
                case "name":
                    searchName();
                    break;
                case "upc":
                    searchUpc();
                    break;
                case "logout":
                    return;
            }
        }
    }

    private void searchName() {
        System.out.println("Enter the name to search for: ");
        String name = input.nextLine();
        if(!name.equals("")) {
            MyArrayList<Product> products = ProductDAO.searchProductsByName(name);
            for (int index = 0; index < products.getSize(); index++) {
                System.out.println(String.format("[%s] %s", index, products.getItem(index)));
            }
            System.out.println("Select a product to view locations");
            String indexString = input.nextLine();
            int index = Integer.parseInt(indexString);
            MyArrayList<Location> locations = LocationDAO.getLocations(products.getItem(index).getProductId());
            if(locations.getSize() == 0) {
                System.out.println("This product has no current locations");
            } else {
                for(int x = 0; x < locations.getSize(); x++) {
                    System.out.println(locations.getItem(x));
                }
            }
        }
    }

    private void searchUpc() {
        System.out.println("Enter the UPC to search for:");
        String upc = input.nextLine();
        if(!upc.equals("")) {
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
            }
        }
    }

}
