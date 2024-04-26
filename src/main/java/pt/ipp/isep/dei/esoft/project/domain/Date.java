package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;

/**
 * to create variables that represent a date in day/month/year
 */
public class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    public Date(String birthdate) {
        setDate(birthdate);
    }

    private boolean validateDate(String date) {
        String[] dateParts = date.trim().split("/");

        if (dateParts.length != 3) {
            return false;
        }

        //TODO: IMPLEMENT VALIDATION

        return true;

    }

    public void setDate(String date) {

        if (validateDate(date)) {
            String[] dateParts = date.trim().split("/");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {

        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public boolean isPastDate() {
        LocalDate today = LocalDate.now();
        String todayDateString = (today.getDayOfMonth()) + "/" + today.getMonthValue() + "/" + today.getYear();
        Date todayDate = new Date(todayDateString);
        return this.compareTo(todayDate) < 0;
    }

    @Override
    public int compareTo(Date date) {
        if (this.getYear() == date.getYear()) {
            if (this.getMonth() == date.getMonth()) {
                if (this.getDay() == date.getDay()) {
                    return 0;
                }
            }
        }

        //TODO: IMPLEMENT
        return 0;
    }

}
