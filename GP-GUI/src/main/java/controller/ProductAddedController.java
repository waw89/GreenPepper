/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.gp.domain.ProductOrder;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class ProductAddedController implements Initializable {

    @FXML
    private Text txtSummaryProductName;
    @FXML
    private Text txtProductSummarySize;
    @FXML
    private Text txtProductSummaryPrice;
    @FXML
    private ImageView imgTrash;

    private MainPageController mainController;
    private ProductOrder productOrder;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setProductOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
    }

    public void setMainController(MainPageController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void deleteProductFromSummary(MouseEvent event) {
        Node productNode = imgTrash.getParent();
        mainController.removeProductFromSummary(productNode, productOrder);
    }

    public Text getTxtSummaryProductName() {
        return txtSummaryProductName;
    }

    public void setTxtSummaryProductName(String txtSummaryProductName) {
        this.txtSummaryProductName.setText(txtSummaryProductName);
    }

    public Text getTxtProductSummarySize() {
        return txtProductSummarySize;
    }

    public void setTxtProductSummarySize(String txtProductSummarySize) {
        this.txtProductSummarySize.setText(txtProductSummarySize);
    }

    public Text getTxtProductSummaryPrice() {
        return txtProductSummaryPrice;
    }

    public void setTxtProductSummaryPrice(String txtProductSummaryPrice) {
        this.txtProductSummaryPrice.setText(txtProductSummaryPrice);
    }
}
