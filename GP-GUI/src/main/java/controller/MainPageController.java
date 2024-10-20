/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.BusinessProduct;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.Product;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    private Button btnSearch;
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

    BusinessProduct prodBusiness = new BusinessProduct();
    @FXML
    private VBox summaryContainer;

    List<ProductOrder> poList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Product> products = prodBusiness.getAllProducts();
        for (Product product : products) {
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

    @FXML
    private void save(MouseEvent event) {
        loadPage("CreateOrder");
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

    public void updateSummary(ProductOrder productSelected, Order order) {

        for (int i = 0; i < productSelected.getAmount(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductAdded.fxml"));
                AnchorPane productCell = loader.load();
                ProductAddedController cellController = loader.getController();
                cellController.setMainController(this);
                cellController.setProductOrder(productSelected);
                cellController.setTxtSummaryProductName(productSelected.getProduct().getName());
                cellController.setTxtProductSummaryPrice("$" + productSelected.getProduct().getPrice());
                summaryContainer.getChildren().add(productCell);
                poList.add(productSelected);

            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        order.setProducts(poList);
        System.out.println("Productos en la orden: " + poList.size());
    }

    public void removeProductFromSummary(Node productNode, ProductOrder productOrder) {
        summaryContainer.getChildren().remove(productNode);
        poList.remove(productOrder);
    }

}
