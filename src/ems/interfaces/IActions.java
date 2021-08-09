package ems.interfaces;

import java.util.ArrayList;

public interface IActions<T> {

     int checkExist(ArrayList<T> objectList, String id);
    
     T addNew();

    /**
	 * 
	 * @param objectList
	 * @param id
	 * @return Boolean
	 */
    boolean delete(java.util.ArrayList<T> objectList, String id);

    /**
     *
     * @param objectList
     * @param name
     */
     void searchByName(java.util.ArrayList<T> objectList, String name);

    /**
     *
     * @param objectList
     */
     void viewAll(java.util.ArrayList<T> objectList);

    /**
     *
     * @param objectList
     * @param id
     * @return
     */
     boolean edit(ArrayList<T> objectList, String id);
    
}
