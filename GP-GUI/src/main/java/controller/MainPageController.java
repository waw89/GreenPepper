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
import javafx.scene.control.Alert;
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
    @FXML
    private Button btnClean;

    List<ProductOrder> poList = new ArrayList<>();

    BusinessProduct prodBusiness = new BusinessProduct();

    OrderBusiness oBusiness = new OrderBusiness();

    Order order = new Order(); // why is this being created here?

    ProductItemController productItemController;

    ObservableList<IndividualProduct> foodList;

    ObservableList<IndividualProduct> drinkList;

    ObservableList<IndividualProduct> extrasList;

    FilteredList<IndividualProduct> filter;

    List<ProductAddedController> productAddedNodes = new ArrayList<>();

    ProductAddedController productAddedController;
    
    float cummulativeProductPrice = 0; 
    
    float priceRepeatedProducts = 0;

    /**
     * Gets the order of the controller
     *
     * @return
     */
    public Order getOrder() {
        return order;
    }

    public VBox getSummaryContainer() {
        return summaryContainer;
    }

    public ProductItemController getProductItemController() {
        return productItemController;
    }

    public void setProductItemController(ProductItemController productItemController) {
        this.productItemController = productItemController;
    }

    public ProductAddedController getProductAddedController() {
        return productAddedController;
    }

    public void setProductAddedController(ProductAddedController productAddedController) {
        this.productAddedController = productAddedController;
    }

    public AnchorPane getAp() {
        return ap;
    }

    public BorderPane getBp() {
        return bp;
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
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(1);

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

    @FXML
    public void loadPage(String namePage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + namePage + ".fxml"));
            Parent root = loader.load();

            if (namePage.equals("ActiveOrders")) {
                ActiveOrdersController aoController = loader.getController();
                aoController.setMainController(this);  // Pasando el controlador principal
            } else if (namePage.equals("OrdersHistory")) {
                OrdersHistoryController ohController = loader.getController();
                ohController.setMainPageController(this);
            }

            bp.setCenter(root);  // Establece el contenido en el centro del BorderPane
        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * loads a new CreateOrderController, by recieving the name of the FXML and
     * the order that is being created.
     *
     * @param namePage name of the FXML file for the Create Order
     * @param order
     */
    public void loadPage(String namePage, Order order) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + namePage + ".fxml"));
            Parent root = loader.load();
            if (namePage.equals("CreateOrder")) {
                CreateOrderController controller = loader.getController();
                controller.setOrder(order);
                controller.setMainController(this);
            }else if(namePage.equals("EditOrderProducts")){
                EditOrderProductsController eopController = loader.getController();
                eopController.setMainPageController(this);
                eopController.setOrder(order);
                
                
            }
            bp.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void save(MouseEvent event) throws IOException {
        if (!poList.isEmpty()) {
            for (ProductAddedController paController : productAddedNodes) {
                for (ProductItemController piController : paController.getProductItemNodes()) {
                    productItemController.getProductDetails(piController);
                }
            }
            Iterator<ProductOrder> iterator = poList.iterator();
            while (iterator.hasNext()) {
                ProductOrder po = iterator.next();
                if (po.getPRODUCT_SIZE() == null || po.getDetails() == null) {
                    iterator.remove(); // Elimina de forma segura el elemento actual.
                }
            }
            this.order.setProducts(poList);
            loadPage("CreateOrder", this.order);
        } else {
            showEmptyPoListError();
        }

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

    public void updateSummaryWithNewSelectedProduct(ProductOrder productSelected, int selectedAmmountOfProduct) throws IOException {
        boolean productExists = false;

        for (Node node : summaryContainer.getChildren()) {
            ProductAddedController cellController = (ProductAddedController) node.getUserData(); // get the information of the node and cast it to a Controller
            if (cellController.getProductOrder().getProduct().getName().equals(productSelected.getProduct().getName())) { // verify if the selectedProduct is equal to the node in turn in the iteration
                priceRepeatedProducts += productSelected.getPrice();
                int currentAmount = Integer.parseInt(cellController.getTxtAmount());
                int newAmount = currentAmount + selectedAmmountOfProduct;
                cellController.setTxtAmount(String.valueOf(newAmount));
                cellController.setTxtProductSummaryPrice(String.valueOf(String.valueOf(priceRepeatedProducts + oBusiness.calculateCost(order)))); // QUIZAS SEA CONVENIENTE HACER UN METODO SOLO PARA CALCULAR EL NUEVO PRECIO
                productExists = true;

                int j = currentAmount + 1;
                for (int i = 0; i < selectedAmmountOfProduct; i++) {
                    cellController.addProductToListContainer(productSelected.getPrice(), "#" + j);
                    j++;
                    productSelected.setOrder(order);
                    poList.add(productSelected);
                }
                break;
            }
        }
 
        if (!productExists) {
            priceRepeatedProducts = 0;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductAdded.fxml"));
            AnchorPane productCell = loader.load();
            ProductAddedController cellController = loader.getController();
            cellController.setMainController(this);
            cellController.setProductOrder(productSelected);
            cellController.setTxtSummaryProductName(productSelected.getProduct().getName());
            cellController.setTxtProductSummaryPrice(String.valueOf(productSelected.getProduct().getPrice() * selectedAmmountOfProduct));
            cellController.setTxtAmount(String.valueOf(selectedAmmountOfProduct));
            productCell.setUserData(cellController);
            summaryContainer.getChildren().add(productCell);
            productAddedNodes.add(cellController);
            int j = 1;
            for (int i = 0; i < selectedAmmountOfProduct; i++) {
                cellController.addProductToListContainer(productSelected.getPrice(), "#" + j);
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
        Iterator<ProductAddedController> nodeIterator = productAddedNodes.iterator();
        Iterator<ProductOrder> iterator = poList.iterator();
        while (iterator.hasNext()) {
            ProductOrder product = iterator.next();
            if (product.getProduct().getName().equalsIgnoreCase(productOrder.getProduct().getName())) {
                iterator.remove();
            }
        }

        while (nodeIterator.hasNext()) {
            ProductAddedController pa = nodeIterator.next();
            if (pa.getProductOrder().getProduct().getName().equalsIgnoreCase(productOrder.getProduct().getName())) {
                nodeIterator.remove();
            }
        }

        lblSubtotal.setText("$" + oBusiness.calculateCost(order));
        lblTotal.setText(lblSubtotal.getText());
    }

    public void removeProductFromProductList(ProductOrder productOrder, Node node, int newAmount) {
        cummulativeProductPrice = 0;
        poList.remove(productOrder);
        ProductAddedController cellController = (ProductAddedController) node.getUserData();
        cellController.setTxtAmount(String.valueOf(newAmount));
        cellController.setTxtProductSummaryPrice(String.valueOf(cellController.getProductOrder().getPrice() * newAmount));
        
        for (ProductAddedController paController : productAddedNodes) {
                for (ProductItemController piController : paController.getProductItemNodes()) {
                    if(piController.getProductOrder().getProduct().getName().equals(productOrder.getProduct().getName())){
                      cummulativeProductPrice += piController.getProductOrder().getPrice();
                    }
                }
            }
        
        cellController.setTxtProductSummaryPrice(String.valueOf(cummulativeProductPrice));
        lblSubtotal.setText("$" + oBusiness.calculateCost(order));
        lblTotal.setText(lblSubtotal.getText());
    }

    public void cleanSummary() {
        summaryContainer.getChildren().clear();
        poList.clear();
        productAddedController.getProductItemNodes().clear();
        productAddedNodes.clear();
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

                // Pasar la instancia del MainPageController al ProductCardController
                cardController.setMainController(this);

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

                // Pasar la instancia del MainPageController al ProductCardController
                cardController.setMainController(this);

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
                cardController.setMainController(this);
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

    public void updateTotalPrice() {
        float total = 0;
        for (Node node : summaryContainer.getChildren()) {
            ProductAddedController paController = (ProductAddedController) node.getUserData();
            total += Float.parseFloat(paController.getTxtProductSummaryPrice());
        }

        lblSubtotal.setText("$" + oBusiness.calculateCost(order));
        lblTotal.setText(lblSubtotal.getText());
        order.setPrice(oBusiness.calculateCost(order));
    }

    public void removeItemFromPoList(ProductOrder productOrder) {
        poList.remove(productOrder);
        this.order.setProducts(poList);
    }

    public void addItemToPoList(ProductOrder productOrder) {
         poList.add(productOrder);
        this.order.setProducts(poList);
    }

    private void showEmptyPoListError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Lista de productos vacía");
        alert.setContentText("No se puede crear la orden. Agregue al menos un producto");
        alert.showAndWait();
    }
}
