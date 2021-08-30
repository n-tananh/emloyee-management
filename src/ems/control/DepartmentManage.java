package ems.control;

import ems.check.Check;
import ems.check.Check.error;
import static ems.Main.departmentList;
import ems.interfaces.*;
import ems.entity.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentManage implements IActions<Department> {

    private static DepartmentManage instance;

    private DepartmentManage() {
    }

    public static DepartmentManage getInstance() {
        if (instance == null) {
            instance = new DepartmentManage();
        }
        return instance;
    }

    Check check = new Check();
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
        String id, name;
        Department newDepartment = new Department();

        boolean flag = false;

        try {
            do {
                System.out.print("> Enter ID {d[number] , Eg: d001}: ");
                id = sc.nextLine();
                flag = check.checkID(id);

                int index = checkExist(departmentList, id);
                if (index >= 0) {
                    System.out.println("Department already existed !, re-enter id");
                    flag = false;
                } else {
                    if (flag) {
                        newDepartment.setId(id);
                    }
                }

            } while (!flag);

            do {
                System.out.print("> Enter Name: ");
                name = sc.nextLine();
                flag = check.checkName(name);
                if (flag) {
                    newDepartment.setName(name);
                }
            } while (!flag);

            return newDepartment;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(ArrayList<Department> objectList, String id) {
        int index = checkExist(objectList, id);
        if (index < 0) {
            System.out.println("Dont have department have ID is: " + id);
            return false;
        } else {
            try {
                objectList.remove(index);
                return true;
            } catch (NullPointerException e) {
                e.getMessage();
                return false;
            }
        }

    }

    @Override
    public boolean edit(ArrayList<Department> objectList, String id) {
        String tmp;
        boolean flag = false;
        int index = checkExist(objectList, id);

        if (index < 0) {
            System.out.println("Doesn't have department have  is: " + id);
            return false;
        } else {
            Department dp = objectList.get(index);
            System.out.println("Enter the information to be corrected, leave blank if omitted.");

            System.out.print("> Enter Name: ");
            tmp = sc.nextLine();
            if (tmp.trim().length() > 0) {
                do {
                    if (tmp.trim().length() <= 2) {
                        System.out.println("Name must have 3 or more characters,re-enter name");
                    } else if (check.checkPattern(error.SPECIAL_CHARACTERS.getPattern(), tmp)) {
                        System.out.println("Name contain special characters, re-enter name");
                    } else {
                        flag = !flag;
                        dp.setName(tmp);
                    }
                } while (!flag);
            }
            return true;
        }
    }

    @Override
    public void viewAll(ArrayList<Department> objectList) {
        System.out.printf("\t%s\n", "List departments");
        System.out.printf("%10s | %10s |\n", "ID", "Name");
        objectList.forEach(dp -> {
            System.out.printf("%10s | %10s |\n", dp.getId(), dp.getName());
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

    @Override
    public boolean writeToFile(ArrayList<Department> objectList, String path) {
        boolean result = false;

        FileOutputStream fos;
        ObjectOutputStream oos;
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
    public List<Department> readFromFile(String path) {
        List<Department> departments = null;
        try {
            File file = new File(path);
            if (file.length() == 0) {
                departments = new ArrayList<>();
            } else {
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
                departments = (List<Department>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return departments;
    }

}
