package view;

import controller.Controller;

public class View {

    private Controller controller;

    public View(controller.Controller controller){
        this.controller = controller;

    }

    public void runProgram() {
    	controller.startSale();
        controller.addItem(1);
        controller.addItem(2);
        controller.addItem(3, 2);
        controller.addItem(3, 3);
        controller.addItem(1);
        controller.addItem(1, 4);
        controller.addItem(3);
        controller.applyDiscount(101);
        controller.enterPayment(1000);
    }
}
