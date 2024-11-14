/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

    private void loadProductSummary() {
        poList.addAll(this.order.getProducts());
        
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
                cellController.setMainController(mainPageController);
                cellController.setProductOrder(po);
                cellController.setTxtSummaryProductName(po.getProduct().getName());
                cellController.setTxtProductSummaryPrice(String.valueOf(po.getPrice()));
                cellController.setTxtAmount(String.valueOf(productAmount));
                productCell.setUserData(cellController);
                summaryContainer.getChildren().add(productCell);
                productsAddedToSummary.add(po);
                productAddedNodes.add(cellController);
                cellController.addExistentProductToListContainer(po.getPrice(), "#" + (j+1), po.getDetails(), po.getPRODUCT_SIZE());
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
                    
                    
                    
                    cellController.addExistentProductToListContainer(po.getPrice(), "#" + (j+1), po.getDetails(), po.getPRODUCT_SIZE());
                
                }

            }
            lblSubtotal.setText("$" + oBusiness.calculateCost(order));
            lblTotal.setText(lblSubtotal.getText());
            order.setPrice(oBusiness.calculateCost(order));
        }

    }
}
