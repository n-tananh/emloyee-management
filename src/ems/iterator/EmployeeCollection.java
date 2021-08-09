/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.iterator;

import ems.entity.Employee;
import ems.interfaces.Collection;
import ems.interfaces.Iterator;
import java.util.List;

public class EmployeeCollection implements Collection {
    List<Employee> list;

    public EmployeeCollection(List<Employee> list) {
        this.list = list;
    }

    @Override
    public Iterator getIterator() {
        return new EmployeeIterator();
    }
 
    class EmployeeIterator implements Iterator<Employee> {
        int currentIndex = 0;
        
        @Override
        public boolean hasNext() {
            return currentIndex < list.size();
        }

        @Override
        public Employee next() {
            return list.get(currentIndex++);
        }        
    }
  
}
