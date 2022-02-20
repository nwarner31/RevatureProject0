package net.revature.nwarner.project0.views;

import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.database.ProductDAO;
import net.revature.nwarner.project0.database.LocationDAO;
import net.revature.nwarner.project0.models.Location;
import net.revature.nwarner.project0.models.Product;
import net.revature.nwarner.project0.utility.InputGatherer;

public class LayoutManagerView implements RoleView {

    @Override
    public void run() {
        System.out.println("Welcome Layout Manager.");

        while(true) {
            String upc = InputGatherer.getStringInput("Enter the product UPC:");

            if(InputGatherer.isLogout(upc)) return;
            Product product = ProductDAO.searchProductsByUpc(upc);
            if (product == null) {
                System.out.println("No product found");
                continue;
            }
            System.out.println(product);

            String toContinue = InputGatherer.getStringInput("Do you want to work with this product?");
            if(InputGatherer.isNo(toContinue)) continue;
            MyArrayList<Location> locations = LocationDAO.getLocations(product.getProductId());
            if(locations.getSize() > 0) {
                for (int index = 0; index < locations.getSize(); index++) {
                    System.out.println(String.format("[%s] %s", index, locations.getItem(index)));
                }

                while (true) {
                    String action = InputGatherer.getStringInput("Do you want to create, move, or change a location capacity?");

                    switch (action) {
                        case "create":
                            create(product.getProductId());
                            break;
                        case "move":
                            int index = InputGatherer.getIntInput(locations.getSize() - 1, "Select which location you want to move:");
                            move(locations.getItem(index));
                            break;
                        case "cc":
                            index = InputGatherer.getIntInput(locations.getSize() - 1, "Select which location you want to change capacity:");
                            changeCapacity(locations.getItem(index));
                            break;
                        case "exit":
                            break;
                        default:
                            System.out.println("Not a valid input");
                            continue;
                    }
                    break;
                }

            } else {
                System.out.println("Product currently has no locations.");
                String createNew = InputGatherer.getStringInput("Do you want to create one?");
                if(InputGatherer.isYes(createNew)) create(product.getProductId());
                else if(InputGatherer.isLogout(createNew)) return;
            }
        }
    }

    private void create(int productId) {
        String area = InputGatherer.getStringInput("Enter the area:");
        String aisle = InputGatherer.getStringInput("Enter the aisle:");
        String section = InputGatherer.getStringInput("Enter the section:");
        int capacity = InputGatherer.getIntInput("Enter the shelf capacity");
        Location location = new Location(area, aisle, section, productId, capacity);
        LocationDAO.addLocation(location);
    }

    private void move(Location l) {
        String newArea = InputGatherer.getStringInput(String.format("Current area: %s", l.getArea()), l.getArea());
        l.setArea(newArea);

        String newAisle = InputGatherer.getStringInput(String.format("Current aisle: %s", l.getAisle()), l.getAisle());
        l.setAisle(newAisle);

        String newSection = InputGatherer.getStringInput(String.format("Current section: %s", l.getSection()), l.getSection());
        l.setSection(newSection);

        LocationDAO.updateProductLocation(l);
    }

    private void changeCapacity(Location l) {
        int newCapacity = InputGatherer.getIntInput(String.format("Current capacity: %s", l.getCapacity()), l.getCapacity());
        if(newCapacity != l.getCapacity()) {
            l.setCapacity(newCapacity);
            LocationDAO.updateCapacity(l);
        }
    }
}
