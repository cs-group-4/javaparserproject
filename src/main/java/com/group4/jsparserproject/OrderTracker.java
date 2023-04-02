/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group4.jsparserproject;
import java.util.*;
/**
 *
 * @author PC USER
 */
public class OrderTracker {

    public static void main(String[]args){
		UserInterface ui = new UserInterface();
		ui.home();

	}
}

//Once the admin logs in, they will be able to make the needed changes to the customers tracking page.

// Gain, Thando and Mwizas class
class UserInterface extends UpdateTracking{
	Scanner tracker = new Scanner(System.in);
        public void seperator(){ // Sperates the different pages
            System.out.println("----------------------------------------------");
        }
	public void home(){
                seperator(); 
//What a user see the moment they open the tracking site

		System.out.println("Home Page");
		System.out.println("Press 1 to track your package");
		System.out.println("Press 2 for Admin Login");
		System.out.print("Enter here: ");

//prompt user/admin to input a value 
		int input = tracker.nextInt();

//depending on what value the user/admin enters, this is what will be displayed
                // switch statements not allowed in javaparser
                if(input == 1){
                    trackProductScreen();
                } else if(input == 2){
                    admin();
                } else{
                    System.out.println("Invalid Input, try again.");
                }
	}
	//user is able to see and track the progress of their product
	public void trackProductScreen(){
                seperator(); 
                System.out.println("Tracking Page");
		System.out.println("Enter your tracking code,or enter 0 to go back to previous screen");
		long code = tracker.nextInt();
		//check if code is valid
                String shippingStage;
                if(code == 0){
                    home();
                }
		if(isValid(code)){
                    int currentShippingStage = getShippingStage();
                    if(currentShippingStage == 1){
                        shippingStage = "Loading";
                    } else if(currentShippingStage == 2){
                        shippingStage = "In transit";
                    } else{
                        shippingStage = "Delivered";
                    }

                    trackingDetailsScreen(getCustomerName(), getOrderNumber(), shippingStage);
		} else{
			System.out.println("Code doesnt exist");
			trackProductScreen();
		}
		
		
	}
	//allows the admin to make updates and changes concerning the shipment
	public void admin(){
            seperator(); 
            System.out.println("Admin Page");
//		System.out.println("Enter Password");
//		String password = tracker.next();
		//updates
		
//		int trackingCode = tracker.nextInt();
                System.out.println("Customer name: " + getCustomerName());
		System.out.println("Order numer: " + getOrderNumber());

                String shipStage;
                if(getShippingStage() == 1){
                        shipStage = "Loading";
                    } else if(getShippingStage() == 2){
                        shipStage = "In transit";
                    } else{
                        shipStage = "Delivered";
                    }
		System.out.println("Shipping stage: " + shipStage);
                seperator(); 
		System.out.println("To update the shipping stage, ");
		System.out.println("Press 1 for loading");
		System.out.println("Press 2 for in transit");
		System.out.println("Press 3 for delivered");

		System.out.print("Enter 0 to go back to previous screen: ");
		int back = tracker.nextInt();
		if(back == 0){
			home();
		}else {
			setShippingStage(back);
			admin();
		}

	}
	//displays the details of the customer as well as their shipping details
	public void trackingDetailsScreen(String name, String orderNumber, String shippingStage){
                seperator(); 
		System.out.printf("Fullname: %s %n Order Number: %s %n Shipping Stage: %s", name, orderNumber, shippingStage);
                System.out.println("");
		System.out.println("Enter 0 to go back to previous screen: ");
		int back = tracker.nextInt();
		if(back == 0){
			trackProductScreen();
		}
	}
}

// Mwiza and Emmanuels class
class UpdateTracking extends TrackingBackEnd{
    int shippingStage = 1;
    
    public void setShippingStage(int stage){
        shippingStage = stage;
    }
     public int getShippingStage(){
        return this.shippingStage;
    }
    
    
}

// Gabby and Tabas class
class TrackingBackEnd {
    String customerName = "Jane Doe";    
    String orderNumber = "ABC12345";

    long trackingCode = 123456;
    boolean valid = false;
    public boolean isValid(long code){
        if(code == trackingCode){
            valid = true;
        }
        return valid;
    }
    public  String getCustomerName(){
        return this.customerName;
    }
    public String getOrderNumber(){
        return this.orderNumber;
    }
}
