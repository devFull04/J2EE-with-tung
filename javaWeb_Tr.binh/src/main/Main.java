package main;

import dao.ProductDAO;
import model.Product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        // Thêm sản phẩm mới
        Product p1 = new Product("Áo Thun Nam", 150000, 50, "Áo");
        productDAO.addProduct(p1);
        System.out.println("Đã thêm sản phẩm: " + p1.getName());

        Product p2 = new Product("Quần Jean Nữ", 300000, 30, "Quần");
        productDAO.addProduct(p2);
        System.out.println("Đã thêm sản phẩm: " + p2.getName());

        // Hiển thị tất cả sản phẩm
        List<Product> productList = productDAO.getAllProducts();
        System.out.println("\nDanh sách sản phẩm:");
        for (Product p : productList) {
            System.out.println(p);
        }

        // Cập nhật sản phẩm
        if (!productList.isEmpty()) {
            Product updateProduct = productList.get(0);
            updateProduct.setPrice(170000);
            updateProduct.setQuantity(60);
            productDAO.updateProduct(updateProduct);
            System.out.println("\nĐã cập nhật sản phẩm id=" + updateProduct.getId());
        }

        // Tìm sản phẩm theo ID
        Product searchedProduct = productDAO.getProductById(1);
        if (searchedProduct != null) {
            System.out.println("\nSản phẩm tìm thấy: " + searchedProduct);
        }

        // Xóa sản phẩm
        if (!productList.isEmpty()) {
            int deleteId = productList.get(1).getId();
            productDAO.deleteProduct(deleteId);
            System.out.println("\nĐã xóa sản phẩm id=" + deleteId);
        }

        // Hiển thị lại sản phẩm sau khi xóa
        System.out.println("\nDanh sách sản phẩm sau khi xóa:");
        for (Product p : productDAO.getAllProducts()) {
            System.out.println(p);
        }
    }
}