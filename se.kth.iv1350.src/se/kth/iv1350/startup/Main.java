package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.controller.OperationFailedException;
import se.kth.iv1350.integration.DatabaseConnectionErrorException;
import se.kth.iv1350.view.View;

/**
 * Main starts up the program by starting controller and view
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class Main {

    public static void main(String[] args) throws OperationFailedException {
	    Controller controller = new Controller();
        View view = new View(controller);
        view.runProgram();
        
    }
}
