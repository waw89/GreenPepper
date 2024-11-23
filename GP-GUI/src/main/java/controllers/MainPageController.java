/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.PRODUCT_SIZE;
import com.mycompany.gp.domain.PRODUCT_TYPE;
import core.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.text.Text;
import model.MainPageModel;

/**
 * Controller for the main page. This controller is the first to be initialized
 * in the application. It handles all the actions of the user in the
 * MainPage.fxml
 *
 * @author Luis Enrique Contreras Peraza
 * @author Jesús Raúl Luna Bringas
 * @author Brayan D. García Picos
 */
public class MainPageController {


    /*
        JavaFX Methods
     */
    @FXML
    private void homeImgClick(MouseEvent event) {
    }

    @FXML
    private void home(MouseEvent event) {
    }

    @FXML
    private void activeOrders(MouseEvent event) {
    }

    @FXML
    private void ordersHistory(MouseEvent event) {
    }

    @FXML
    private void logOut(MouseEvent event) {
    }

    @FXML
    private void ordersImgClick(MouseEvent event) {
    }

    @FXML
    private void historyImgClick(MouseEvent event) {
    }

    @FXML
    private void logOutImgClick(MouseEvent event) {
    }

    @FXML
    private void cleanFields(MouseEvent event) {
    }

    @FXML
    private void save(MouseEvent event) {

    }

    @FXML
    private void showFoods(MouseEvent event) {
    }

    @FXML
    private void showDrinks(MouseEvent event) {
    }

    @FXML
    private void showExtras(MouseEvent event) {
    }

    @FXML
    private void search(KeyEvent event) {
    }

    @FXML
    private void cleanSearchBar(MouseEvent event) {

    }

    /*
        Class methods
     */
    /**
     * This method initializes the controller with the view handler and an order
     * model instance.
     */
    public void init(ViewHandler viewHandler, MainPageModel orderModel) {
        this.viewHandler = viewHandler;
        this.mainPageModel = orderModel;
        doSetup();

    }

    /**
     * Performs confgurations before starting the flow of the program.
     */
    public void doSetup() {
        chargeProductsToDatabase();
        loadFoodProducts();
    }

    /**
     * Calls the method to charge products to the database
     */
    public void chargeProductsToDatabase() {
        mainPageModel.chargeProductsToDatabase();
    }

    /**
     * Handles the request of adding a new selected product.
     */
    public void addProductToSummary(ProductCardController productCardController) {
        // create the loader
        try {
            generateNewProductAdded(productCardController);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Iterates the products of the database and creates a Product card for each
     * of them, then it adds the product card to the product container
     */
    public void loadFoodProducts() {
        this.mainPageModel.initializaListOfProductCard();
        try {
            for (IndividualProduct foodProduct : mainPageModel.getProductsFromDatabase()) {
                if (foodProduct.getType().equals(PRODUCT_TYPE.FOOD)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductCard.fxml")); // create a loader for the fxml file
                    AnchorPane productCard = loader.load(); // load the productCard
                    ProductCardController cardController = loader.getController(); // get a controller
                    cardController.setProductCardAnchorPaneElement(productCard);
                    cardController.customControllerWithProductData(foodProduct, this);
                    productContainer.getChildren().add(productCard);
                    this.mainPageModel.addProductCardToList(cardController);
                    cardController.setupBinding();
                }

            }

        } catch (Exception e) {

            System.err.println(e);
        }

    }

    /**
     * Loads a new ProductAddedController and then adds it to the list of the
     * summary.
     *
     * @return The ProductAddedController that was generated
     * @throws Exception
     */
    public ProductAddedController generateNewProductAdded(ProductCardController productCardController) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ProductAdded.fxml"));
        AnchorPane productAddedAnchorPane = loader.load();

        ProductAddedController productAddedController = loader.getController();
        
        
        productAddedController.customControllerWithProductCardData(productCardController.getProductCardModel());
        productAddedController.setupBinding();
        configureProductAddedWithProductCard(productCardController, productAddedController);
        addProductAddedToListOfModel(productAddedController);
        productAddedController.setAnchorPaneElement(productAddedAnchorPane);
        addNewProductItemToProductAdded(productAddedController);

        summaryContainer.getChildren().add(productAddedAnchorPane);

        return productAddedController;
    }

    public ProductAddedController configureProductAddedWithProductCard(ProductCardController productCardController, ProductAddedController productAddedController) {


        return productAddedController;

    }

    public ProductAddedController addNewProductItemToProductAdded(ProductAddedController productAddedController) throws Exception {

        productAddedController.addProductItemToProductAdded(productAddedController);

        return productAddedController;
    }

    /**
     * Update the list of the ProductAddedController from the
     *
     * @param productAddedController
     * @return
     */
    public ProductAddedController addProductAddedToListOfModel(ProductAddedController productAddedController) {
        if (mainPageModel.getListOfProductAddedElements() == null) {
            mainPageModel.initializesListOfProductAdded(); // initializes the list from the model  
        }
        mainPageModel.addProductAddedToList(productAddedController);
        return productAddedController;
    }

    @FXML
    private BorderPane bp;

    @FXML
    private ImageView homeImg;
    @FXML
    private Button btnInicio;

    @FXML
    private Button btnActiveOrders;

    @FXML
    private Button btnOrdersHistory;

    @FXML
    private Button btnLogOut;

    @FXML
    private ImageView orderImg;

    @FXML
    private ImageView historyImg;

    @FXML
    private ImageView logOutImg;

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
        Class variables
     */
    private ViewHandler viewHandler;

    private MainPageModel mainPageModel;

}
