package ems;

import java.util.*;

import ems.entity.*;
import ems.control.*;

public class Main {

    public static ArrayList<Department> departmentList;
    public static ArrayList<Employee> employeeList;

    public static void main(String[] args) {
        departmentList = new ArrayList<>();
        employeeList = new ArrayList<>();
        DepartmentManage departmentManage = new DepartmentManage();
        EmployeeManage employeeManage = new EmployeeManage();

        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            mainMenu();

            int optionManage = sc.nextInt();
            switch (optionManage) {
                case 1 -> {
                    do {
                        departmentMenu();
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1 -> {
                                Department department = departmentManage.addNew();
                                if (department != null) {
                                    departmentList.add(department);
                                    System.out.println("Success");
                                    break;
                                }
                                System.out.println("Failed");

                            }

                            case 2 -> departmentManage.viewAll(departmentList);

                            case 3 -> {
                                System.out.println("\tEdit department: ");
                                sc.nextLine();
                                String id = sc.nextLine();
                                System.out.println(departmentManage.edit(departmentList, id) ? "Success" : "Fail");
                            }

                            case 4 -> {
                                System.out.print("> Enter id to del department");
                                sc.nextLine();
                                String id = sc.nextLine();
                                System.out.println(departmentManage.delete(departmentList, id) ? "Success" : "Fail");

                            }

                            case 5 -> {
                                sc.nextLine();
                                System.out.println("Search by name");
                                System.out.print("> Enter name: ");
                                String name = sc.nextLine();
                                departmentManage.searchByName(departmentList, name);
                            }

                            case 6 -> System.out.println("Bai bai");

                            default -> System.out.println("Wrong choice !");
                        }
                    } while (choice < 6);
                }
                case 2 -> {
                    do {
                        employeeMenu();
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1 -> {
                                sc.nextLine();
                                Employee employee = employeeManage.addNew();
                                if (employee != null) {
                                    employeeList.add(employee);
                                    System.out.println("Success");
                                    break;
                                }
                                System.out.println("Failed");
                            }

                            case 2 -> employeeManage.viewAll(employeeList);

                            case 3 -> {
                                sc.nextLine();
                                System.out.println("Edit Employee");
                                System.out.print("> Enter Employee ID: ");
                                String id = sc.nextLine();
                                System.out.println(employeeManage.edit(employeeList, id) ? "Success" : "Fail");

                            }

                            case 4 -> {
                                System.out.print("> Enter id to del employee: ");
                                sc.nextLine();
                                String id = sc.nextLine();
                                System.out.println(employeeManage.delete(employeeList, id) ? "Success" : "Fail");
                            }

                            case 5 -> {
                                System.out.print("> Enter name: ");
                                sc.nextLine();
                                String name = sc.nextLine();
                                employeeManage.searchByName(employeeList, name);
                            }

                            case 6 -> {
                                sc.nextLine();
                                System.out.println("Salary Calculator: ");
                                System.out.print("> Enter id employee: ");
                                String id = sc.nextLine();
                                float totalSalary = employeeManage.salaryCalculator(employeeList, id);
                                System.out.printf("Salary of employee %s is %f", id, totalSalary);
                            }

                            case 7 -> System.out.println("Bai bai");

                            default -> System.out.println("Wrong choice !");
                        }
                    } while (choice != 7);

                }
                case 3 -> {
                    System.out.println("Bye");
                    System.exit(0);
                }
                default -> System.out.println("Wrong choice !");
            }
        } while (true);
    }

    static void mainMenu() {
        System.out.println("Employee Management");
        System.out.println("1. Department management");
        System.out.println("2. Employee management");
        System.out.println("3. Exit");
        System.out.print(">Enter choice: ");
    }

    static void departmentMenu() {
        System.out.println("++++++Department management++++++");
        System.out.println("1. Add new department");
        System.out.println("2. View all departments");
        System.out.println("3. Edit department");
        System.out.println("4. Delete department");
        System.out.println("5. Search by Name");
        System.out.println("6. Back to main");
        System.out.print(">Enter choice: ");
    }

    static void employeeMenu() {
        System.out.println("++++++Employee management++++++");
        System.out.println("1. Add new Employee");
        System.out.println("2. View all Employees");
        System.out.println("3. Edit Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Search by Name");
        System.out.println("6. Salary calculator");
        System.out.println("7. Back to main");
        System.out.print(">Enter choice: ");
    }
}
