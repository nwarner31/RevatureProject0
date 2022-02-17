package net.revature.nwarner.project0.views;

import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.database.ProductDAO;
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
            System.out.println("Would you like to create, edit, or search products?");
            String action = input.nextLine();
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
        if (!upc.equals("") && !name.equals("") && !deptNumber.equals("")) {
            Product p = new Product(upc, name, deptNumber);
            ProductDAO.addProduct(p);
        }

    }

    private void edit() {
        searchProducts();
        while(true) {
            System.out.println("Enter the row number to edit that item:");
            String indexString = input.nextLine();
            int index = Integer.parseInt(indexString);
            System.out.println(products.getItem(index));
            System.out.println("Do you want to edit this item?");
            String answer = input.nextLine();

            System.out.println(String.format("Answer: %s", answer));
            if(answer.equals("y") || answer.equals("yes")) {
                editProduct(products.getItem(index));

                System.out.println("Do you want to edit another item in the list?");
                String cont = input.nextLine();
                if(cont.equals("n") || cont.equals("no")) return;
            }

        }
    }

    private void editProduct(Product p) {
        System.out.println(String.format("Current UPC: %s", p.getUpc()));
        String newUpc = input.nextLine();
        if(!newUpc.equals("")) p.setUpc(newUpc);

        System.out.println(String.format("Current product name: %s", p.getProductName()));
        String newName = input.nextLine();
        if(!newName.equals("")) p.setProductName(newName);

        System.out.println(String.format("Current department number: %s", p.getDepartmentNumber()));
        String newDept = input.nextLine();
        if(!newDept.equals("")) p.setDepartmentNumber(newDept);

        ProductDAO.updateProduct(p);
    }

    private void searchProducts() {
        System.out.println("Do you want to search by upc, name, or department?");
        String answer = input.nextLine();

        switch (answer) {
            case "upc":
                System.out.println("Enter the upc you want to search for:");
                String upc = input.nextLine();
                if (!upc.equals("")) {
                    MyArrayList<Product> products = new MyArrayList<>();
                    products.addItem(ProductDAO.searchProductsByUpc(upc));
                    this.products = products;
                }
                break;
            case "name":
                System.out.println("Enter the product name you want to search for:");
                String productName = input.nextLine();
                if(!productName.equals("")) {
                    MyArrayList<Product> products = ProductDAO.searchProductsByName(productName);
                    this.products = products;
                }
                break;
            case "dept":
                System.out.println("Enter the department number you want to search for:");
                String deptNum = input.nextLine();
                if(!deptNum.equals("")) {
                    MyArrayList<Product> products = ProductDAO.searchProductsByDepartment(deptNum);
                    this.products = products;
                }
                break;
        }
        view();
    }

    private void view() {
        for(int index = 0; index < products.getSize(); index++) {
            System.out.println(String.format("[%s] %s", index, products.getItem(index)));
        }
    }
}
