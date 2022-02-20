package net.revature.nwarner.project0.utility;

import java.util.Scanner;

public class InputGatherer {

    private static final Scanner input = new Scanner(System.in);

    public static String getStringInput(String prompt) {
        while(true) {
            System.out.println(prompt);
            String inputString = input.nextLine();
            if(!inputString.isEmpty()) {
                return inputString;
            } else {
                System.out.println("No input entered");
            }
        }
    }

    public static String getStringInput(String prompt, String defaultValue) {
        System.out.println(prompt);
        String inputString = input.nextLine();
        if(inputString.isEmpty()) {
            return defaultValue;
        } else {
            return inputString;
        }
    }

    public static int getIntInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            String inputString = input.nextLine();
            if(!inputString.isEmpty()) {
                try {
                    int intValue = Integer.parseInt(inputString);
                    if (intValue < 0) {
                        System.out.println("Value must be 0 or larger");
                    } else {
                        return intValue;
                    }
                } catch(NumberFormatException nfe) {
                    System.out.println("Not a valid number");
                }
            } else {
                System.out.println("No input entered");
            }
        }
    }

    public static int getIntInput(String prompt, int defaultValue) {
        while(true) {
            System.out.println(prompt);
            String inputString = input.nextLine();
            if(inputString.isEmpty()) return defaultValue;
            else {
                try{
                    int intValue = Integer.parseInt(inputString);
                    if (intValue < 0) {
                        System.out.println("Value must be 0 or larger");
                    } else {
                        return intValue;
                    }
                }catch (NumberFormatException nfe) {
                    System.out.println("Not a valid number");
                }
            }
        }
    }

    public static int getIntInput(int maxValue, String prompt) {
        while (true) {
            int value = getIntInput(prompt);
            if (value > maxValue) {
                System.out.println("Value too large");
            } else {
                return value;
            }
        }
    }

    public static boolean isYes(String inputString) {
        return inputString.equalsIgnoreCase("y") || inputString.equalsIgnoreCase("yes");
    }

    public static boolean isNo(String inputString) {
        return inputString.equalsIgnoreCase("n") || inputString.equalsIgnoreCase("no");
    }

    public static boolean isLogout(String inputString) {
        return inputString.equalsIgnoreCase("logout");
    }
}
