/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.DinerOrder;
import com.mycompany.gp.domain.Order;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class OrderCardController implements Initializable {

    @FXML
    private Text txtNombreMesa;
    @FXML
    private Text txtFolioMesa;
    @FXML
    private Button btnViewDetails;
    @FXML
    private Button btnPay;
    
    private DinerOrder dinerOrder;

    OrderBusiness ob = new OrderBusiness();
    
    MainPageController mainPageController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(dinerOrder != null){
            setDinerOrder(dinerOrder);
        }
        
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
    
    
    public void setDinerOrder(DinerOrder order){
        this.dinerOrder = order;
    }
    
    public Text getTxtNombreMesa() {
        return txtNombreMesa;
    }

    public void setTxtNombreMesa(String txtNombreMesa) {
        this.txtNombreMesa.setText(txtNombreMesa); 
    }

    public Text getTxtFolioMesa() {
        return txtFolioMesa;
    }

    public void setTxtFolioMesa(String txtFolioMesa) {
        this.txtFolioMesa.setText(txtFolioMesa);
    }

    public Button getBtnViewDetails() {
        return btnViewDetails;
    }

    public void setBtnViewDetails(Button btnViewDetails) {
        this.btnViewDetails = btnViewDetails;
    }

    public Button getBtnPay() {
        return btnPay;
    }

    public void setBtnPay(Button btnPay) {
        this.btnPay = btnPay;
    }

    public OrderBusiness getOb() {
        return ob;
    }

    public void setOb(OrderBusiness ob) {
        this.ob = ob;
    }

    
    @FXML
    private void OptionViewDetails(MouseEvent event) {
    
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ActiveOrderDetailDiner.fxml"));
            Parent detail = loader.load();
            
            ActiveOrderDetailDinerController detailController = loader.getController();
            
            detailController.setDinerOrder(dinerOrder);
            detailController.setMainPageController(mainPageController);
//            Stage stage = new Stage();
//            stage.setScene(new Scene(detail));
//            stage.showAndWait();
            mainPageController.getBp().setCenter(detail);
            txtNombreMesa.setText(dinerOrder.getOrderName());
                    
        } catch (IOException ex) {
            Logger.getLogger(OrderCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void OptionPayOrder(MouseEvent event) {
    }


}
