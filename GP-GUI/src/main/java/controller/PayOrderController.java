/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.DeliveryOrder;
import com.mycompany.gp.domain.DinerOrder;
import com.mycompany.gp.domain.ORDER_STATE;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.PickUpOrder;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class PayOrderController implements Initializable {

    @FXML
    private ImageView btnBack;
    @FXML
    private Text txtIdOrder;
    @FXML
    private Text txtOpenDate;
    @FXML
    private Text txtTotalAmount;
    @FXML
    private Text txtCambio;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtAmountReceived;
    @FXML
    private Button btnCalcular;
    
    private Order order;
    private DinerOrder dinerOrder;    
    private DeliveryOrder deliveryOrder;
    private PickUpOrder pickUpOrder;
    OrderBusiness oBusines = new OrderBusiness();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(dinerOrder != null){
            setDinerOrder(dinerOrder);
        }
        
        if(deliveryOrder != null){
            setDeliveryOrder(deliveryOrder);
        }
        
        if(pickUpOrder != null){
            setPickUpOrder(pickUpOrder);
        }
        
        btnAccept.setDisable(true);
        
    }

    public ImageView getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(ImageView btnBack) {
        this.btnBack = btnBack;
    }

    public Text getTxtIdOrder() {
        return txtIdOrder;
    }

    public void setTxtIdOrder(Text txtIdOrder) {
        this.txtIdOrder = txtIdOrder;
    }

    public Text getTxtOpenDate() {
        return txtOpenDate;
    }

    public void setTxtOpenDate(Text txtOpenDate) {
        this.txtOpenDate = txtOpenDate;
    }

    public Text getTxtTotalAmount() {
        return txtTotalAmount;
    }

    public void setTxtTotalAmount(Text txtTotalAmount) {
        this.txtTotalAmount = txtTotalAmount;
    }

    public Text getTxtCambio() {
        return txtCambio;
    }

    public void setTxtCambio(Text txtCambio) {
        this.txtCambio = txtCambio;
    }

    public Button getBtnAccept() {
        return btnAccept;
    }

    public void setBtnAccept(Button btnAccept) {
        this.btnAccept = btnAccept;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public DinerOrder getDinerOrder() {
        return dinerOrder;
    }

    public void setDinerOrder(DinerOrder dinerOrder) {
        this.dinerOrder = dinerOrder;
        loadData();
    }

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
        loadData();
    }

    public PickUpOrder getPickUpOrder() {
        return pickUpOrder;
    }

    public void setPickUpOrder(PickUpOrder pickUpOrder) {
        this.pickUpOrder = pickUpOrder;
        loadData();
    }

    public TextField getTxtAmountReceived() {
        return txtAmountReceived;
    }

    public void setTxtAmountReceived(TextField txtAmountReceived) {
        this.txtAmountReceived = txtAmountReceived;
    }
    
    public void setOrder(Order order){
        this.order = order;
    }
    
    

    private void loadData(){
        if(dinerOrder instanceof DinerOrder ){
            
            txtOpenDate.setText(dinerOrder.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            txtIdOrder.setText("#" + dinerOrder.getOrderNumber() + " - " + dinerOrder.getOrderName());
            txtTotalAmount.setText("$" + dinerOrder.getPrice());
            
        } else if(deliveryOrder instanceof DeliveryOrder){
            
            txtOpenDate.setText(deliveryOrder.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            txtIdOrder.setText(deliveryOrder.getCustomerName() + " - " + deliveryOrder.getAddress() + " - " + deliveryOrder.getPhoneNumber());
            txtTotalAmount.setText("$" + deliveryOrder.getPrice());
            
        } else if(pickUpOrder instanceof PickUpOrder){
            
            txtOpenDate.setText(pickUpOrder.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            txtIdOrder.setText(pickUpOrder.getCustomerName() + " - " + pickUpOrder.getCustomerPhone());
            txtTotalAmount.setText("$" + pickUpOrder.getPrice());
            
        }
    }

    @FXML
    private void OptionBack(MouseEvent event) {
    
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void OptionCalculate(MouseEvent event){
    
        txtCambio.setText("$0");
        
        
        
        if(dinerOrder instanceof DinerOrder){
            try {

                Float amountTotal = dinerOrder.getPrice();
                Float amountReceived = Float.parseFloat(txtAmountReceived.getText());

                if (amountReceived >= amountTotal) {
                    Float cambio = amountReceived - amountTotal;
                    txtCambio.setStyle("-fx-fill: black;");
                    txtCambio.setText("$" + String.format("%.2f", cambio));
                    btnAccept.setDisable(false);
                } else {
                    txtCambio.setStyle("-fx-fill: red;");
                    txtCambio.setText("Monto Insuficiente");
                    btnAccept.setDisable(true);
                }

            } catch (NumberFormatException e) {
                txtCambio.setText("Entrada inválida");
            }     
        } else if(deliveryOrder instanceof DeliveryOrder){
            try {

                Float amountTotal = deliveryOrder.getPrice();
                Float amountReceived = Float.parseFloat(txtAmountReceived.getText());

                if (amountReceived >= amountTotal) {
                    Float cambio = amountReceived - amountTotal;
                    txtCambio.setStyle("-fx-fill: black;");
                    txtCambio.setText("$" + String.format("%.2f", cambio));
                    btnAccept.setDisable(false);
                } else {
                    txtCambio.setStyle("-fx-fill: red;");
                    txtCambio.setText("Monto Insuficiente");
                    btnAccept.setDisable(true);
                }

            } catch (NumberFormatException e) {
                txtCambio.setText("Entrada inválida");
            } 
        } else if(pickUpOrder instanceof PickUpOrder){
            
            try {

                Float amountTotal = pickUpOrder.getPrice();
                Float amountReceived = Float.parseFloat(txtAmountReceived.getText());

                if (amountReceived >= amountTotal) {
                    Float cambio = amountReceived - amountTotal;
                    txtCambio.setStyle("-fx-fill: black;");
                    txtCambio.setText("$" + String.format("%.2f", cambio));
                    btnAccept.setDisable(false);
                } else {
                    txtCambio.setStyle("-fx-fill: red;");
                    txtCambio.setText("Monto Insuficiente");
                    btnAccept.setDisable(true);
                }

            } catch (NumberFormatException e) {
                txtCambio.setText("Entrada inválida");
            }
        } 
          
    }

    @FXML
    private void OptionAccept(MouseEvent event) {
    
        if(dinerOrder instanceof DinerOrder){
            dinerOrder.setORDER_STATE(ORDER_STATE.PAID);
            oBusines.EditDataDiner(dinerOrder);
        }
        
        
        Stage stage = (Stage) btnAccept.getScene().getWindow();
        stage.close();
    
    }

    @FXML
    private void OptionCancel(MouseEvent event) {
    
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    
    }
    
}
