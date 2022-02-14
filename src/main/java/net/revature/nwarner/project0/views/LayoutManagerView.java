package net.revature.nwarner.project0.views;

import net.revature.nwarner.project0.collections.MyArrayList;
import net.revature.nwarner.project0.models.Section;

import java.util.Scanner;

public class LayoutManagerView implements RoleView {

    private final Scanner input;
    private MyArrayList<Section> sections;
    private Section currentSection;

    public LayoutManagerView() {
        input = new Scanner(System.in);
    }

    private void createSection() {
        System.out.println("Please enter the area:");
        String area = input.nextLine();
        System.out.println("Please enter the aisle:");
        String aisle = input.nextLine();
        System.out.println("Please enter the section");
        String section = input.nextLine();
        System.out.println("Please enter the type of section this is: ");
        String type = input.nextLine();
        Section s = new Section(area, aisle, section, type);
        System.out.println("Do you want to set this to be the current section you are working on?");
        String answer = input.nextLine();
        if (answer.equals("y") || answer.equals("yes")) currentSection = s;
    }

    private void setCurrent() {

    }

    private void viewCurrent() {
        if(currentSection == null) {
            System.out.println("No current section assigned");
        } else {
            System.out.println(currentSection);
        }
    }

    @Override
    public void run() {
        System.out.println("Welcome Layout Manager.");

        while(true) {
            System.out.println("Would you like to create section");
            String action = input.nextLine();
            switch (action) {
                case "create section":
                    createSection();
                    break;
                case "view current":
                    viewCurrent();
                    break;
                case "logout":
                    return;
            }
        }
    }
}
