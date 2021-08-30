package ems.entity;

public class ParttimeEmployee extends Employee {

    private float word_hours;
    private float price_per_hour;

    public float getWord_hours() {
        return this.word_hours;
    }

    public void setWord_hours(float word_hours) {
        this.word_hours = word_hours;
    }

    public float getPrice_per_hour() {
        return this.price_per_hour;
    }

    public void setPrice_per_hour(float price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

}
