package ems.entity;

public class Department {

    private String id;
    private String name;

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

    @Override
    public String toString() {
        return "--> Department{ID=" + id + " Name= " + name + "}";
    }
}
