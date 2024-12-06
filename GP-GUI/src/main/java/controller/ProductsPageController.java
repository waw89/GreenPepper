/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.BusinessProduct;
import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.PRODUCT_SIZE;
import com.mycompany.gp.domain.Product;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class ProductsPageController implements Initializable {

    @FXML
    private Button btnFood;
    @FXML
    private Button btnDrink;
    @FXML
    private Button btnExtra;
    @FXML
    private TextField txtSearchProduct;
    @FXML
    private Button btnClean;
    @FXML
    private Button addButton;
    @FXML
    private VBox productContainer;

    ObservableList<IndividualProduct> foodList;

    ObservableList<IndividualProduct> drinkList;

    ObservableList<IndividualProduct> extrasList;

    FilteredList<IndividualProduct> filter;

    BusinessProduct prodBusiness = new BusinessProduct();

    AdminMainPageController adminMainController;

    /**
     * Getters & Setters
     */
    public void setAdminMainController(AdminMainPageController adminMainController) {
        this.adminMainController = adminMainController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        foodList = FXCollections.observableArrayList();
        List<IndividualProduct> products = prodBusiness.getAllFoods();
        foodList.addAll(products);
        filter = new FilteredList(foodList, p -> true);
        for (Product product : foodList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminProductCard.fxml"));
                AnchorPane productCard = loader.load();
                AdminProductCardController cardController = loader.getController();
                cardController.setProduct(product);
                cardController.setProductsPageController(this);
                cardController.setTxtProductName(product.getName());
                cardController.setTxtSize(setSizeText(product.getPRODUCT_SIZE()));
                cardController.setTxtPrice(String.valueOf(product.getPrice()));
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(3);

            } catch (IOException ex) {
                Logger.getLogger(ProductsPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void showFoods(MouseEvent event) {
        setSelectedButtonStyle(btnFood);
        cleanProductsList();
        foodList = FXCollections.observableArrayList();
        List<IndividualProduct> products = prodBusiness.getAllFoods();
        foodList.addAll(products);
        filter = new FilteredList(foodList, p -> true);
        for (Product product : foodList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminProductCard.fxml"));
                AnchorPane productCard = loader.load();
                AdminProductCardController cardController = loader.getController();
                cardController.setProduct(product);
                cardController.setProductsPageController(this);
                cardController.setTxtProductName(product.getName());
                cardController.setTxtSize(setSizeText(product.getPRODUCT_SIZE()));
                cardController.setTxtPrice(String.valueOf(product.getPrice()));
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(3);

            } catch (IOException ex) {
                Logger.getLogger(ProductsPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void showDrinks(MouseEvent event) {
        setSelectedButtonStyle(btnDrink);
        cleanProductsList();
        drinkList = FXCollections.observableArrayList();
        List<IndividualProduct> products = prodBusiness.getAllDrinks();
        drinkList.addAll(products);
        filter = new FilteredList(drinkList, p -> true);
        for (Product product : drinkList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminProductCard.fxml"));
                AnchorPane productCard = loader.load();
                AdminProductCardController cardController = loader.getController();
                cardController.setProduct(product);
                cardController.setProductsPageController(this);
                cardController.setTxtProductName(product.getName());
                cardController.setTxtSize(setSizeText(product.getPRODUCT_SIZE()));
                cardController.setTxtPrice(String.valueOf(product.getPrice()));
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(3);

            } catch (IOException ex) {
                Logger.getLogger(ProductsPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void showExtras(MouseEvent event) {
        setSelectedButtonStyle(btnExtra);
        cleanProductsList();
        extrasList = FXCollections.observableArrayList();
        List<IndividualProduct> products = prodBusiness.getAllExtras();
        extrasList.addAll(products);
        filter = new FilteredList(extrasList, p -> true);
        for (Product product : extrasList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminProductCard.fxml"));
                AnchorPane productCard = loader.load();
                AdminProductCardController cardController = loader.getController();
                cardController.setProduct(product);
                cardController.setProductsPageController(this);
                cardController.setTxtProductName(product.getName());
                cardController.setTxtSize(setSizeText(product.getPRODUCT_SIZE()));
                cardController.setTxtPrice(String.valueOf(product.getPrice()));
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(3);

            } catch (IOException ex) {
                Logger.getLogger(ProductsPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void search(KeyEvent event) {
        txtSearchProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Muestra todos si el campo de búsqueda está vacío
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return product.getName().toLowerCase().contains(lowerCaseFilter); // Filtra por nombre
            });

            // Actualizar el contenedor con los productos filtrados
            updateProductContainer(filter);
        });
    }

    @FXML
    private void cleanSearchBar(MouseEvent event) {
        this.txtSearchProduct.clear();
    }

    @FXML
    private void addProduct(MouseEvent event) {
        this.adminMainController.loadPage("AddProductPage");
    }

    /**
     * Método para actualizar el contenedor de productos basado en la lista
     * filtrada
     */
    public void updateProductContainer(FilteredList<IndividualProduct> filteredList) {
        productContainer.getChildren().clear(); // Limpia los productos actualesS

        for (IndividualProduct product : filteredList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminProductCard.fxml"));
                AnchorPane productCard = loader.load();
                AdminProductCardController cardController = loader.getController();

                cardController.setTxtProductName(product.getName());
                cardController.setTxtSize(setSizeText(product.getPRODUCT_SIZE()));
                cardController.setTxtPrice(String.valueOf(product.getPrice()));

                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(10);
            } catch (IOException ex) {
                Logger.getLogger(ProductsPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String setSizeText(PRODUCT_SIZE size) {
        switch (size) {
            case SMALL:
                return "CH";
            case MEDIUM:
                return "M";
            case LARGE:
                return "G";
            default:
                return "Indefinido";
        }

    }

    private void setSelectedButtonStyle(Button selectedButton) {

        List<Button> buttons = List.of(btnFood, btnDrink, btnExtra);

        for (Button button : buttons) {
            if (button.equals(selectedButton)) {

                button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 50; -fx-border-radius: 50;");
            } else {

                button.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black;");
            }
        }
    }

    private void cleanProductsList() {
        productContainer.getChildren().clear();
    }
}
