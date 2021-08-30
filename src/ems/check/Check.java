/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.check;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Tan Anh
 */
public class Check {

    public enum error {

        DATE("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$"),
        NUMBER("^[0-9]*$"),
        NAME("^[a-zA-Z\\s]+"),
        SPECIAL_CHARACTERS("[^a-zA-Z0-9\\s]");

        private final String pattern;

        error(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return this.pattern;
        }
    }

    public boolean checkPattern(String patternStr, String input) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public boolean checkID(String id) {
        boolean result = false;
        if (id.trim().length() <= 0) {
            System.out.println("ID must filled, re-enter ID");
        } else if (checkPattern(error.SPECIAL_CHARACTERS.pattern, id)) {
            System.out.println("ID can't contain special characters, re-enter ID");
        } else {
            result = true;
        }
        return result;
    }

    public boolean checkName(String name) {
        boolean result = false;
        if (name.trim().length() <= 1) {
            System.out.println("Name must contain 2 or more characters, re-enter name");
        } else if (!checkPattern(error.NAME.pattern, name)) {
            System.out.println("Name can't contain numbers, re-enter name");
        } else if (checkPattern(error.SPECIAL_CHARACTERS.pattern, name)) {
            System.out.println("Name can't contain special characters, re-enter name");
        } else {
            result = true;
        }
        return result;
    }

    public boolean checkDate(String date) {
        boolean result = false;

        if (checkPattern(error.DATE.pattern, date)) {
            result = true;
        } else {
            System.out.println("Date must format [dd/MM/yyyy], re-enter date !");
        }

        return result;
    }

    public boolean checkAddress(String address) {
        boolean result = false;
        if (address.trim().length() <= 3) {
            System.out.println("Name must contain 3 or more characters, re-enter name");
        } else {
            result = true;
        }
        return result;
    }

    public boolean checkPhone(String phone) {
        boolean result = false;
        if (phone.trim().length() <= 2) {
            System.out.println("Phone must contain 3 or more numbers, re-enter name");
        } else if (!checkPattern(error.NUMBER.pattern, phone)) {
            System.out.println("Phone number can't contain letters , re-enter phone number");
        } else {
            result = true;
        }
        return result;
    }

    public float checkSalary(String salary) {
        float result = Float.parseFloat(salary);

        if (salary.trim().length() <= 0) {
            System.out.println("Salary must filled, re-enter ID");
        } else if (!checkPattern(error.NUMBER.pattern, salary)) {
            System.out.println("Salary  can't contain letters , re-enter salary");
        } else if (result <= 0) {
            System.out.println("Salary must greater than 0 , re-enter salary");
        }

        return result;
    }

    public float checkWorkHours(String numberStr) {
        int result = Integer.parseInt(numberStr);

        if (numberStr.trim().length() <= 0) {
            System.out.println("Work hours  must filled, re-enter ID");
        } else if (!checkPattern(error.NUMBER.pattern, numberStr)) {
            System.out.println("Work hours can't contain letters , re-enter work hours ");
        } else if (result <= 0) {
            System.out.println("Work hours  must greater than 0 , re-enter work hours ");
        }

        return result;
    }

    public boolean checkWorkDays(String dayStr) {
        boolean result = false;
        if (dayStr.trim().length() <= 0) {
            System.out.println("Work hours  must filled, re-enter ID");
        } else if (!checkPattern(error.NUMBER.pattern, dayStr)) {
            System.out.println("Work hours can't contain letters , re-enter work hours ");
        } else {
            result = true;
        }
        return result;
    }

}
