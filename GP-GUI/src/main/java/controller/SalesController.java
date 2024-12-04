/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
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
import java.util.ArrayList;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SalesController implements Initializable {

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
    //List<Order> orders = oBussines.getAllOrder();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        List<Order> cashOrders = new ArrayList<>();
        List<Order> cardOrders = new ArrayList<>();
        List<Order> transferOrders = new ArrayList<>();
        
        float totalCash = 0;
        float totalCard = 0;
        float totalTransfer = 0;

        for (Order order : orders) {
            
            if (order.getPaymentMethod() == PAYMENT_METHOD.EFECTIVO) {
                cashOrders.add(order);
                totalCash += order.getPrice();
            } else if (order.getPaymentMethod() == PAYMENT_METHOD.TARJETA) {
                cardOrders.add(order);
                totalCard += order.getPrice();
            } else if (order.getPaymentMethod() == PAYMENT_METHOD.TRANSFERENCIA) {
                transferOrders.add(order);
                totalCard += order.getPrice();
            }
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
