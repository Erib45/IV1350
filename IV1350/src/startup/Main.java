package startup;

import controller.Controller;
import integration.Register;
import view.View;

public class Main {

    public static void main(String[] args) {
    	Register register = new Register();
	    Controller controller = new Controller(register);
        View view = new View(controller);
        view.runProgram();
        
    }
}
