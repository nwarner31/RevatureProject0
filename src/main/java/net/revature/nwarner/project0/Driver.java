package net.revature.nwarner.project0;

import net.revature.nwarner.project0.views.RoleView;
import net.revature.nwarner.project0.views.RoleViewFactory;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;


public class Driver {



    public static Logger logger;

    public static void main(String[] args) {
        logger = LogManager.getLogger(Driver.class);
        Scanner input = new Scanner(System.in);
        logger.info("Hello This is a test.");
        while(true) {
            System.out.println("Please enter the role code to activate");
            String role = input.nextLine();
            if(role.equals("quit")) break;
            RoleView rv = RoleViewFactory.getRoleView(role);
            if(rv == null) System.out.println("Invalid choice");
            else rv.run();
        }
    }
}
