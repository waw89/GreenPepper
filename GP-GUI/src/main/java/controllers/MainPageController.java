/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import core.ViewHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import model.OrderModel;

/**
 *
 * @author waw
 */
public class MainPageController {

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

    public void init(ViewHandler viewHandler, OrderModel orderModel) {
        this.viewHandler = viewHandler;
        this.orderModel = orderModel;
    }

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
        System.out.println("Saving stuff");
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
        Variables
    */
    private ViewHandler viewHandler;
    private OrderModel orderModel;

}
