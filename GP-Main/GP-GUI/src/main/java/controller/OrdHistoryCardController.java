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
import java.util.List;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class OrdHistoryCardController implements Initializable {

    @FXML
    private Text txtMesa;
    @FXML
    private Text txtIdPedido;
    @FXML
    private Button btnVerDetalles;
    @FXML
    private Text txtEstado;

    Order order;
    OrderBusiness oBusiness = new OrderBusiness();
    List<DinerOrder> orderList = oBusiness.getAllDinOrder();
    MainPageController mainController;

    public void setMainController(MainPageController mainController) {
        this.mainController = mainController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderList = oBusiness.getAllDinOrder();
    }

    public Text getTxtMesa() {
        return txtMesa;
    }

    public void setTxtMesa(String txtMesa) {
        this.txtMesa.setText(txtMesa);
    }

    public Text getTxtIdPedido() {
        return txtIdPedido;
    }

    public void setTxtIdPedido(String txtIdPedido) {
        this.txtIdPedido.setText(txtIdPedido);
    }

    public Button getBtnVerDetalles() {
        return btnVerDetalles;
    }

    public void setBtnVerDetalles(Button btnVerDetalles) {
        this.btnVerDetalles = btnVerDetalles;
    }

    public Text getTxtEstado() {
        return txtEstado;
    }

    public void setTxtEstado(String txtEstado) {
        if ("CANCELADO".equals(txtEstado)) {
            this.txtEstado.setFill(Color.RED);
        } else if ("PAGADO".equals(txtEstado)) {
            this.txtEstado.setFill(Color.GREEN);
        }

        this.txtEstado.setText(txtEstado);
    }

    private DinerOrder findOrder(String idPedido) {

        Long orderId;
        orderId = Long.valueOf(idPedido);

        for (DinerOrder orderFind : orderList) {
            if (orderFind.getOrderNumber().equals(orderId)) {
                return orderFind;
            }
        }
        return null;
    }

    @FXML
    private void detailOption(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClosedOrderDetail.fxml"));
            Parent detail = loader.load();

            ClosedOrderDetailController detailController = loader.getController();
            
            String idPedido = txtIdPedido.getText();
            DinerOrder order = findOrder(idPedido);
            detailController.setOrderDetails(order);
            detailController.setMainPageController(mainController);
            //detailController.setTxtIdOrder(new Text(txtIdPedido.getText()));
            mainController.getBp().setCenter(detail);

        } catch (IOException ex) {
            Logger.getLogger(OrdHistoryCardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
