package net.revature.nwarner.project0;

import net.revature.nwarner.project0.utility.InputGatherer;
import net.revature.nwarner.project0.views.RoleView;
import net.revature.nwarner.project0.views.RoleViewFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;


public class Driver {



    public static Logger logger;

    public static void main(String[] args) {
        logger = LogManager.getLogger(Driver.class);
        //logger.info("Hello This is a test.");
        while(true) {
            String role = InputGatherer.getStringInput("Please enter the role code to activate");
            if(role.equals("quit")) break;
            RoleView rv = RoleViewFactory.getRoleView(role);
            if(rv == null) System.out.println("Invalid choice");
            else rv.run();
        }
    }
}
