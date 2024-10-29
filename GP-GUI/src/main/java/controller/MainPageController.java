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
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class MainPageController implements Initializable {

    @FXML
    private Button btnInicio;
    @FXML
    private Button btnActiveOrders;
    @FXML
    private Button btnOrdersHistory;
    @FXML
    private Button btnLogOut;
    @FXML
    private AnchorPane ap;
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
    private BorderPane bp;
    @FXML
    private ImageView homeImg;
    @FXML
    private ImageView orderImg;
    @FXML
    private ImageView historyImg;
    @FXML
    private ImageView logOutImg;
    @FXML
    private VBox productContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox summaryContainer;
    @FXML
    private Label lblSubtotal;
    @FXML
    private Label lblDiscount;
    @FXML
    private Label lblTotal;

    List<ProductOrder> poList = new ArrayList<>();
    BusinessProduct prodBusiness = new BusinessProduct();
    OrderBusiness oBusiness = new OrderBusiness();
    Order order = new Order();

    ObservableList<IndividualProduct> foodList;
    ObservableList<IndividualProduct> drinkList;
    ObservableList<IndividualProduct> extrasList;
    FilteredList<IndividualProduct> filter;
    @FXML
    private Button btnClean;

    public Order getOrder() {
        return order;
    }

    public VBox getSummaryContainer() {
        return summaryContainer;
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

                // Pasar la instancia del MainPageController al ProductCardController
                cardController.setMainController(this);

                cardController.setTxtProductName(product.getName());
                cardController.setTxtPrice("$" + product.getPrice());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(10);

            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void home(MouseEvent event) {
        bp.setCenter(ap);
    }

    @FXML
    private void activeOrders(MouseEvent event) {
        loadPage("ActiveOrders");
    }

    @FXML
    private void ordersHistory(MouseEvent event) {
        loadPage("OrdersHistory");
    }

    @FXML
    private void logOut(MouseEvent event) {
    }

    private void loadPage(String namePage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/" + namePage + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        bp.setCenter(root);
    }

    private void loadPage(String namePage, Order order) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + namePage + ".fxml"));
            Parent root = loader.load();
            CreateOrderController controller = loader.getController();
            controller.setOrder(order);
            bp.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void save(MouseEvent event) throws IOException {

        loadPage("CreateOrder", this.order);
    }

    @FXML
    private void homeImgClick(MouseEvent event) {
        home(event);
    }

    @FXML
    private void ordersImgClick(MouseEvent event) {
        activeOrders(event);
    }

    @FXML
    private void historyImgClick(MouseEvent event) {
        ordersHistory(event);
    }

    @FXML
    private void logOutImgClick(MouseEvent event) {
    }

    public void updateSummary(ProductOrder productSelected, int amount) throws IOException {
        boolean productExists = false;

        for (Node node : summaryContainer.getChildren()) {
            ProductAddedController cellController = (ProductAddedController) node.getUserData();
            if (cellController.getProductOrder().getProduct().getName().equals(productSelected.getProduct().getName())) {
                int currentAmount = Integer.parseInt(cellController.getTxtAmount());
                int newAmount = currentAmount + amount;
                cellController.setTxtAmount(String.valueOf(newAmount));
                cellController.setTxtProductSummaryPrice("$" + productSelected.getProduct().getPrice() * newAmount);
                productExists = true;

                int j = currentAmount + 1;
                for (int i = 0; i < amount; i++) {
                    cellController.addProductToListContainer("$" + productSelected.getPrice(), "#" + j);
                    j++;
                    productSelected.setOrder(order);
                    poList.add(productSelected);
                }
                break;
            }
        }

        if (!productExists) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductAdded.fxml"));
            AnchorPane productCell = loader.load();
            ProductAddedController cellController = loader.getController();
            cellController.setMainController(this);
            cellController.setProductOrder(productSelected);
            cellController.setTxtSummaryProductName(productSelected.getProduct().getName());
            cellController.setTxtProductSummaryPrice("$" + productSelected.getProduct().getPrice() * amount);
            cellController.setTxtAmount(String.valueOf(amount));
            productCell.setUserData(cellController);
            summaryContainer.getChildren().add(productCell);

            int j = 1;
            for (int i = 0; i < amount; i++) {
                cellController.addProductToListContainer("$" + productSelected.getPrice(), "#" + j);
                j++;
                productSelected.setOrder(order);
                poList.add(productSelected);
            }
        }

        order.setProducts(poList);
        lblSubtotal.setText("$" + oBusiness.calculateCost(order));
        lblTotal.setText(lblSubtotal.getText());
        order.setPrice(oBusiness.calculateCost(order));
    }

    public void removeProductFromSummary(Node productNode, ProductOrder productOrder) {
        summaryContainer.getChildren().remove(productNode);

        Iterator<ProductOrder> iterator = poList.iterator();
        while (iterator.hasNext()) {
            ProductOrder product = iterator.next();
            if (product.getProduct().getName().equalsIgnoreCase(productOrder.getProduct().getName())) {
                iterator.remove();
            }
        }

        lblSubtotal.setText("$" + oBusiness.calculateCost(order));
        lblTotal.setText(lblSubtotal.getText());
    }

    public void removeProductFromProductList(ProductOrder productOrder, Node node, int newAmount) {
        poList.remove(productOrder);
        ProductAddedController cellController = (ProductAddedController) node.getUserData();
        cellController.setTxtAmount(String.valueOf(newAmount));
        cellController.setTxtProductSummaryPrice(String.valueOf(cellController.getProductOrder().getPrice() * newAmount));
        lblSubtotal.setText("$" + oBusiness.calculateCost(order));
        lblTotal.setText(lblSubtotal.getText());
    }

    public void cleanSummary() {
        summaryContainer.getChildren().clear();
        poList.clear();
        lblSubtotal.setText("$0.00");
        lblTotal.setText(lblSubtotal.getText());

    }

    @FXML
    private void cleanFields(MouseEvent event) {
        cleanSummary();
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

                // Pasar la instancia del MainPageController al ProductCardController
                cardController.setMainController(this);

                cardController.setTxtProductName(product.getName());
                cardController.setTxtPrice("$" + product.getPrice());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(10);

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

                // Pasar la instancia del MainPageController al ProductCardController
                cardController.setMainController(this);

                cardController.setTxtProductName(product.getName());
                cardController.setTxtPrice("$" + product.getPrice());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(10);

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

                // Pasar la instancia del MainPageController al ProductCardController
                cardController.setMainController(this);

                cardController.setTxtProductName(product.getName());
                cardController.setTxtPrice("$" + product.getPrice());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(10);

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
                cardController.setMainController(this);
                cardController.setTxtProductName(product.getName());
                cardController.setTxtPrice("$" + product.getPrice());

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
