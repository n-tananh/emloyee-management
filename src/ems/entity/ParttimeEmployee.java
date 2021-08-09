package ems.entity;

import java.util.Scanner;

public class ParttimeEmployee extends Employee {

    private float word_hours;
    private float price_per_hour;

    public float getWord_hours() {
        return this.word_hours;
    }

    /**
     *
     * @param word_hours
     */
    public void setWord_hours(float word_hours) {
        this.word_hours = word_hours;
    }

    public float getPrice_per_hour() {
        return this.price_per_hour;
    }

    /**
     *
     * @param price_per_hour
     */
    public void setPrice_per_hour(float price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    @Override
    public void enterInfor(Scanner sc) {
        super.enterInfor(sc);
        System.out.print("> Enter work hours: ");
        this.word_hours = sc.nextFloat();
        System.out.print("> Enter price per hour: ");
        this.price_per_hour = sc.nextFloat();
    }

    @Override
    public String toString() {
        return super.toString()
                + "\t\t" + this.word_hours
                + "\t\t" + this.price_per_hour;
    }

}
