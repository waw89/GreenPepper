/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.BusinessProduct;
import business.OrderBusiness;
import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.Product;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
public class EditOrderProductsController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private VBox summaryContainer;
    @FXML
    private Label lblSubtotal;
    @FXML
    private Label lblDiscount;
    @FXML
    private Label lblTotal;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
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
    private ScrollPane scrollPane;
    @FXML
    private VBox productContainer;

    /*
    * Objects that needs the controller to do all the stuff
     */
    Order order;

    MainPageController mainPageController;

    List<ProductOrder> poList = new ArrayList<>();

    List<ProductAddedController> productAddedNodes = new ArrayList<>();

    ProductAddedController productAddedController;

    OrderBusiness oBusiness = new OrderBusiness();

    List<ProductOrder> productsAddedToSummary = new ArrayList<>();

    int productRepeatedAmount = 0;

    BusinessProduct prodBusiness = new BusinessProduct();

    ObservableList<IndividualProduct> foodList;

    ObservableList<IndividualProduct> drinkList;

    ObservableList<IndividualProduct> extrasList;

    FilteredList<IndividualProduct> filter;

    ProductItemController productItemController;

    /*
    * Getters & Setters
     */
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        if (this.order != null) {
            loadProductSummary();
        }

    }

    public MainPageController getMainPageController() {
        return mainPageController;
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    public ProductAddedController getProductAddedController() {
        return productAddedController;
    }

    public void setProductAddedController(ProductAddedController productAddedController) {
        this.productAddedController = productAddedController;
    }

    public ProductItemController getProductItemController() {
        return productItemController;
    }

    public void setProductItemController(ProductItemController productItemController) {
        this.productItemController = productItemController;
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductCard.fxml"));
                AnchorPane productCard = loader.load();
                ProductCardController cardController = loader.getController();
                cardController.setEopController(this);
                cardController.setTxtProductName(product.getName());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(1);

            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void cleanFields(MouseEvent event) {
    }

    @FXML
    private void save(MouseEvent event) {
    }

    private void loadProductSummary() {
        poList.addAll(this.order.getProducts());

        // Ordenar la lista alfabéticamente por el nombre del producto
        Collections.sort(poList, new Comparator<ProductOrder>() {
            @Override
            public int compare(ProductOrder po1, ProductOrder po2) {
                return po1.getProduct().getName().compareToIgnoreCase(po2.getProduct().getName());
            }
        });

        for (ProductOrder po : poList) {
            showExistingProductInSummary(po);
        }

    }

    private void showExistingProductInSummary(ProductOrder po) {
        boolean productExists = false;

        Iterator<ProductOrder> iterator = productsAddedToSummary.iterator();
        while (iterator.hasNext()) {
            ProductOrder poIterator = iterator.next();
            if (poIterator.getProduct().getName().equals(po.getProduct().getName())) {
                productExists = true;
            }
        }

        if (!productExists) {
            productRepeatedAmount = 0;
            int productAmount = 1;
            int j = 0;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductAdded.fxml"));
                AnchorPane productCell = loader.load();
                ProductAddedController cellController = loader.getController();
                cellController.setEopController(this);
                cellController.setProductOrder(po);
                cellController.setTxtSummaryProductName(po.getProduct().getName());
                cellController.setTxtProductSummaryPrice(String.valueOf(po.getPrice()));
                cellController.setTxtAmount(String.valueOf(productAmount));
                productCell.setUserData(cellController);
                summaryContainer.getChildren().add(productCell);
                productsAddedToSummary.add(po);
                productAddedNodes.add(cellController);
                cellController.addExistentProductToListContainer(po.getPrice(), "#" + (j + 1), po.getDetails(), po.getPRODUCT_SIZE());
            } catch (IOException ex) {
                Logger.getLogger(EditOrderProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            for (Node node : summaryContainer.getChildren()) {

                ProductAddedController cellController = (ProductAddedController) node.getUserData();
                if (cellController.getProductOrder().getProduct().getName().equals(po.getProduct().getName())) {
                    productRepeatedAmount++;
                    int j = productRepeatedAmount;
                    cellController.setTxtAmount(String.valueOf(productRepeatedAmount + 1));
                    cellController.setTxtProductSummaryPrice(String.valueOf(po.getPrice() * productRepeatedAmount));

                    cellController.addExistentProductToListContainer(po.getPrice(), "#" + (j + 1), po.getDetails(), po.getPRODUCT_SIZE());

                }

            }
            lblSubtotal.setText("$" + oBusiness.calculateCost(order));
            lblTotal.setText(lblSubtotal.getText());
            order.setPrice(oBusiness.calculateCost(order));
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductCard.fxml"));
                AnchorPane productCard = loader.load();
                ProductCardController cardController = loader.getController();

                cardController.setEopController(this);

                cardController.setTxtProductName(product.getName());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(1);

            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductCard.fxml"));
                AnchorPane productCard = loader.load();
                ProductCardController cardController = loader.getController();

                cardController.setEopController(this);

                cardController.setTxtProductName(product.getName());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(1);

            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductCard.fxml"));
                AnchorPane productCard = loader.load();
                ProductCardController cardController = loader.getController();

                cardController.setEopController(this);

                cardController.setTxtProductName(product.getName());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(1);

            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void cleanProductsList() {
        productContainer.getChildren().clear();
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

    /**
     * Método para actualizar el contenedor de productos basado en la lista
     * filtrada
     */
    private void updateProductContainer(FilteredList<IndividualProduct> filteredList) {
        productContainer.getChildren().clear(); // Limpia los productos actuales

        for (IndividualProduct product : filteredList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductCard.fxml"));
                AnchorPane productCard = loader.load();
                ProductCardController cardController = loader.getController();

                // Configurar los detalles del producto
                cardController.setEopController(this);
                cardController.setTxtProductName(product.getName());

                productContainer.getChildren().add(productCard); // Añadir el producto filtrado al contenedor
                productContainer.setSpacing(10);
            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cleanSearchBar(MouseEvent event) {
        this.txtSearchProduct.clear();
    }

}
