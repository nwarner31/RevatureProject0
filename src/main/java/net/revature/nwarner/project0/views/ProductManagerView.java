package net.revature.nwarner.project0.views;

import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.models.Product;

import java.util.Scanner;


public class ProductManagerView implements RoleView {

    private MyArrayList<Product> products;
    private final Scanner input;
    public ProductManagerView() {
        input = new Scanner(System.in);
        products = new MyArrayList<>();
    }

    @Override
    public void run() {
        System.out.println("Welcome Product Manager.");

        while(true) {
            System.out.println("Would you like to create, edit, or view products?");
            String action = input.nextLine();
            switch (action) {
                case "create":
                    create();
                    break;
                case "view":
                    view();
                    break;
                case "edit":
                    edit();
                    break;
                case "logout":
                    return;

            }
        }
    }

    private void create() {
        System.out.println("Please enter product UPC:");
        String upc = input.nextLine();
        System.out.println("Please enter the product name:");
        String name = input.nextLine();
        System.out.println("Please enter the product department number:");
        String deptNumber = input.nextLine();
        products.addItem(new Product(upc, name, deptNumber));
    }

    private void edit() {
        view();
        System.out.println("Enter the line number of the product you would like to edit:");
        int index = Integer.parseInt(input.nextLine()) - 1;
        Product product = products.getItem(index);
        System.out.printf("Product UPC: %s%n", product.getUpc());
        System.out.printf("Current Name: %s%n", product.getProductName());
        System.out.print("Enter the new name: ");
        String newName = input.next();
        newName = newName.equals("") ? product.getProductName() : newName;
        System.out.print("Enter the new department: ");
        String newDept = input.next();
        newDept = newDept.equals("") ? product.getDepartmentNumber() : newDept;

        products.updateItem(new Product(product.getUpc(), newName, newDept), index);
    }

    private void view() {
        for(int index = 0; index < products.getSize(); index++) {
            System.out.println(products.getItem(index));
        }
    }
}
