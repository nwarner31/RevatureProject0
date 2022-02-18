package net.revature.nwarner.project0.views;

import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.database.ProductDAO;
import net.revature.nwarner.project0.database.LocationDAO;
import net.revature.nwarner.project0.models.Location;
import net.revature.nwarner.project0.models.Product;

import java.util.Scanner;

public class LayoutManagerView implements RoleView {

    private final Scanner input;

    public LayoutManagerView() {
        input = new Scanner(System.in);
    }





    @Override
    public void run() {
        System.out.println("Welcome Layout Manager.");

        while(true) {
            System.out.println("Enter the product UPC:");
            String upc = input.nextLine();
            if(upc.equals("logout")) return;
            Product product = ProductDAO.searchProductsByUpc(upc);
            System.out.println(product);
            System.out.println("Do you want to work with this product?");
            String answer = input.nextLine();
            if(answer.equals("n") || answer.equals("no")) continue;
            else if(answer.equals("logout")) return;
            MyArrayList<Location> locations = LocationDAO.getLocations(product.getProductId());
            if(locations.getSize() > 0) {
                for (int index = 0; index < locations.getSize(); index++) {
                    System.out.println(String.format("[%s] %s", index, locations.getItem(index)));
                }

                System.out.println("Do you want to create, move, or change a location capacity?");
                answer = input.nextLine();
                switch (answer) {
                    case "create":
                        create(product.getProductId());
                        break;
                    case "move":
                        System.out.println("Select which location you want to move:");
                        String indexString = input.nextLine();
                        int index = Integer.parseInt(indexString);
                        move(locations.getItem(index));
                        break;
                    case "cc":
                        System.out.println("Select which location you wat to change capacity:");
                        indexString = input.nextLine();
                        index = Integer.parseInt(indexString);
                        changeCapacity(locations.getItem(index));
                }
            } else {
                System.out.println("Product currently has no locations.");
                System.out.println("Do you want to create one?");
                answer = input.nextLine();
                if(answer.equals("y") || answer.equals("yes")) create(product.getProductId());
                else if(answer.equals("logout")) return;
            }

        }
    }

    private void create(int productId) {
        System.out.println("Enter the area:");
        String area = input.nextLine();
        System.out.println("Enter the aisle:");
        String aisle = input.nextLine();
        System.out.println("Enter the section:");
        String section = input.nextLine();
        System.out.println("Enter the shelf capacity");
        String capacityString = input.nextLine();
        int capacity = Integer.parseInt(capacityString);
        Location location = new Location(area, aisle, section, productId, capacity);
        LocationDAO.addLocation(location);
    }

    private void move(Location l) {
        System.out.println(String.format("Current area: %s", l.getArea()));
        String newArea = input.nextLine();
        if(!newArea.equals("")) l.setArea(newArea);

        System.out.println(String.format("Current aisle: %s", l.getAisle()));
        String newAisle = input.nextLine();
        if(!newAisle.equals("")) l.setAisle(newAisle);

        System.out.println(String.format("Current section: %s", l.getSection()));
        String newSection = input.nextLine();
        if(!newSection.equals("")) l.setSection(newSection);

        LocationDAO.updateProductLocation(l);

    }

    private void changeCapacity(Location l) {
        System.out.println(String.format("Current capacity: %s", l.getCapacity()));
        String newCapacity = input.nextLine();
        if(!newCapacity.equals("")) {
            int capacity = Integer.parseInt(newCapacity);
            l.setCapacity(capacity);
            LocationDAO.updateCapacity(l);
        }

    }

}
