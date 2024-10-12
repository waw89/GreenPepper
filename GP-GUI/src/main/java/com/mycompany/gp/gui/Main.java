/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.gp.gui;

import business.UserBusiness;
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
        
        //deployMenu();
        chargeUsers();
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
            System.out.println("6. Exit");
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
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option != 6);
        
        tec.close(); 
    }

    public static void addOrder() {
        System.out.println("Add Order functionality goes here.");
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
}
