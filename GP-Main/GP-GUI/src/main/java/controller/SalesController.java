/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.ORDER_STATE;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.PAYMENT_METHOD;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SalesController implements Initializable{

    @FXML
    private DatePicker fechaInicial;
    @FXML
    private CheckBox chBoxTodoTiempo;
    @FXML
    private CheckBox chBoxAnual;
    @FXML
    private CheckBox chBoxMensual;
    @FXML
    private CheckBox chBoxRango;
    @FXML
    private DatePicker fechaFinal;
    @FXML
    private Button btnVisualizar;
    @FXML
    private Button btnImprimir;
    @FXML
    private CheckBox chBoxSemanal;
    
    OrderBusiness oBussines = new OrderBusiness();
    List<Order> orders = oBussines.getAllOrder();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orders = oBussines.getAllOrder();
        fechaInicial.setDisable(true);
        fechaFinal.setDisable(true);
    }

    public DatePicker getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(DatePicker fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public CheckBox getChBoxTodoTiempo() {
        return chBoxTodoTiempo;
    }

    public void setChBoxTodoTiempo(CheckBox chBoxTodoTiempo) {
        this.chBoxTodoTiempo = chBoxTodoTiempo;
    }

    public CheckBox getChBoxAnual() {
        return chBoxAnual;
    }

    public void setChBoxAnual(CheckBox chBoxAnual) {
        this.chBoxAnual = chBoxAnual;
    }

    public CheckBox getChBoxMensual() {
        return chBoxMensual;
    }

    public void setChBoxMensual(CheckBox chBoxMensual) {
        this.chBoxMensual = chBoxMensual;
    }

    public CheckBox getChBoxRango() {
        return chBoxRango;
    }

    public void setChBoxRango(CheckBox chBoxRango) {
        this.chBoxRango = chBoxRango;
    }

    public DatePicker getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(DatePicker fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Button getBtnVisualizar() {
        return btnVisualizar;
    }

    public void setBtnVisualizar(Button btnVisualizar) {
        this.btnVisualizar = btnVisualizar;
    }

    public Button getBtnImprimir() {
        return btnImprimir;
    }

    public void setBtnImprimir(Button btnImprimir) {
        this.btnImprimir = btnImprimir;
    }

    public CheckBox getChBoxSemanal() {
        return chBoxSemanal;
    }

    public void setChBoxSemanal(CheckBox chBoxSemanal) {
        this.chBoxSemanal = chBoxSemanal;
    }

    

    @FXML
    private void checkBoxAction(ActionEvent event) {
        if(chBoxTodoTiempo.isSelected()){
            
            chBoxAnual.setSelected(false);
            chBoxMensual.setSelected(false);
            chBoxSemanal.setSelected(false);
            chBoxRango.setSelected(false);
            fechaInicial.setDisable(true);
            fechaFinal.setDisable(true);
            
        } else if(chBoxAnual.isSelected()){
            
            chBoxTodoTiempo.setSelected(false);
            chBoxMensual.setSelected(false);
            chBoxSemanal.setSelected(false);
            chBoxRango.setSelected(false);
            fechaInicial.setDisable(true);
            fechaFinal.setDisable(true);
            
        } else if(chBoxMensual.isSelected()){
            
            chBoxTodoTiempo.setSelected(false);
            chBoxAnual.setSelected(false);
            chBoxSemanal.setSelected(false);
            chBoxRango.setSelected(false);
            fechaInicial.setDisable(true);
            fechaFinal.setDisable(true);
            
        } else if(chBoxSemanal.isSelected()){
            
            chBoxTodoTiempo.setSelected(false);
            chBoxAnual.setSelected(false);
            chBoxMensual.setSelected(false);
            chBoxRango.setSelected(false);
            fechaInicial.setDisable(true);
            fechaFinal.setDisable(true);
            
        } else if(chBoxRango.isSelected()){
            
            chBoxTodoTiempo.setSelected(false);
            chBoxAnual.setSelected(false);
            chBoxMensual.setSelected(false);
            chBoxSemanal.setSelected(false);
            fechaInicial.setDisable(false);
            fechaFinal.setDisable(false);
            
        }
    }
    
    public void loadVisualize(){
        List<Order> orders = oBussines.getAllOrder();

        float totalCash = 0;
        float totalCard = 0;
        float totalTransfer = 0;
        float ventaTotal = 0;

        List<Map<String, Object>> paidOrders = new ArrayList<>();

        for (Order order : orders) {
            if (order.getOrderState() == ORDER_STATE.PAID) {
                String paymentMethodString = "";

                if (order.getPaymentMethod() == PAYMENT_METHOD.EFECTIVO) {
                    paymentMethodString = "Efectivo";
                    totalCash += order.getPrice();
                } else if (order.getPaymentMethod() == PAYMENT_METHOD.TARJETA) {
                    paymentMethodString = "Tarjeta";
                    totalCard += order.getPrice();
                } else if (order.getPaymentMethod() == PAYMENT_METHOD.TRANSFERENCIA) {
                    paymentMethodString = "Transferencia";
                    totalTransfer += order.getPrice();
                }

                Map<String, Object> orderData = new HashMap<>();
                orderData.put("orderNumber", order.getOrderNumber());
                
                LocalDateTime creationDateTime = order.getCreationDate();
                Date creationDate = Date.from(creationDateTime.atZone(ZoneId.systemDefault()).toInstant());
                orderData.put("creationDate", creationDate);
                
                orderData.put("price", order.getPrice());
                orderData.put("paymentMethod", paymentMethodString);

                paidOrders.add(orderData);
            }
        }
        
        ventaTotal = totalCash + totalCard + totalTransfer;
        
        try{
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/jasperReport/VentasPeriodo.jasper"));
            
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Efectivo", totalCash);
            parameters.put("Tarjeta", totalCard);
            parameters.put("Transferencia", totalTransfer);
            parameters.put("VentaTotal", ventaTotal);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(paidOrders);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            
            JasperViewer.viewReport(jasperPrint, false);

            
        } catch (JRException e) {
            e.printStackTrace();
        }
        

    }
    

    @FXML
    private void visualizarOption(MouseEvent event) {
        loadVisualize();
    }

    @FXML
    private void imprimirOption(MouseEvent event) {
    }
}
