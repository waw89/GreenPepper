/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.gp.gui;

import business.BusinessProduct;
import business.UserBusiness;
import com.mycompany.gp.domain.PRODUCT_TYPE;
import java.util.Scanner;

/**
 *
 * @author Raul
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        chargeUsers();
        chargeProducts();
        deployMenu();
    }
 public static void deployMenu() {
        Scanner tec = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("---------------------------");
            System.out.println("Welcome to Green Pepper!");
            System.out.println("Choose an option:");
            System.out.println("1. Add Order");
            System.out.println("2. Edit Order");
            System.out.println("3. Cancel Order");
            System.out.println("4. View Active Orders");
            System.out.println("5. View Orders History");
            System.out.println("6. Add new Product");
            System.out.println("7. Exit");
            System.out.println("---------------------------");
            
            System.out.print("I want to: ");
            option = tec.nextInt(); 

            switch (option) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    editOrder();
                    break;
                case 3:
                    cancelOrder();
                    break;
                case 4:
                    viewActiveOrders();
                    break;
                case 5:
                    viewOrderHistory();
                    break;
                case 6:
                    addProduct();
                    break;
                case 7:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option != 6);
        
        tec.close(); 
    }

    public static void addOrder() {
         Scanner tec = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("---------------------------");
            System.out.println("Add Order");
            System.out.println("Choose an option:");
            System.out.println("1. Delivery Order");
            System.out.println("2. Diner Order");
            System.out.println("3. Pick Up Order");
            System.out.println("4. Back To Main Menu");
            System.out.println("---------------------------");
            
            System.out.print("I want to: ");
            option = tec.nextInt(); 

            switch (option) {
                case 1:
                    addDeliveryOrder();
                    break;
                case 2:
                    addDinerOrder();
                    break;
                case 3:
                    addPickUpOrder();
                    break;
                case 4:
                    deployMenu();
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option != 4);
        
        tec.close(); 
    }

    public static void editOrder() {
        System.out.println("Edit Order functionality goes here.");
    }

    public static void cancelOrder() {
        System.out.println("Cancel Order functionality goes here.");
    }

    public static void viewActiveOrders() {
        System.out.println("Viewing active orders...");
    }

    public static void viewOrderHistory() {
        System.out.println("Viewing orders history...");
    }

    public static void chargeUsers(){
        UserBusiness bu = new UserBusiness();
        
        bu.chargeUsers();
        System.out.println("Connection successed!");
    }
    
    public static void chargeProducts(){
        BusinessProduct bp = new BusinessProduct();
        
        bp.chargerProducts();
        System.out.println("Products charge successllfully");
    }

    private static void addDeliveryOrder() {
    }

    private static void addDinerOrder() {
    }

    private static void addPickUpOrder() {
    }
    
    private static void addProduct(){
        Scanner tec = new Scanner(System.in);
        BusinessProduct bp = new BusinessProduct();
        String name;
        PRODUCT_TYPE type = null;
        int price, selectType;
        boolean state = false;
        
        System.out.println("Add new Product");
        System.out.println("-----------------");
        System.out.println("Product Type");
        System.out.println("-----------------");
        System.out.println("1.- FOOD");
        System.out.println("2.- DRINK");
        System.out.println("3.- EXTRA");
        System.out.println("Enter the number");
        selectType = tec.nextInt();
        
        switch (selectType) {
            case 1:
                type = type.FOOD;
                break;
            case 2:
                type = type.DRINK;
                break;
            case 3:
                type = type.EXTRA;
                break;
            default:
                break;
        }    
        
        tec.nextLine();
        System.out.println("-----------------");
        System.out.println("Name: ");
        name = tec.nextLine(); 
        
        System.out.println("Price:");
        price = tec.nextInt();
                
        bp.createProduct(type, name, price, state);
    }
}
