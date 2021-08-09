package ems;

import java.util.Scanner;

public class Test {
    static String startBox = String.format("%s %39s %s\n", "\u250c", " ", "\u2510").replace(" ", "\u2500");;
    static String dividerBox = String.format("%s %39s %s\n", "\u250c", " ", "\u2510").replace(" ", "\u2500");;
    static String endBox = String.format("%s %39s %s\n", "\u250c", " ", "\u2510").replace(" ", "\u2500");;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mainMenu();

        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> departmentMenu();
            case 2 -> employeeMenu();
            default -> System.out.println("Wrong");
        }
    }

    static void mainMenu() {
        System.out.print(startBox);
        System.out.printf("%-17s %s %17s\n", "\u2502", "Program", "\u2502");
        System.out.print(dividerBox);
        System.out.printf("%s %-39s %s\n", "\u2502", "1. Department management", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "2. Employee management", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "3. Exit", "\u2502");
        System.out.print(endBox);
        System.out.print(">Enter choice: ");
    }

    static void departmentMenu() {
        System.out.print(startBox);
        System.out.printf("%-10s %s %10s\n", "\u2502", "Department management", "\u2502");
        System.out.print(dividerBox);
        System.out.printf("%s %-39s %s\n", "\u2502", "1. Add new department", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "2. View all departments", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "3. Edit department", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "4. Delete department", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "5. Search by name", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "6. Exit", "\u2502");
        System.out.print(endBox);
        System.out.print(">Enter choice: ");
    }

    static void employeeMenu() {
        System.out.print(startBox);
        System.out.printf("%-10s %s %12s\n", "\u2502", "Employee management", "\u2502");
        System.out.print(dividerBox);
        System.out.printf("%s %-39s %s\n", "\u2502", "1. Add new Employee", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "2. View all Employee", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "3. Edit Employee", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "4. Delete Employee", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "5. Search by name", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "6. Salary Calculator", "\u2502");
        System.out.printf("%s %-39s %s\n", "\u2502", "7. Exit", "\u2502");
        System.out.print(endBox);
        System.out.print(">Enter choice: ");
    }
}
