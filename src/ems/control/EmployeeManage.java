package ems.control;

import static ems.Main.departmentList;
import static ems.Main.employeeList;
import ems.check.Check;
import ems.entity.Department;
import ems.entity.Employee;
import ems.entity.FulltimeEmployee;
import ems.entity.ParttimeEmployee;
import ems.interfaces.*;
import ems.iterator.EmployeeCollection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeManage implements IActions<Employee>, IEServices {

    Scanner sc = new Scanner(System.in);
    Check check = new Check();

    DepartmentManage departmentManage = DepartmentManage.getInstance();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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

        if (departmentList.size() <= 0) {
            System.out.println("Must have at least 1 department to add new employee");
        } else {
            Employee newEmployee;
            System.out.println("Enter: "
                    + "\t[f] for add fulltime"
                    + "\n\t[p] for add parttime");

            System.out.print(">Enter Choice: ");
            String typeChoice = sc.nextLine();

            switch (typeChoice.trim().toLowerCase()) {
                case "f" -> {
                    System.out.println("Add new fulltime employee");
                    newEmployee = new FulltimeEmployee();
                    enterInfor(newEmployee);
                    return newEmployee;
                }
                case "p" -> {
                    System.out.println("Add new parttime employee");
                    newEmployee = new ParttimeEmployee();
                    enterInfor(newEmployee);
                    return newEmployee;
                }
                default -> {
                    System.out.println("Wrong choice !");
                }
            }
        }

        return null;
    }

    @Override
    public boolean delete(ArrayList<Employee> objectList, String id) {
        int index = checkExist(objectList, id);
        if (index < 0) {
            System.out.printf("Employee %s not exist", id);
            return false;
        } else {
            if (index > -1) {
                objectList.remove(index);
                return true;
            }
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
        System.out.printf("No employee called %s ", name);
    }

    @Override
    public void viewAll(ArrayList<Employee> objectList) {
        if (objectList.size() > 0) {
            String dateStr;
            System.out.printf("%s\n", "List employees");
            System.out.printf("%10s | %10s | %10s | %10s | %10s |\n", 
                    "ID", "Name", "DoB", "Address", "Phone");
            
            // Iterator design pattern usage
            // Traverse Employees of a Employee Collection
            EmployeeCollection ec = new EmployeeCollection(objectList);
            Iterator<Employee> iter = ec.getIterator();
            
            while (iter.hasNext()) {
                Employee e = iter.next();
                dateStr = dateFormat.format(e.getDob());
                System.out.printf("%10s | %10s | %10s | %10s | %10s |\n",
                        e.getId(), e.getName(), dateStr, e.getAddress(), e.getPhone() + "");
            }
            return;
        }

        System.out.println("Don't have any employee");
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
                    ex.printStackTrace();
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

            System.out.println("List of departments: ");
            departmentManage.viewAll(departmentList);

            tmp = sc.nextLine();
            int indexDp = departmentManage.checkExist(departmentList, tmp);
            if (tmp.trim().length() > 0) {
                Department dp = departmentList.get(indexDp);
                if (dp != null) {
                    employee.setDepartment(dp);
                } else {
                    System.out.println("Wrong department id");
                }
            }

            if (employee instanceof ParttimeEmployee) {
                System.out.print("> Enter work hours [number]: ");
                tmp = sc.nextLine();
                if (tmp.trim().length() > 0) {
                    float wh = Float.parseFloat(tmp);
                    ((ParttimeEmployee) employee).setWord_hours(wh);
                }

                System.out.print("> Enter work price per hour [number]: ");
                tmp = sc.nextLine();
                if (tmp.trim().length() > 0) {
                    float pph = Float.parseFloat(tmp);
                    ((ParttimeEmployee) employee).setPrice_per_hour(pph);
                }
                return true;

            } else if (employee instanceof FulltimeEmployee) {
                System.out.print("> Enter Basic Salary [number]: ");
                tmp = sc.nextLine();
                if (tmp.trim().length() > 0) {
                    ((FulltimeEmployee) employee).setBasic_salary(Float.parseFloat(tmp));
                }

                System.out.print("> Enter Coefficients Salary [number]: ");
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
    public float salaryCalculator(ArrayList<Employee> objectList) {
        boolean flag = false;
        float work_days = 0;
        float totalSalary = -1;

        int indexEmployee;

        do {
            viewAll(objectList);

            System.out.print("> Enter id employee: ");
            String id = sc.nextLine();
            indexEmployee = checkExist(objectList, id);
            if (indexEmployee < 0) {
                System.out.printf("Employee %s not exist, re-enter id ", id);
            }
        } while (indexEmployee < 0);

        do {
            System.out.print("> Enter work days [nubmer]: ");
            String work_daysStr = sc.nextLine();
            flag = check.checkWorkDays(work_daysStr);
            if (flag) {
                if (Float.parseFloat(work_daysStr) > 0) {
                    work_days = Float.parseFloat(work_daysStr);
                } else {
                    System.out.println("Work days must greater than 0");
                    flag = false;
                }
            }
        } while (!flag);

        Employee employee = objectList.get(indexEmployee);
        if (employee instanceof FulltimeEmployee) {
            totalSalary = ((FulltimeEmployee) employee).getBasic_salary() * ((FulltimeEmployee) employee).getCoefficients_salary() * work_days;
        } else if (employee instanceof ParttimeEmployee) {
            totalSalary = ((ParttimeEmployee) employee).getWord_hours() * ((ParttimeEmployee) employee).getPrice_per_hour() * work_days;
        }

        return totalSalary;

    }

    @Override
    public boolean writeToFile(ArrayList<Employee> objectList, String path) {
        boolean result = false;

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(objectList);
            fos.close();
            oos.close();

            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Employee> readFromFile(String path) {
        List<Employee> employees = null;
        try {
            File file = new File(path);
            if (file.length() == 0) {
                employees = new ArrayList<>();
            } else {
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
                employees = (List<Employee>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return employees;
    }

    public void viewDetail(ArrayList<Employee> objectList, String id) {
        int index = checkExist(objectList, id);

        if (index < 0) {
            System.out.println("Employee doesn't exist");
        } else {
            Employee e = employeeList.get(index);
            String dateStr = dateFormat.format(e.getDob());

            System.out.printf("\t%s %s: \n", "Detail informations of employees ", e.getName());
            if (e instanceof FulltimeEmployee) {

                System.out.printf("%10s | %10s | %10s | %10s | %10s |  %15s | %20s |\n",
                        "ID", "Name", "DoB", "Address", "Phone", "Basic salary", "Coefficients Salary");
                System.out.printf("%10s | %10s | %10s | %10s | %10d | %16s | %20s |\n",
                        e.getId(), e.getName(), dateStr, e.getAddress(), e.getPhone(), ((FulltimeEmployee) e).getBasic_salary() + "", ((FulltimeEmployee) e).getCoefficients_salary() + "");

            } else if (e instanceof ParttimeEmployee) {
                System.out.printf("%10s | %10s | %10s | %10s | %10s |  %15s | %20s |\n",
                        "ID", "Name", "DoB", "Address", "Phone", "Work hours", "Price per hour");
                System.out.printf("%10s | %10s | %10s | %10s | %10d | %16s | %20s |\n",
                        e.getId(), e.getName(), dateStr, e.getAddress(), e.getPhone(), ((ParttimeEmployee) e).getWord_hours() + "", ((ParttimeEmployee) e).getPrice_per_hour() + "");
            }

        }
    }

    public void enterInfor(Employee employee) {
        String tmp = "";
        boolean flag = false;

        // get and validate id
        do {
            System.out.print("> Enter ID {e[number], Eg: e1}: ");
            tmp = sc.nextLine();
            flag = check.checkID(tmp);

            int index = checkExist(employeeList, tmp);
            if (index >= 0) {
                System.out.println("ID already existed in system, enter another ID !");
                flag = false;
            } else {
                if (flag) {
                    employee.setId(tmp);
                }
            }
        } while (!flag);

        // get and validate name
        do {
            System.out.print("> Enter name: ");
            tmp = sc.nextLine();
            flag = check.checkName(tmp);
            if (flag) {
                employee.setName(tmp);
            }
        } while (!flag);

        // get and validate date of birth
        do {
            System.out.print("> Enter DoB [dd/MM/yyyy]: ");
            tmp = sc.nextLine();
            flag = check.checkDate(tmp);
            if (flag) {
                try {
                    employee.setDob(dateFormat.parse(tmp));
                } catch (ParseException ex) {
                    System.out.println("Parse error");
                }
            }
        } while (!flag);

        // get and validate adresss
        do {
            System.out.print("> Enter Address: ");
            tmp = sc.nextLine();

            flag = check.checkAddress(tmp);
            if (flag) {
                employee.setAddress(tmp);
            }
        } while (!flag);

        // get and validate phone number
        do {
            System.out.print("> Enter Phone: ");
            tmp = sc.nextLine();

            flag = check.checkPhone(tmp);
            if (flag) {
                employee.setPhone(Integer.parseInt(tmp));
            }
        } while (!flag);

        // get and validate department
        do {
            departmentManage.viewAll(departmentList);

            System.out.print("> Enter Department ID: ");
            tmp = sc.nextLine();
            int existIndex = departmentManage.checkExist(departmentList, tmp);

            if (existIndex < 0) {
                System.out.println("Department not exist, choose again !");
                flag = false;
            } else {
                flag = check.checkID(tmp);
                if (flag) {
                    Department dp = departmentList.get(existIndex);
                    employee.setDepartment(dp);
                    flag = true;
                }
            }

        } while (!flag);

        if (employee instanceof FulltimeEmployee) {

            // get and validate Basic Salary
            do {
                System.out.print("> Enter Basic Salary: ");
                tmp = sc.nextLine();
                flag = check.checkSalary(tmp) > 0;
                if (flag) {
                    float s = Float.parseFloat(tmp);
                    ((FulltimeEmployee) employee).setBasic_salary(s);
                }

            } while (!flag);

            //get and validate coefficients salary
            do {
                System.out.print("> Enter Coefficients Salary: ");
                tmp = sc.nextLine();
                flag = check.checkSalary(tmp) > 0;
                if (flag) {
                    float s = Float.parseFloat(tmp);
                    ((FulltimeEmployee) employee).setCoefficients_salary(s);
                }

            } while (!flag);

        } else if (employee instanceof ParttimeEmployee) {
            System.out.print("> Enter work hours: ");
            tmp = sc.nextLine();
            if (tmp.trim().length() > 0) {
                float wh = Float.parseFloat(tmp);
                ((ParttimeEmployee) employee).setWord_hours(wh);
            }

            do {
                System.out.print("> Enter work price per hour: ");
                tmp = sc.nextLine();
                flag = check.checkSalary(tmp) > 0;
                if (flag) {
                    float pph = Float.parseFloat(tmp);
                    ((ParttimeEmployee) employee).setPrice_per_hour(pph);
                }

            } while (!flag);

        }
    }

    public int getAge(ArrayList<Employee> objectList, String id) {
        
        int index = checkExist(objectList, id);
        Employee e = objectList.get(index);
        
        int age;
        
        Date dob = e.getDob();
        
        int now = LocalDate.now().getYear();
        System.out.println(now);
        int yearOld = dob.getYear() + 1900;
        
        System.out.println(yearOld);
        age = now - yearOld;
        
        return age;
    }
}
