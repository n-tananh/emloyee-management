package ems.interfaces;

import ems.entity.Employee;

import java.util.ArrayList;

public interface IEServices {

	/**
	 * 
	 */
	 float salaryCalculator(ArrayList<Employee> objectList, String employee_id);

}