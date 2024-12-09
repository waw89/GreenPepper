/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.BusinessProduct;
import business.OrderBusiness;
import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.Product;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class EditOrderProductsController implements Initializable {

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

    /*
    * Objects that needs the controller to do all the stuff
     */
    Order order;

    MainPageController mainPageController;

    List<ProductOrder> poList = new ArrayList<>();

    List<EditProductAddedController> productAddedNodes = new ArrayList<>();

    EditProductAddedController editProductAddedController;

    OrderBusiness oBusiness = new OrderBusiness();

    List<ProductOrder> productsAddedToSummary = new ArrayList<>();

    int productRepeatedAmount = 0;
    
    float priceRepeatedProduct = 0;
    
    float cummulativeProductPrice = 0;

    BusinessProduct prodBusiness = new BusinessProduct();

    ObservableList<IndividualProduct> foodList;

    ObservableList<IndividualProduct> drinkList;

    ObservableList<IndividualProduct> extrasList;

    FilteredList<IndividualProduct> filter;

    EditProductItemController editProductItemController;
    @FXML
    private Label orderNumber;

    /*
    * Getters & Setters
     */
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        if (this.order != null) {
            loadProductSummary();
        }

    }

    public MainPageController getMainPageController() {
        return mainPageController;
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    public EditProductAddedController getEditProductAddedController() {
        return editProductAddedController;
    }

    public void setEditProductAddedController(EditProductAddedController editProductAddedController) {
        this.editProductAddedController = editProductAddedController;
    }

    public EditProductItemController getEditProductItemController() {
        return editProductItemController;
    }

    public void setEditProductItemController(EditProductItemController editProductItemController) {
        this.editProductItemController = editProductItemController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        foodList = FXCollections.observableArrayList();
        List<IndividualProduct> products = prodBusiness.getAllFoods();
        foodList.addAll(products);
        filter = new FilteredList(foodList, p -> true);
        for (Product product : foodList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditProductCard.fxml"));
                AnchorPane productCard = loader.load();
                EditProductCardController cardController = loader.getController();
                cardController.setEopController(this);
                cardController.setTxtProductName(product.getName());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(1);

            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void cleanFields(MouseEvent event) {
    }

    @FXML
    private void save(MouseEvent event) throws Exception {
        if(!poList.isEmpty()){
             for (EditProductAddedController paController : productAddedNodes) {
                for (EditProductItemController piController : paController.getProductItemNodes()) {
                    editProductItemController.getProductDetails(piController);
                }
            }
            Iterator<ProductOrder> iterator = poList.iterator();
            while (iterator.hasNext()) {
                ProductOrder po = iterator.next();
                if (po.getPRODUCT_SIZE() == null || po.getDetails() == null) {
                    iterator.remove(); // Elimina de forma segura el elemento actual.
                }
            }
            oBusiness.deleteAllProductsFromOrder(order);
            this.order.setProducts(poList);
            oBusiness.editOrder(this.order);
            showOrderEditedConfirmation();
            mainPageController.getBp().setCenter(mainPageController.getAp());
        }else{
            showEmptyPoListError();
        }
    }

    private void loadProductSummary() {
        poList.addAll(this.order.getProducts());
        this.orderNumber.setText(String.valueOf(this.order.getOrderNumber()));
        // Ordenar la lista alfabéticamente por el nombre del producto
        Collections.sort(poList, new Comparator<ProductOrder>() {
            @Override
            public int compare(ProductOrder po1, ProductOrder po2) {
                return po1.getProduct().getName().compareToIgnoreCase(po2.getProduct().getName());
            }
        });

        for (ProductOrder po : poList) {
            showExistingProductInSummary(po);
        }

    }

    private void showExistingProductInSummary(ProductOrder po) {
        boolean productExists = false;

        Iterator<ProductOrder> iterator = productsAddedToSummary.iterator();
        while (iterator.hasNext()) {
            ProductOrder poIterator = iterator.next();
            if (poIterator.getProduct().getName().equals(po.getProduct().getName())) {
                productExists = true;
            }
        }

        if (!productExists) {
            productRepeatedAmount = 0;
            priceRepeatedProduct = 0;
            int productAmount = 1;
            int j = 0;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditProductAdded.fxml"));
                AnchorPane productCell = loader.load();
                EditProductAddedController cellController = loader.getController();
                cellController.setEopController(this);
                cellController.setProductOrder(po);
                cellController.setTxtSummaryProductName(po.getProduct().getName());
                cellController.setTxtProductSummaryPrice(String.valueOf(po.getPrice()));
                priceRepeatedProduct = po.getPrice();
                cellController.setTxtAmount(String.valueOf(productAmount));
                productCell.setUserData(cellController);
                summaryContainer.getChildren().add(productCell);
                productsAddedToSummary.add(po);
                productAddedNodes.add(cellController);
                cellController.addExistentProductToListContainer(po.getPrice(), "#" + (j + 1), po.getDetails(), po.getPRODUCT_SIZE());
            } catch (IOException ex) {
                Logger.getLogger(EditOrderProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            for (Node node : summaryContainer.getChildren()) {

                EditProductAddedController cellController = (EditProductAddedController) node.getUserData();
                if (cellController.getProductOrder().getProduct().getName().equals(po.getProduct().getName())) {
                    productRepeatedAmount++;
                    priceRepeatedProduct += po.getPrice();
                    int j = productRepeatedAmount;
                    cellController.setTxtAmount(String.valueOf(productRepeatedAmount + 1));
                    cellController.setTxtProductSummaryPrice(String.valueOf(priceRepeatedProduct));

                    cellController.addExistentProductToListContainer(po.getPrice(), "#" + (j + 1), po.getDetails(), po.getPRODUCT_SIZE());

                }

            }
            lblSubtotal.setText("$" + this.order.getPrice());
            lblTotal.setText(lblSubtotal.getText());
            
        }

    }

    @FXML
    private void showFoods(MouseEvent event) {
        setSelectedButtonStyle(btnFood);
        cleanProductsList();
        foodList = FXCollections.observableArrayList();
        List<IndividualProduct> products = prodBusiness.getAllFoods();
        foodList.addAll(products);
        filter = new FilteredList(foodList, p -> true);

        for (Product product : foodList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditProductCard.fxml"));
                AnchorPane productCard = loader.load();
                EditProductCardController cardController = loader.getController();

                cardController.setEopController(this);

                cardController.setTxtProductName(product.getName());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(1);

            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void showDrinks(MouseEvent event) {
        setSelectedButtonStyle(btnDrink);
        cleanProductsList();
        drinkList = FXCollections.observableArrayList();
        List<IndividualProduct> products = prodBusiness.getAllDrinks();
        drinkList.addAll(products);
        filter = new FilteredList(drinkList, p -> true);

        for (Product product : drinkList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditProductCard.fxml"));
                AnchorPane productCard = loader.load();
                EditProductCardController cardController = loader.getController();

                cardController.setEopController(this);

                cardController.setTxtProductName(product.getName());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(1);

            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void showExtras(MouseEvent event) {
        setSelectedButtonStyle(btnExtra);
        cleanProductsList();
        extrasList = FXCollections.observableArrayList();
        List<IndividualProduct> products = prodBusiness.getAllExtras();
        extrasList.addAll(products);
        filter = new FilteredList(extrasList, p -> true);
        for (Product product : extrasList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditProductCard.fxml"));
                AnchorPane productCard = loader.load();
                EditProductCardController cardController = loader.getController();

                cardController.setEopController(this);

                cardController.setTxtProductName(product.getName());
                productContainer.getChildren().add(productCard);
                productContainer.setSpacing(1);

            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void cleanProductsList() {
        productContainer.getChildren().clear();
    }

    private void setSelectedButtonStyle(Button selectedButton) {

        List<Button> buttons = List.of(btnFood, btnDrink, btnExtra);

        for (Button button : buttons) {
            if (button.equals(selectedButton)) {

                button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 50; -fx-border-radius: 50;");
            } else {

                button.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black;");
            }
        }
    }

    @FXML
    private void search(KeyEvent event) {
        txtSearchProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Muestra todos si el campo de búsqueda está vacío
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return product.getName().toLowerCase().contains(lowerCaseFilter); // Filtra por nombre
            });

            // Actualizar el contenedor con los productos filtrados
            updateProductContainer(filter);
        });
    }

    /**
     * Método para actualizar el contenedor de productos basado en la lista
     * filtrada
     */
    private void updateProductContainer(FilteredList<IndividualProduct> filteredList) {
        productContainer.getChildren().clear(); // Limpia los productos actuales

        for (IndividualProduct product : filteredList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditProductCard.fxml"));
                AnchorPane productCard = loader.load();
                EditProductCardController cardController = loader.getController();

                // Configurar los detalles del producto
                cardController.setEopController(this);
                cardController.setTxtProductName(product.getName());

                productContainer.getChildren().add(productCard); // Añadir el producto filtrado al contenedor
                productContainer.setSpacing(10);
            } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cleanSearchBar(MouseEvent event) {
        this.txtSearchProduct.clear();
    }

    public void updateSummaryWithNewSelectedProduct(ProductOrder productSelected, int selectedAmmountOfProduct) throws IOException {
        boolean productExists = false;

         for (Node node : summaryContainer.getChildren()) {
            EditProductAddedController cellController = (EditProductAddedController) node.getUserData(); // get the information of the node and cast it to a Controller
            if (cellController.getProductOrder().getProduct().getName().equals(productSelected.getProduct().getName())) { // verify if the selectedProduct is equal to the node in turn in the iteration
                priceRepeatedProduct += productSelected.getPrice();
                int currentAmount = Integer.parseInt(cellController.getTxtAmount());
                int newAmount = currentAmount + selectedAmmountOfProduct;
                cellController.setTxtAmount(String.valueOf(newAmount));
                cellController.setTxtProductSummaryPrice(String.valueOf(String.valueOf(priceRepeatedProduct + oBusiness.calculateCost(order)))); // QUIZAS SEA CONVENIENTE HACER UN METODO SOLO PARA CALCULAR EL NUEVO PRECIO
                productExists = true;

                int j = currentAmount + 1;
                for (int i = 0; i < selectedAmmountOfProduct; i++) {
                    cellController.addProductToListContainer(productSelected.getPrice(), "#" + j);
                    j++;
                    productSelected.setOrder(order);
                    poList.add(productSelected);
                }
                break;
            }
        }

        if (!productExists) {
            priceRepeatedProduct = 0;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditProductAdded.fxml"));
            AnchorPane productCell = loader.load();
            EditProductAddedController cellController = loader.getController();
            cellController.setEopController(this);
            cellController.setProductOrder(productSelected);
            cellController.setTxtSummaryProductName(productSelected.getProduct().getName());
            cellController.setTxtProductSummaryPrice(String.valueOf(productSelected.getProduct().getPrice() * selectedAmmountOfProduct));
            cellController.setTxtAmount(String.valueOf(selectedAmmountOfProduct));
            productCell.setUserData(cellController);
            summaryContainer.getChildren().add(productCell);
            productAddedNodes.add(cellController);
            int j = 1;
            for (int i = 0; i < selectedAmmountOfProduct; i++) {
                cellController.addProductToListContainer(productSelected.getPrice(), "#" + j);
                j++;
                productSelected.setOrder(order);
                poList.add(productSelected);
            }
        }

        order.setProducts(poList);
        lblSubtotal.setText("$" + oBusiness.calculateCost(order));
        lblTotal.setText(lblSubtotal.getText());
        order.setPrice(oBusiness.calculateCost(order));
    }

   public void removeItemFromPoList(ProductOrder productOrder) {
        poList.remove(productOrder);
        this.order.setProducts(poList);
    }

    public void addItemToPoList(ProductOrder productOrder) {
         poList.add(productOrder);
        this.order.setProducts(poList);
    }
    
      public void updateTotalPrice() {
        float total = 0;
        for (Node node : summaryContainer.getChildren()) {
            EditProductAddedController paController = (EditProductAddedController) node.getUserData();
            total += Float.parseFloat(paController.getTxtProductSummaryPrice());
        }

        lblSubtotal.setText("$" + oBusiness.calculateCost(order));
        lblTotal.setText(lblSubtotal.getText());
        order.setPrice(oBusiness.calculateCost(order));
    }
      
       public void removeProductFromSummary(Node productNode, ProductOrder productOrder) {
        summaryContainer.getChildren().remove(productNode);
        Iterator<EditProductAddedController> nodeIterator = productAddedNodes.iterator();
        Iterator<ProductOrder> iterator = poList.iterator();
        while (iterator.hasNext()) {
            ProductOrder product = iterator.next();
            if (product.getProduct().getName().equalsIgnoreCase(productOrder.getProduct().getName())) {
                iterator.remove();
            }
        }

        while (nodeIterator.hasNext()) {
            EditProductAddedController pa = nodeIterator.next();
            if (pa.getProductOrder().getProduct().getName().equalsIgnoreCase(productOrder.getProduct().getName())) {
                nodeIterator.remove();
            }
        }

        lblSubtotal.setText("$" + oBusiness.calculateCost(order));
        lblTotal.setText(lblSubtotal.getText());
    }

    public void removeProductFromProductList(ProductOrder productOrder, Node node, int newAmount) {
        cummulativeProductPrice = 0;
        poList.remove(productOrder);
        EditProductAddedController cellController = (EditProductAddedController) node.getUserData();
        cellController.setTxtAmount(String.valueOf(newAmount));
        
        for (EditProductAddedController paController : productAddedNodes) {
                for (EditProductItemController piController : paController.getProductItemNodes()) {
                    if(piController.getProductOrder().getProduct().getName().equals(productOrder.getProduct().getName())){
                      cummulativeProductPrice += piController.getProductOrder().getPrice();
                    }
                }
            }
        
        
        
        
        cellController.setTxtProductSummaryPrice(String.valueOf(cummulativeProductPrice));
        lblSubtotal.setText("$" + oBusiness.calculateCost(order));
        lblTotal.setText(lblSubtotal.getText());
    }

    private void showEmptyPoListError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Lista de productos vacía");
        alert.setContentText("No se puede crear la orden. Agregue al menos un producto");
        alert.showAndWait();
    }
    
     private void showOrderEditedConfirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Orden editada");
        alert.setContentText("Se ha editado la orden correctamente");
        alert.showAndWait();
    }

}
