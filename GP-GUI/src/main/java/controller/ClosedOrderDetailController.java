package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import business.OrderBusiness;
import com.mycompany.gp.domain.DinerOrder;
import com.mycompany.gp.domain.ORDER_STATE;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ClosedOrderDetailController implements Initializable {

    @FXML
    private Text txtOpenDate;
    @FXML
    private Text txtIdOrder;
    @FXML
    private VBox orderContainer;
    @FXML
    private Text txtTotalPrice;
    @FXML
    private Text txtOrderName;
    @FXML
    private Button btnReopen;

    OrderBusiness oBusiness = new OrderBusiness();

    private List<ProductOrder> productOrderList;
    private List<ProductOrder> productOrder = oBusiness.getAllProductOrder();
    private DinerOrder dinerOrder;
    @FXML
    private ImageView btnBack;

    MainPageController mainPageController;

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productOrder = oBusiness.getAllProductOrder();

        if (dinerOrder != null) {
            setOrderDetails(dinerOrder);
        }

    }

    public void setOrderDetails(DinerOrder order) {
        this.dinerOrder = order;
        loadProductsOrder();
    }

    private void loadProductsOrder() {
        if (dinerOrder == null) {
            return;
        }

        txtOpenDate.setText(dinerOrder.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        txtIdOrder.setText("#" + dinerOrder.getOrderNumber());
        txtTotalPrice.setText("$" + dinerOrder.getPrice());
        txtOrderName.setText(dinerOrder.getOrderName());

        productOrderList = dinerOrder.getProducts();

        Map<String, Integer> productCount = new HashMap<>();

        for (ProductOrder orderProduct : productOrderList) {
            String productName = orderProduct.getProduct().getName();
            productCount.put(productName, productCount.getOrDefault(productName, 0) + 1);
        }

        orderContainer.getChildren().clear();

        for (Map.Entry<String, Integer> entry : productCount.entrySet()) {
            String productName = entry.getKey();
            int count = entry.getValue();

            float price = productOrderList.stream().filter(p -> p.getProduct().getName().equals(productName)).findFirst().map(ProductOrder::getPrice).orElse(0.0f);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductOrder.fxml"));
                Parent orderCard = loader.load();
                ProductOrderController cardController = loader.getController();

                cardController.setTxtProduct(count + " x " + productName);
                cardController.setTxtPrice("$" + (count * price));

                orderContainer.getChildren().add(orderCard);
                orderContainer.setSpacing(0);

            } catch (IOException ex) {
                Logger.getLogger(ClosedOrderDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Text getTxtOpenDate() {
        return txtOpenDate;
    }

    public void setTxtOpenDate(Text txtOpenDate) {
        this.txtOpenDate.setText(txtOpenDate.getText());
    }

    public Text getTxtIdOrder() {
        return txtIdOrder;
    }

    public void setTxtIdOrder(Text txtIdOrder) {
        this.txtIdOrder.setText(txtIdOrder.getText());
    }

    public VBox getOrderContainer() {
        return orderContainer;
    }

    public void setOrderContainer(VBox orderContainer) {
        this.orderContainer = orderContainer;
    }

    public Text getTxtTotalPrice() {
        return txtTotalPrice;
    }

    public void setTxtTotalPrice(Text txtTotalPrice) {
        this.txtTotalPrice.setText(txtTotalPrice.getText());
    }

    public Text getTxtOrderName() {
        return txtOrderName;
    }

    public void setTxtOrderName(Text txtOrderName) {
        this.txtOrderName.setText(txtOrderName.getText());
    }

    @FXML
    private void OptionReopenOrder(MouseEvent event) {
        try {
            boolean openOrder = askForOpen();
            if (openOrder) {
                dinerOrder.setORDER_STATE(ORDER_STATE.ACTIVE);
                oBusiness.EditDataDiner(dinerOrder);
                showOrderOpenedConfirmation();
                mainPageController.loadPage("OrdersHistory");
            }
        } catch (Exception e) {
            Logger.getLogger(OrderCardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    private void OptionBack(MouseEvent event) {

        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();

    }

    private void showOrderOpenedConfirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Orden reabierta");
        alert.setContentText("Se ha reabierto la orden correctamente");
        alert.showAndWait();
    }

    private boolean askForOpen() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Reabrir la orden");
        alert.setContentText("¿Desea volver a abrir la orden?");

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }

}
