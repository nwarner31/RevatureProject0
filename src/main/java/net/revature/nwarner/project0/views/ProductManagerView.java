package net.revature.nwarner.project0.views;

import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.database.ProductDAO;
import net.revature.nwarner.project0.models.Product;
import net.revature.nwarner.project0.utility.InputGatherer;

public class ProductManagerView implements RoleView {

    private MyArrayList<Product> products;

    public ProductManagerView() {
        products = new MyArrayList<>();
    }

    @Override
    public void run() {
        System.out.println("Welcome Product Manager.");

        while(true) {
            String action = InputGatherer.getStringInput("Would you like to create, edit, or search products?");
            switch (action) {
                case "create":
                    create();
                    break;
                case "search":
                    searchProducts();
                    break;
                case "edit":
                    edit();
                    break;
                case "logout":
                    return;
                default:
                    System.out.println("Not a valid input");
                    break;
            }
        }
    }

    private void create() {
        String upc = InputGatherer.getStringInput("Please enter product UPC:");
        String name = InputGatherer.getStringInput("Please enter the product name:");
        String deptNumber = InputGatherer.getStringInput("Please enter the product department number:");

        Product p = new Product(upc, name, deptNumber);
        if(!ProductDAO.addProduct(p)) System.out.println("Product not added");
    }

    private void edit() {
        searchProducts();
        if(products.getSize()==0) {
            return;
        }
        while(true) {
            int index = InputGatherer.getIntInput(products.getSize() - 1 ,"Enter the row number to edit that item:");
            System.out.println(products.getItem(index));
            String answer = InputGatherer.getStringInput("Do you want to edit this item?");

            if(InputGatherer.isYes(answer)) {
                editProduct(products.getItem(index));
                String editAnother = InputGatherer.getStringInput("Do you want to edit another item in the list?");
                if(InputGatherer.isNo(editAnother)) return;
            } else {
                String selectAgain = InputGatherer.getStringInput("Do you want to select another item?");
                if(InputGatherer.isNo(selectAgain)) return;
            }

        }
    }

    private void editProduct(Product p) {
        String newUpc = InputGatherer.getStringInput(String.format("Current UPC: %s", p.getUpc()), p.getUpc());
        p.setUpc(newUpc);

        String newName = InputGatherer.getStringInput(String.format("Current product name: %s", p.getProductName()), p.getProductName());
        p.setProductName(newName);

        String newDept = InputGatherer.getStringInput(String.format("Current department number: %s", p.getDepartmentNumber()), p.getDepartmentNumber());
        p.setDepartmentNumber(newDept);

        if(!ProductDAO.updateProduct(p)) System.out.println("Product not updated");
    }

    private void searchProducts() {
        while (true) {
            String answer = InputGatherer.getStringInput("Do you want to search by upc, name, or department?");
            switch (answer) {
                case "upc":
                    String upc = InputGatherer.getStringInput("Enter the upc you want to search for:");
                    Product product = ProductDAO.searchProductsByUpc(upc);
                    MyArrayList<Product> products = new MyArrayList<>();
                    if(product != null) products.addItem(product);

                    this.products = products;
                    break;
                case "name":
                    String productName = InputGatherer.getStringInput("Enter the product name you want to search for:");
                    products = ProductDAO.searchProductsByName(productName);
                    this.products = products;
                    break;
                case "dept":
                    String deptNum = InputGatherer.getStringInput("Enter the department number you want to search for:");
                    products = ProductDAO.searchProductsByDepartment(deptNum);
                    this.products = products;
                    break;
                default:
                    System.out.println("Not a valid entry");
                    continue;
            }
            break;
        }
        if (products.getSize() == 0) System.out.println("No products found");
        else if (products.getSize() == 1) System.out.println(products.getItem(0));
        else view();
    }

    private void view() {
        for(int index = 0; index < products.getSize(); index++) {
            System.out.println(String.format("[%s] %s", index, products.getItem(index)));
        }
    }
}
