package ems.control;

import ems.entity.Department;
import ems.entity.Employee;
import ems.entity.FulltimeEmployee;
import ems.entity.ParttimeEmployee;
import ems.interfaces.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeManage implements IActions<Employee>, IEServices {

    Scanner sc = new Scanner(System.in);
    DepartmentManage departmentManage = new DepartmentManage();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    // Check whether object exist return index of object
    @Override
    public int checkExist(ArrayList<Employee> objectList, String id) {
        for (int i = 0; i < objectList.size(); i++) {
            Employee employee = objectList.get(i);
            if (employee.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Employee addNew() {
        Employee newEmployee;
        System.out.println("Enter: "
                + "\t[f] for add fulltime"
                + "\n\t[p] for add parttime");

        System.out.print("> Choice: ");
        String typeChoice = sc.nextLine();

        switch (typeChoice.trim().toLowerCase()) {
            case "f" -> {
                System.out.println("Add new fulltime employee");
                newEmployee = new FulltimeEmployee();
                newEmployee.enterInfor(sc);
                return newEmployee;
            }
            case "p" -> {
                System.out.println("Add new fulltime employee");
                newEmployee = new ParttimeEmployee();
                newEmployee.enterInfor(sc);
                return newEmployee;
            }
            default -> {
                System.out.println("Wrong choice !");
            }
        }
        return null;
    }

    @Override
    public boolean delete(ArrayList<Employee> objectList, String id) {
        int index = checkExist(objectList, id);
        if (index > -1) {
            objectList.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public void searchByName(ArrayList<Employee> objectList, String name) {
        for (int i = 0; i < objectList.size(); i++) {
            Employee department = objectList.get(i);
            if (department.getName().equals(name)) {
                System.out.println(department.toString());
                return;
            }
        }
        System.out.println("No Employee called: " + name);
    }

    @Override
    public void viewAll(ArrayList<Employee> objectList) {
        System.out.println("\tView all employees");
        System.out.println("\tID\tName\tDoB\tAddress\tPhone\tDepartment\tSalary\tSalary2");
        objectList.forEach(employee -> {
            System.out.println(employee.toString());
        });
    }

    @Override
    public boolean edit(ArrayList<Employee> objectList, String id) {
        System.out.println("Edit employee");
        int index = checkExist(objectList, id);

        if (index > -1) {
            sc.nextLine();
            Employee employee = objectList.get(index);
            String tmp;

            System.out.print("> Enter Name: ");
            tmp = sc.nextLine();
            if (tmp.trim().length() > 0) {
                employee.setName(tmp);
            }

            System.out.print("> Enter DoB: ");
            tmp = sc.nextLine();
            if (tmp.trim().length() > 0) {
                try {
                    Date date = dateFormat.parse(tmp);
                    employee.setDob(date);
                } catch (ParseException ex) {
                    Logger.getLogger(EmployeeManage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.print("> Enter address: ");
            tmp = sc.nextLine();
            if (tmp.trim().length() > 0) {
                employee.setAddress(tmp);
            }

            System.out.print("> Enter phone: ");
            tmp = sc.nextLine();
            if (tmp.trim().length() > 0) {
                employee.setPhone(Integer.parseInt(tmp));
            }

            System.out.print("> Enter department id: ");
            tmp = sc.nextLine();
            if (tmp.trim().length() > 0) {
                Department dp = departmentManage.getObjectByID(tmp);
                if (dp != null) {
                    employee.setDepartment(dp);
                } else {
                    System.out.println("Wrong department id");
                }
            };

            if (employee instanceof ParttimeEmployee) {
                System.out.print("> Enter work hours: ");
                tmp = sc.nextLine();
                if (tmp.trim().length() > 0) {
                    float wh = Float.parseFloat(tmp);
                    ((ParttimeEmployee) employee).setWord_hours(wh);
                }

                System.out.print("> Enter work price per hour: ");
                tmp = sc.nextLine();
                if (tmp.trim().length() > 0) {
                    float pph = Float.parseFloat(tmp);
                    ((ParttimeEmployee) employee).setPrice_per_hour(pph);
                }
                return true;

            } else if (employee instanceof FulltimeEmployee) {
                System.out.print("> Enter Basic Salary: ");
                tmp = sc.nextLine();
                if (tmp.trim().length() > 0) {
                    ((FulltimeEmployee) employee).setBasic_salary(Float.parseFloat(tmp));
                }

                System.out.print("> Enter Coefficients Salary: ");
                tmp = sc.nextLine();
                if (tmp.trim().length() > 0) {
                    ((FulltimeEmployee) employee).setCoefficients_salary(Float.parseFloat(tmp));
                }
                return true;

            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public float salaryCalculator(ArrayList<Employee> objectList, String employee_id) {
        float totalSalary = -1;
        int indexEmployee = checkExist(objectList, employee_id);
        if (indexEmployee > -1) {
            Employee employee = objectList.get(indexEmployee);
            if (employee instanceof FulltimeEmployee) {
                totalSalary = ((FulltimeEmployee) employee).getBasic_salary() * ((FulltimeEmployee) employee).getCoefficients_salary();
                return totalSalary;
            } else {
                totalSalary = ((ParttimeEmployee) employee).getWord_hours() * ((ParttimeEmployee) employee).getPrice_per_hour();
                return totalSalary;
            }
        }
        return totalSalary;
    }
}
