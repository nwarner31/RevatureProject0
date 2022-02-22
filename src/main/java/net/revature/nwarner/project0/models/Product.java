package net.revature.nwarner.project0.models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return upc.equals(product.upc) && productName.equals(product.productName) && departmentNumber.equals(product.departmentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upc, productName, departmentNumber);
    }
}
