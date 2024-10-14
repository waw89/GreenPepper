/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.gp.gui;

import business.BusinessProduct;
import business.OrderBusiness;
import business.UserBusiness;
import com.mycompany.gp.domain.DeliveryOrder;
import com.mycompany.gp.domain.DinerOrder;
import com.mycompany.gp.domain.Employee;
import com.mycompany.gp.domain.ORDER_STATE;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.PRODUCT_TYPE;
import com.mycompany.gp.domain.PickUpOrder;
import com.mycompany.gp.domain.Product;
import com.mycompany.gp.domain.ProductOrder;
import java.time.LocalDateTime;
import static java.time.temporal.TemporalQueries.zone;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Raul
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        chargeUsers();
        chargeProducts();
        deployMenu();
    }

    public static void deployMenu() throws Exception {
        Scanner tec = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("---------- Green Pepper ----------");
            System.out.println("Menú Principal");
            System.out.println("1. Agregar Pedido");
            System.out.println("2. Ver Pedidos Activos");
            System.out.println("3. Ver Historial de Pedidos");
            System.out.println("4. Salir");
            System.out.println("---------------------------");

            System.out.print("Elija una opción: ");
            option = tec.nextInt();

            switch (option) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    viewActiveOrders();
                    break;
                case 3:
                    viewOrderHistory();
                    break;
                case 4:
                    System.out.println("Saliendo... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción Invalida. Intente nuevamente: ");
            }
        } while (option != 4);

        tec.close();
    }

    public static void addOrder() throws Exception {
        Scanner tec = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("---------------------------");
            System.out.println("Agregar Orden");
            System.out.println("1. A Domicilio");
            System.out.println("2. En Comedor");
            System.out.println("3. Para Recoger");
            System.out.println("4. Volver al menú principal");
            System.out.println("---------------------------");

            System.out.print("Elija una opción: ");
            option = tec.nextInt();

            switch (option) {
                case 1:
                    showProducts();
                    addDeliveryOrder();
                    break;
                case 2:
                    showProducts();
                    addDinerOrder();
                    break;
                case 3:
                    showProducts();
                    addPickUpOrder();
                    break;
                case 4:
                    deployMenu();
                    break;
                default:
                    System.out.println("Opción Invalida. Intente nuevamente: ");
            }
        } while (option != 4);

        tec.close();
    }

    public static void editOrder(Order order) throws Exception {
        Scanner tec = new Scanner(System.in);
        int i = 0;
        int actionOption = 0;
        List<ProductOrder> products = order.getProducts();
        OrderBusiness ob = new OrderBusiness();
        for (ProductOrder product : products) {
            i++;
            System.out.println((i + 1) + ". " + products.get(i - 1).getProduct().getName());
        }

        if (order instanceof PickUpOrder) {
            System.out.println("------------------------");
            System.out.println();
            System.out.println("ORDEN PARA RECOGER");
            System.out.println("1. Editar productos");
            System.out.println("2. Editar Nombre del Cliente");
            System.out.println("3. Editar Teléfono");
            System.out.println("4. Volver atrás");
            System.out.println();
            System.out.println("------------------------");
            System.out.println("Seleccione lo que desea editar: ");
            actionOption = tec.nextInt();
            tec.nextLine();
            do {
                switch (actionOption) {
                    case 1:
                        showProducts();
                        editProductList(order);
                        break;

                    case 2:
                        System.out.println("Ingrese el nuevo nombre: ");
                        String name = tec.nextLine();
                        ((PickUpOrder) order).setCustomerName(name);
                        ob.editOrder(order);
                        System.out.println("Se ha actualizado el pedido!");
                        break;
                    case 3:

                        System.out.println("Ingrese el nuevo telefono: ");
                        String phone = tec.nextLine();
                        ((PickUpOrder) order).setCustomerPhone(phone);
                        ob.editOrder(order);
                        System.out.println("Se ha actualizado el pedido!");
                    case 4:
                        viewActiveOrders();
                        break;
                }
            } while (actionOption != 4);

        } else if (order instanceof DeliveryOrder) {
            System.out.println("------------------------");
            System.out.println();
            System.out.println("ORDEN A DOMICILIO");
            System.out.println("1. Editar productos");
            System.out.println("2. Editar Nombre del Cliente");
            System.out.println("3. Editar Teléfono");
            System.out.println("4. Editar Dirección");
            System.out.println("5. Volver atrás");

            System.out.println();
            System.out.println("------------------------");
            actionOption = tec.nextInt();
            tec.nextLine();

            do {
                switch (actionOption) {
                    case 1:
                        showProducts();
                        editProductList(order);
                        break;

                    case 2:

                        System.out.println("Ingrese el nuevo nombre: ");
                        String name = tec.nextLine();
                        ((DeliveryOrder) order).setCustomerName(name);
                        ob.editOrder(order);
                        System.out.println("Se ha actualizado el pedido!");
                        break;
                    case 3:
                        System.out.println("Ingrese el nuevo telefono: ");
                        String phone = tec.nextLine();
                        ((DeliveryOrder) order).setPhoneNumber(phone);
                        ob.editOrder(order);
                        System.out.println("Se ha actualizado el pedido!");
                        break;
                    case 4:
                        System.out.println("Ingrese la nueva dirección: ");
                        String address = tec.nextLine();
                        ((DeliveryOrder) order).setAddress(address);
                        ob.editOrder(order);
                        System.out.println("Se ha actualizado el pedido!");
                        break;
                    case 5:
                        viewActiveOrders();
                        break;
                }
            } while (actionOption != 5);

        } else if (order instanceof DinerOrder) {
            System.out.println("------------------------");
            System.out.println();
            System.out.println("ORDEN EN COMEDOR");
            System.out.println("1. Editar productos");
            System.out.println("2. Editar Nombre del Pedido");
            System.out.println("3. Volver atrás");
            System.out.println();
            System.out.println("------------------------");

            actionOption = tec.nextInt();
            tec.nextLine();
            do {
                switch (actionOption) {
                    case 1:
                        showProducts();
                        editProductList(order);
                        break;

                    case 2:
                        System.out.println("Ingrese el nuevo nombre: ");
                        String name = tec.nextLine();
                        ((DinerOrder) order).setOrderName(name);
                        ob.editOrder(order);
                        break;

                    case 3:
                        viewActiveOrders();
                        break;

                }
            } while (actionOption != 3);

        }

    }

    private static void editProductList(Order order) throws Exception {
        Scanner tec = new Scanner(System.in);
        OrderBusiness ob = new OrderBusiness();
        List<Product> products = ob.getAllProducts();
        List<Product> selectedProducts = new ArrayList<>();
        ProductOrder po = new ProductOrder();
        List<ProductOrder> poList = new ArrayList<>();
        UserBusiness ub = new UserBusiness();
        char addOption;
        do {
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i).getName());
            }
            System.out.println("Seleccione el número del producto que desea agregar: ");
            int option = tec.nextInt();
            if (option > 0 && option <= products.size()) {
                Product selectedProduct = products.get(option - 1);
                po = addProductDetails(selectedProduct, order);
                List<ProductOrder> oldList = order.getProducts();

                for (ProductOrder productOrder : oldList) {
                    poList.add(productOrder);
                }

                poList.add(po);

                if (po.getAmount() == 1) {
                    selectedProducts.add(selectedProduct);
                    System.out.println("Se agregó correctamente el producto: " + selectedProduct.getName());
                } else if (po.getAmount() > 1) {
                    for (int i = 0; i < po.getAmount(); i++) {
                        selectedProducts.add(selectedProduct);
                    }
                    System.out.println("Se agregó " + po.getAmount() + " veces " + "el producto: " + selectedProduct.getName());

                }

            } else {
                System.out.println("Opción inválida. Por favor, seleccione un número de producto válido.");
            }

            System.out.println("¿Desea agregar otro producto? (y/n)");
            addOption = tec.next().toLowerCase().charAt(0);

        } while (addOption == 'y');

        order.setProducts(poList);

        ob.editOrder(order);
        System.out.println("Se ha actualizado tu producto!");
        System.out.println("Presiona ENTER para volver al menu principal");
        tec.nextLine();
        deployMenu();
    }

    public static void cancelOrder(Order order) throws Exception {
        Scanner tec = new Scanner(System.in);
        OrderBusiness ob = new OrderBusiness();
        char decision;
        System.out.println("Estas seguro que deseas cancelar?(y/n)");
        decision = tec.next().toLowerCase().charAt(0);
        
        if(decision == 'y'){
            ob.cancelOrder(order);
            System.out.println("La orden ha sido cancelada");
        }else if(decision == 'n'){
            viewActiveOrders();
        }else{
            System.out.println("Opción invalida, digite de nuevo (y/n)");
            decision = tec.next().toLowerCase().charAt(0); 
        }
    }

    public static void viewActiveOrders() throws Exception {
        int i = 0;
        int j = 0;
        OrderBusiness ob = new OrderBusiness();
        List<Order> activeOrders = ob.getActiveOrders();
        Order orderSelected = new Order();
        Scanner tec = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("Lista de Pedidos activos:");
        for (Order order : activeOrders) {
            i++;
            if (order instanceof PickUpOrder) {
                System.out.println(i + "." + ((PickUpOrder) order).getCustomerName() + " - Para Recoger");
            } else if (order instanceof DeliveryOrder) {
                System.out.println(i + "." + ((DeliveryOrder) order).getCustomerName() + " - Para Domicilio");
            } else if (order instanceof DinerOrder) {
                System.out.println(i + "." + ((DinerOrder) order).getOrderName());
            }

        }
        System.out.println("---------------------------");

        System.out.println("Seleccione la orden que desea gestionar: ");
        int orderOption = tec.nextInt();
        if (orderOption > 0 && orderOption <= activeOrders.size()) {
            orderSelected = activeOrders.get(orderOption - 1);
            System.out.println("Orden seleccionada: No. " + activeOrders.get(orderOption - 1));
        } else {
            System.out.println("Opcion inválida. Por favor, seleccione un número de pedido valido");
        }

        List<ProductOrder> orderProducts = orderSelected.getProducts();
        int option = 0;

        do {
//            for(ProductOrder product : orderProducts ){
//                j++;
//                System.out.println(j + "." + product.getProduct().getName());
//            }
            System.out.println();
            System.out.println("Acciones:");
            System.out.println("1. Editar pedido");
            System.out.println("2. Cancelar pedido");
            System.out.println("3. Ver desglose de Orden");
            System.out.println("4. Volver al menu principal");
            System.out.println();
            System.out.println("----------------");
            System.out.println("Seleccione una acción: ");
            option = tec.nextInt();
            switch (option) {
                case 1:
                    editOrder(orderSelected);
                    break;

                case 2:
                    cancelOrder(orderSelected);
                    break;

                case 3:
                    break;

                case 4:
                    deployMenu();
                    break;

            }
        } while (option != 4);
    }

    public static void viewOrderHistory() {
        int i = 0;
        OrderBusiness ob = new OrderBusiness();
        List<Order> canceledPaidOrders = ob.getCanceledPaidOrders();
        System.out.println("---------------------------");
        System.out.println("Historial de pedidos:");
        for (Order order : canceledPaidOrders) {
            i++;
            if(order instanceof PickUpOrder){
                System.out.println(i + "." + ((PickUpOrder) order).getCustomerName() + " " + ((PickUpOrder) order).getCustomerPhone() + " - Para Recoger ");
            }else if(order instanceof DeliveryOrder){
                System.out.println(i + "." + ((DeliveryOrder) order).getCustomerName() + ((DeliveryOrder) order).getCustomerName() + " - A Domicilio");
            }else if(order instanceof DinerOrder){
                System.out.println(i + "." + ((DinerOrder) order).getOrderName() + " - En Comedor");
            }
            
        }
        System.out.println("---------------------------");
    }

    public static void chargeUsers() {
        UserBusiness bu = new UserBusiness();
        bu.chargeUsers();
    }

    public static void chargeProducts() {
        BusinessProduct bp = new BusinessProduct();
        bp.chargerProducts();
    }

    private static void addDeliveryOrder() {
    }

    private static void addDinerOrder() {
    }

    private static void addPickUpOrder() {
        Scanner tec = new Scanner(System.in);
        OrderBusiness ob = new OrderBusiness();
        List<Product> products = ob.getAllProducts();
        List<Product> selectedProducts = new ArrayList<>();
        PickUpOrder order = new PickUpOrder();
        ProductOrder po = new ProductOrder();
        List<ProductOrder> poList = new ArrayList<>();
        UserBusiness ub = new UserBusiness();
        char addOption;

        do {

            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i).getName());
            }
            System.out.println("Seleccione el número del producto que desea agregar: ");
            int option = tec.nextInt();

            if (option > 0 && option <= products.size()) {
                Product selectedProduct = products.get(option - 1);
                po = addProductDetails(selectedProduct, order);
                poList.add(po);
                if (po.getAmount() == 1) {
                    selectedProducts.add(selectedProduct);
                    System.out.println("Se agregó correctamente el producto: " + selectedProduct.getName());
                } else if (po.getAmount() > 1) {
                    for (int i = 0; i < po.getAmount(); i++) {
                        selectedProducts.add(selectedProduct);
                    }
                    System.out.println("Se agregó " + po.getAmount() + " veces " + "el producto: " + selectedProduct.getName());

                }

            } else {
                System.out.println("Opción inválida. Por favor, seleccione un número de producto válido.");
            }

            System.out.println("¿Desea agregar otro producto? (y/n)");
            addOption = tec.next().toLowerCase().charAt(0);

        } while (addOption == 'y');

        order.setProducts(poList);
        tec.nextLine();
        System.out.println("Ingrese detalles generales del pedido (opcional): ");
        String details = tec.nextLine();
        order.setDetails(details);

        System.out.println("Ingrese el nombre del cliente: ");
        String name = tec.nextLine();
        order.setCustomerName(name);

        System.out.println("Ingrese el telefono del cliente: ");
        String phone = tec.nextLine();
        order.setCustomerPhone(phone);

        order.setCreationDate(LocalDateTime.now());

        order.setORDER_STATE(ORDER_STATE.ACTIVE);

        order.setCashier((Employee) ub.findUser(2L));

        PickUpOrder orderCreated = ob.createPickUpOrder(order);

        System.out.println("Orden creada!");

        generatePickUpOrderNote(orderCreated);

    }

    private static void showProducts() {
        int i = 0;
        OrderBusiness ob = new OrderBusiness();
        List<Product> products = ob.getAllProducts();
        System.out.println("---------------------------");
        System.out.println("Lista de productos:");
        for (Product product : products) {
            i++;
            System.out.println(i + "." + product.getName());
        }
        System.out.println("---------------------------");
    }

    private static ProductOrder addProductDetails(Product selectedProduct, Order order) {
        Scanner tec = new Scanner(System.in);
        ProductOrder po = new ProductOrder();
        int amount = 0;
        po.setOrder(order);
        po.setProduct(selectedProduct);
        po.setPrice(selectedProduct.getPrice());
        System.out.println("Producto seleccionado: " + selectedProduct.getName());
        System.out.println("Ingrese la cantidad del producto: ");
        amount = tec.nextInt();
        po.setAmount(amount);
        return po;
    }

    private static void generatePickUpOrderNote(PickUpOrder order) {
        int i = 0;
        Scanner tec = new Scanner(System.in);
        List<ProductOrder> products = order.getProducts();
        System.out.println("----------------------");
        System.out.println("ORDEN PARA RECOGER");
        System.out.println("Creada por: " + order.getCashier().getName());
        System.out.println("Orden No. " + order.getOrderNumber());

        System.out.println(String.format("%-10s %-20s %-10s", "Cantidad", "Nombre", "Precio"));

        for (ProductOrder product : products) {
            double productTotalPrice = product.getAmount() * product.getPrice();
            i++;

            System.out.println(String.format("%-10d %-20s $%-10.2f", product.getAmount(), product.getProduct().getName(), productTotalPrice));
        }

        System.out.println("----------------------");
        System.out.println("Detalles: " + order.getDetails());
        System.out.println(String.format("Total: $%.2f", order.getPrice()));
        System.out.println("----------------------");
        System.out.println("Datos del cliente");
        System.out.println("Nombre: " + order.getCustomerName());
        System.out.println("Telefono: " + order.getCustomerPhone());
        System.out.println("Presiona ENTER para volver al menu principal");
        tec.nextLine();

    }

//    private static void addProduct() {
//        Scanner tec = new Scanner(System.in);
//        BusinessProduct bp = new BusinessProduct();
//        String name;
//        PRODUCT_TYPE type = null;
//        int price, selectType;
//        boolean state = false;
//
//        System.out.println("Add new Product");
//        System.out.println("-----------------");
//        System.out.println("Product Type");
//        System.out.println("-----------------");
//        System.out.println("1.- FOOD");
//        System.out.println("2.- DRINK");
//        System.out.println("3.- EXTRA");
//        System.out.println("Enter the number");
//        selectType = tec.nextInt();
//
//        switch (selectType) {
//            case 1:
//                type = type.FOOD;
//                break;
//            case 2:
//                type = type.DRINK;
//                break;
//            case 3:
//                type = type.EXTRA;
//                break;
//            default:
//                break;
//        }
//
//        tec.nextLine();
//        System.out.println("-----------------");
//        System.out.println("Name: ");
//        name = tec.nextLine();
//
//        System.out.println("Price:");
//        price = tec.nextInt();
//
//        bp.createProduct(type, name, price, state);
//    }
}
