package ems;

import java.util.*;

import ems.entity.*;
import ems.control.*;

public class Main {

    public static ArrayList<Department> departmentList;
    public static ArrayList<Employee> employeeList;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        /* Get instance of DepartmentManage
        Singleton design pattern */
        DepartmentManage departmentManage = DepartmentManage.getInstance();
        
        
        EmployeeManage employeeManage = new EmployeeManage();

        String dPath = "E:\\Git Local\\employee-management\\src\\ems\\data\\department.txt";
        String ePath = "E:\\Git Local\\employee-management\\src\\ems\\data\\employee.txt";

        departmentList = (ArrayList<Department>) departmentManage.readFromFile(dPath);
        employeeList = (ArrayList<Employee>) employeeManage.readFromFile(ePath);

        boolean dFileState = true;
        boolean eFileState = true;

        String choice;
        do {
            mainMenu();
            String optionManage = sc.nextLine();
            switch (optionManage) {
                case "1" -> {

                    do {
                        departmentMenu();
                        choice = sc.nextLine();
                        switch (choice) {
                            case "1" -> {
                                System.out.println("\n__________________________________");
                                System.out.println("Add department");
                                do {
                                    Department department = departmentManage.addNew();
                                    if (department != null) {
                                        departmentList.add(department);
                                        System.out.println("Add Success");
                                        dFileState = false;
                                    } else {
                                        System.out.println("Failed");
                                    }
                                    System.out.println("Enter: "
                                            + "\t[1] add more"
                                            + "\n\t[2] to exit");
                                    System.out.print("> Enter choice: ");
                                    String tmp = sc.nextLine();

                                    if (tmp.equals("2")) {
                                        break;
                                    }
                                } while (true);

                            }

                            case "2" -> {
                                System.out.println("\n__________________________________");
                                departmentManage.viewAll(departmentList);
                            }

                            case "3" -> {
                                sc.nextLine();
                                System.out.println("\n__________________________________");
                                System.out.println("Edit department");
                                System.out.print("Enter id: ");
                                String id = sc.nextLine();
                                boolean e = departmentManage.edit(departmentList, id);

                                if (e) {
                                    System.out.println("Edit successfull");
                                    dFileState = false;
                                } else {
                                    System.out.println("Edit false");
                                }
                            }

                            case "4" -> {
                                sc.nextLine();
                                System.out.println("\n__________________________________");
                                System.out.println("Delete department ");
                                System.out.print("> Enter id of department: ");
                                String id = sc.nextLine();
                                boolean d = departmentManage.delete(departmentList, id);

                                if (d) {
                                    System.out.println("Delete successfull");
                                    dFileState = false;
                                } else {
                                    System.out.println("Delete false");
                                }
                            }

                            case "5" -> {
                                sc.nextLine();
                                System.out.println("\n__________________________________");;
                                System.out.println("Search by name");
                                System.out.print("> Enter name: ");
                                String name = sc.nextLine();
                                departmentManage.searchByName(departmentList, name);
                            }

                            case "6" -> {
                            }

                            default ->
                                System.out.println("Wrong choice !");
                        }
                    } while (!choice.equals("6"));
                }
                case "2" -> {
                    do {
                        employeeMenu();
                        choice = sc.nextLine();

                        switch (choice) {
                            case "1" -> {
                                System.out.println("\n__________________________________");
                                System.out.println("Add new employee");
                                do {
                                    Employee employee = employeeManage.addNew();
                                    if (employee != null) {
                                        employeeList.add(employee);
                                        System.out.println("Add Success");
                                        eFileState = false;
                                    } else {
                                        System.out.println("Add Failed");
                                    }

                                    System.out.println("Enter: "
                                            + "\t[1] add more"
                                            + "\n\t[2] to exit");
                                    System.out.print("> Enter choice: ");
                                    String tmp = sc.nextLine();

                                    if (tmp.equals("2")) {
                                        break;
                                    }
                                } while (true);

                            }

                            case "2" -> {
                                do {
                                    System.out.println("\n__________________________________");
                                    employeeManage.viewAll(employeeList);
                                    System.out.println("Enter: "
                                            + "\t[1] to see detail"
                                            + "\n\t[2] to exit");
                                    System.out.print("> Enter Choice: ");
                                    String tmp = sc.nextLine();

                                    if (tmp.equals("1")) {
                                        System.out.print("> Enter id: ");
                                        String id = sc.nextLine();
                                        employeeManage.viewDetail(employeeList, id);
                                    } else if (tmp.equals("2")) {
                                        break;
                                    } else {
                                        System.out.println("Wrong choice, back to menu !");
                                        break;
                                    }

                                } while (true);
                            }

                            case "3" -> {
                                sc.nextLine();
                                System.out.println("\n__________________________________");
                                System.out.println("Edit Employee");
                                System.out.print("> Enter Employee ID: ");
                                String id = sc.nextLine();
                                boolean res = employeeManage.edit(employeeList, id);
                                if (res) {
                                    System.out.println("Edit successfull");
                                    eFileState = false;
                                } else {
                                    System.out.println("Edit false");
                                }

                            }

                            case "4" -> {
                                System.out.println("\n__________________________________");
                                System.out.print("> Enter id to del employee: ");
                                sc.nextLine();
                                String id = sc.nextLine();

                                boolean res = employeeManage.delete(employeeList, id);
                                if (res) {
                                    System.out.println("Delete successfull");
                                    eFileState = false;
                                } else {
                                    System.out.println("Delete false");
                                }
                            }

                            case "5" -> {
                                System.out.println("\n__________________________________");
                                System.out.print("> Enter name: ");
                                sc.nextLine();
                                String name = sc.nextLine();
                                employeeManage.searchByName(employeeList, name);
                            }

                            case "6" -> {
                                System.out.println("\n__________________________________");
                                System.out.println("Salary Calculator ");
                                float totalSalary = employeeManage.salaryCalculator(employeeList);

                                System.out.printf("Salary of employee is %.2f$", totalSalary);
                            }
                            
                            case "7" ->
                                System.out.println("Back to main...");

                            case "8" -> {
                                int age = employeeManage.getAge(employeeList, "e2"); 
                                System.out.println(age);
                            }
                            default ->
                                System.out.println("Wrong choice !");
                        }
                    } while (!choice.equals("7"));

                }

                case "3" -> {
                    if (!dFileState) {
                        System.out.println("\n__________________________________");
                        System.out.println("Data file department is modified, save ?");
                        System.out.println("Enter: "
                                + "\t[1] to save and exit"
                                + "\n\t[2] to exit");

                        System.out.print(">Enter Choice: ");
                        String in = sc.nextLine();

                        if (in.equals("1")) {
                            boolean res = departmentManage.writeToFile(departmentList, dPath);
                            if (res) {
                                System.out.println("Write file department done");
                            } else {
                                System.out.println("Write file department false");
                            }
                        }
                    }

                    if (!eFileState) {
                        System.out.println("\n__________________________________");
                        System.out.println("Data file employee is modified, save ?");
                        System.out.println("Enter: "
                                + "\t[1] to save and exit"
                                + "\n\t[2] to exit");

                        System.out.print("> Enter Choice: ");
                        String in = sc.nextLine();

                        if (in.equals("1")) {
                            boolean res = employeeManage.writeToFile(employeeList, ePath);
                            if (res) {
                                System.out.println("Write file employee done");
                            } else {
                                System.out.println("Write file employee false");
                            }
                        }

                    }
                    System.out.println("Bye !!!");
                    System.exit(0);
                }
                default ->
                    System.out.println("Wrong choice !");
            }
        } while (true);
    }

    static void mainMenu() {
        System.out.println("\n----Employee Management System------");
        System.out.println("1. Department management");
        System.out.println("2. Employee management");
        System.out.println("3. Exit");
        System.out.print("> Enter choice: ");
    }

    static void departmentMenu() {
        System.out.println("\n-------Department management--------");
        System.out.println("1. Add new department");
        System.out.println("2. View all departments");
        System.out.println("3. Edit department");
        System.out.println("4. Delete department");
        System.out.println("5. Search by Name");
        System.out.println("6. Back to main");
        System.out.print("> Enter choice: ");
    }

    static void employeeMenu() {
        System.out.println("\n--------Employee management---------");
        System.out.println("1. Add new Employee");
        System.out.println("2. View all Employees");
        System.out.println("3. Edit Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Search by Name");
        System.out.println("6. Salary calculator");
        System.out.println("7. Back to main");
        System.out.print("> Enter choice: ");
    }
}
