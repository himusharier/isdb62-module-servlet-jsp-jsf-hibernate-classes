package com.himu;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class ProductBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Product product = new Product(); // Holds the current product data
    private ProductDAO productDAO = new ProductDAO(); // DAO for database operations
    private List<Product> productList; // List of all products
    private boolean editMode = false; // Tracks whether the form is in edit mode

    // Saves or updates a product
    public String saveProduct() {
        try {
            if (editMode) {
                productDAO.update(product); // Update existing product
            } else {
                productDAO.create(product); // Create new product
            }
            refreshList(); // Refresh the product list
            clearForm(); // Clear the form
            return "product?faces-redirect=true"; // Navigate to the product list page
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Stay on the same page if an error occurs
        }
    }

    // Enables edit mode and loads the selected product into the form
    public void editProduct(Product product) {
        this.product = product; // Set the selected product
        editMode = true; // Enable edit mode
    }

    // Deletes a product by ID
    public void deleteProduct(int id) {
        try {
            productDAO.delete(id); // Delete the product
            refreshList(); // Refresh the product list
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Clears the form and resets edit mode
    public void clearForm() {
        product = new Product(); // Reset the product object
        editMode = false; // Disable edit mode
    }

    // Refreshes the product list from the database
    private void refreshList() {
        try {
            productList = productDAO.readAll(); // Fetch all products
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        if (productList == null) {
            refreshList(); // Lazy load the product list
        }
        return productList;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
}