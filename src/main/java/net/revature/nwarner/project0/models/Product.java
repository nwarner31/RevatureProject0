package net.revature.nwarner.project0.models;

public class Product {
    private String upc;
    private String productName;
    private String departmentNumber;

    public Product (String upc, String productName, String departmentNumber) {
        this.upc = upc;
        this.productName = productName;
        this.departmentNumber = departmentNumber;
    }

    public String getUpc() {
        return upc;
    }

    public String getProductName() {
        return productName;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s", upc, productName, departmentNumber);
    }
}
