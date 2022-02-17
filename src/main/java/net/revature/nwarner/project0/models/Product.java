package net.revature.nwarner.project0.models;

public class Product {
    private int productId;
    private String upc;
    private String productName;
    private String departmentNumber;

    public Product (String upc, String productName, String departmentNumber) {
        this.upc = upc;
        this.productName = productName;
        this.departmentNumber = departmentNumber;
    }

    public Product (int productId, String upc, String productName, String departmentNumber) {
        this(upc, productName, departmentNumber);
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDepartmentNumber(String departmentNumber) {
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
