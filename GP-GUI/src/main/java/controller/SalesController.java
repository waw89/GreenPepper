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
    List<Order> orders = oBussines.getAllOrder();

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
    

    @FXML
    private void visualizarOption(MouseEvent event) {
    
        
    
    
    
    
    
    }

    @FXML
    private void imprimirOption(MouseEvent event) {
    }
    
}
