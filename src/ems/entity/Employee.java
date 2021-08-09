package ems.entity;

import static ems.Main.departmentList;
import ems.control.EmployeeManage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Employee {

    private String id;
    private String name;
    private Date dob;
    private String address;
    private int phone;
    private Department department;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getId() {
        return this.id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return this.phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return this.department;
    }

    /**
     *
     * @param department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    public void enterInfor(Scanner sc) {
        System.out.print("> Enter ID: ");
        this.setId(sc.nextLine());
        System.out.print("> Enter Name: ");
        this.setName(sc.nextLine());

        System.out.print("> Enter DoB [dd-MM-yyyy]: ");
        String dobStr = sc.nextLine();

        try {
            this.setDob(dateFormat.parse(dobStr));
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManage.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.print("> Enter Address: ");
        this.setAddress(sc.nextLine());

        System.out.print("> Enter Phone: ");
        this.setPhone(sc.nextInt());
        sc.nextLine();

        System.out.print("> Enter Department ID: ");
        String id = sc.nextLine();
        Department dp = departmentList.stream()
                .filter(department -> id.equals(department.getId()))
                .findAny()
                .orElse(null);
        this.setDepartment(dp);
    }

    @Override
    public String toString() {
        String strDate = dateFormat.format(this.dob);  
        if (this.department != null) {
            return "\t" + this.id
                    + "\t" + this.name
                    + "\t" + strDate
                    + "\t" + this.address
                    + "\t" + this.phone
                    + "\t" + this.department.getName();
        } else {
            return "\t" + this.id
                    + "\t" + this.name
                    + "\t" + strDate
                    + "\t" + this.address
                    + "\t" + this.phone
                    + "\t" + "null";
        }

    }

}
