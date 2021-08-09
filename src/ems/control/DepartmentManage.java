package ems.control;

import static ems.Main.departmentList;
import ems.interfaces.*;
import ems.entity.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DepartmentManage implements IActions<Department> {

    Scanner sc = new Scanner(System.in);

    public int checkExist(ArrayList<Department> listDepartment, String id) {
        for (int i = 0; i < listDepartment.size(); i++) {
            Department department = listDepartment.get(i);
            if (department.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Department addNew() {

        System.out.print("> Enter ID: ");
        String id = sc.nextLine();
        System.out.print("> Enter Name: ");
        String name = sc.nextLine();

        if (id.trim().length() > 0 && name.trim().length() > 0) {
            Department newDepartment = new Department();
            newDepartment.setId(id);
            newDepartment.setName(name);
            return newDepartment;
        }
        return null;

    }

    @Override
    public boolean delete(ArrayList<Department> objectList, String id) {
        int index = checkExist(objectList, id);
        if (index > -1) {
            objectList.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean edit(ArrayList<Department> objectList, String id) {

        int index = checkExist(objectList, id);

        if (index > -1) {
            Department dp = objectList.get(index);
            System.out.println("Enter the information to be corrected, leave blank if omitted.");

            String tmp;
            System.out.print("> Enter Name: ");
            tmp = sc.nextLine();
            if (tmp.trim().length() > 0) {
                dp.setName(tmp);
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @param objectList
     */
    @Override
    public void viewAll(java.util.ArrayList<Department> objectList) {
        System.out.println("\t\tView all departments");
        System.out.println("\t\tID\t\tName");
        objectList.forEach(dp -> {
            System.out.println("\t\t" + dp.getId() + "\t\t" + dp.getName());
        });
    }

    @Override
    public void searchByName(ArrayList<Department> objectList, String name) {
        for (Department department : objectList) {
            if (department.getName().equals(name)) {
                System.out.println(department.toString());
                return;
            }
        }
        System.out.println("No department called: " + name);
    }

    public Department getObjectByID(String id) {
        return departmentList.stream()
                .filter(department -> department.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
