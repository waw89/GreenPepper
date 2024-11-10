/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import business.UserBusiness;
import com.mycompany.gp.domain.DeliveryOrder;
import com.mycompany.gp.domain.DinerOrder;
import com.mycompany.gp.domain.Employee;
import com.mycompany.gp.domain.ORDER_STATE;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.PickUpOrder;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class CreateOrderController implements Initializable {

    private Order order;
    @FXML
    private Button btnDelivery;
    @FXML
    private Button btnDiner;
    @FXML
    private Button btnPickUp;
    @FXML
    private VBox txtFieldContainer;
    @FXML
    private TextArea txtDetails;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnCreateOrder;

    private String type;

    UserBusiness us = new UserBusiness();

    OrderBusiness ob = new OrderBusiness();

    Node currentNode;

    private static DeliveryFieldsController dfc;

    private static DinerFieldsController dinerFieldsController;

    private static PickUpFieldsController pickUpFieldsController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type = "Delivery";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeliveryFields.fxml"));
            AnchorPane txtFieldArea = loader.load();
            DeliveryFieldsController dfc = loader.getController();
            this.dfc = dfc;
            this.currentNode = txtFieldArea;
            txtFieldContainer.getChildren().add(txtFieldArea);

        } catch (IOException ex) {
            Logger.getLogger(CreateOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @FXML
    private void deliveryOption(MouseEvent event) {
        if (type != "Delivery") {
            try {
                txtFieldContainer.getChildren().removeAll(this.currentNode);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeliveryFields.fxml"));
                AnchorPane txtFieldArea = loader.load();
                DeliveryFieldsController dfc = loader.getController();
                this.dfc = dfc;
                txtFieldContainer.getChildren().add(txtFieldArea);
                this.currentNode = txtFieldArea;

                type = "Delivery";
            } catch (IOException ex) {
                Logger.getLogger(CreateOrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void dinerOption(MouseEvent event) {
        if (type != "Diner") {
            try {
                txtFieldContainer.getChildren().removeAll(this.currentNode);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DinerFields.fxml"));
                AnchorPane txtFieldArea = loader.load();
                DinerFieldsController dinnerFieldsController = loader.getController();
                this.dinerFieldsController = dinnerFieldsController;
                txtFieldContainer.getChildren().add(txtFieldArea);
                this.currentNode = txtFieldArea;
                type = "Diner";

            } catch (IOException ex) {
                Logger.getLogger(CreateOrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void pickUpOption(MouseEvent event) {

        if (type != "Pick Up") {
            try {
                txtFieldContainer.getChildren().removeAll(this.currentNode);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PickUpFields.fxml"));
                AnchorPane txtFieldArea = loader.load();
                PickUpFieldsController pickUpFieldsController = loader.getController();
                this.pickUpFieldsController = pickUpFieldsController;
                this.currentNode = txtFieldArea;
                txtFieldContainer.getChildren().add(txtFieldArea);
                type = "Pick Up";

            } catch (IOException ex) {
                Logger.getLogger(CreateOrderController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void goBack(MouseEvent event) {

    }

    @FXML
    private void createOrder(MouseEvent event) throws IOException {

        switch (type) {
            case "Delivery":
                if (!dfc.hasEmptyFields()) {
                    saveForDelivery();
                    showOrderConfirmation();
                }else{
                    showEmptyFieldsError();
                }
                break;
            case "Diner":
                if (!dinerFieldsController.hasEmptyFields()) {
                    saveForDiner();
                    showOrderConfirmation();
                }else{
                    showEmptyFieldsError();
                }
                break;
            case "Pick Up":
                if (!pickUpFieldsController.hasEmptyFields()) {
                    saveForPickUp();
                    showOrderConfirmation();
                }else{
                    showEmptyFieldsError();
                }
                break;
            default:
                break;
        }
    }

    private DeliveryOrder saveForDelivery() {

        DeliveryOrder deliveryOrder = new DeliveryOrder();
        List<ProductOrder> productsForDelivery = new ArrayList<>();

        for (ProductOrder po : this.order.getProducts()) {
            po.setOrder(deliveryOrder);
            productsForDelivery.add(po);
        }

        deliveryOrder.setProducts(productsForDelivery);

        deliveryOrder.setPrice(order.getPrice());

        deliveryOrder.setCustomerName(this.dfc.getTxtCustomerName().getText());

        deliveryOrder.setAddress(this.dfc.getTxtCustomerAddress().getText());

        deliveryOrder.setPhoneNumber(this.dfc.getTxtCustomerPhone().getText());

        deliveryOrder.setDetails(this.txtDetails.getText());

        deliveryOrder.setState(ORDER_STATE.ACTIVE);

        deliveryOrder.setCreationDate(LocalDateTime.now());

        deliveryOrder.setCashier((Employee) us.findUser(2L));

        ob.createDeliveryOrder(deliveryOrder);

        System.out.println("Orden creada!");

        return deliveryOrder;
    }

    private DinerOrder saveForDiner() {
        DinerOrder dinerOrder = new DinerOrder();
        List<ProductOrder> productsForDiner = new ArrayList<>();

        for (ProductOrder po : this.order.getProducts()) {
            po.setOrder(dinerOrder);
            productsForDiner.add(po);
        }

        dinerOrder.setOrderName(this.dinerFieldsController.getTxtTableName().getText().toString());

        dinerOrder.setProducts(productsForDiner);

        dinerOrder.setPrice(order.getPrice());

        dinerOrder.setDetails(this.txtDetails.getText());

        dinerOrder.setOrderState(ORDER_STATE.ACTIVE);

        dinerOrder.setCreationDate(LocalDateTime.now());

        dinerOrder.setCashier((Employee) us.findUser(2L));

        ob.createDinerOrder(dinerOrder);

        System.out.println("Orden creada!");

        return dinerOrder;

    }

    private PickUpOrder saveForPickUp() {
        PickUpOrder pickUpOrder = new PickUpOrder();
        List<ProductOrder> productsForPickUp = new ArrayList<>();

        for (ProductOrder po : this.order.getProducts()) {
            po.setOrder(pickUpOrder);
            productsForPickUp.add(po);
        }

        pickUpOrder.setCustomerName(this.pickUpFieldsController.getCustomerNameTxt().getText().toString());

        pickUpOrder.setCustomerPhone(this.pickUpFieldsController.getPhoneNumberTxt().getText().toString());

        pickUpOrder.setProducts(productsForPickUp);

        pickUpOrder.setPrice(order.getPrice());

        pickUpOrder.setDetails(this.txtDetails.getText());

        pickUpOrder.setOrderState(ORDER_STATE.ACTIVE);

        pickUpOrder.setCreationDate(LocalDateTime.now());

        pickUpOrder.setCashier((Employee) us.findUser(2L));

        ob.createPickUpOrder(pickUpOrder);

        System.out.println("Orden creada!");

        return pickUpOrder;
    }

    private void showOrderConfirmation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Orden creada");
        alert.setContentText("Se ha creado la orden correctamente");
        alert.showAndWait();
    }
    
     private void showEmptyFieldsError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error al crear la orden");
        alert.setContentText("No se pudo crear la orden. Debe llenar los campos obligatorios");
        alert.showAndWait();
    }
}
