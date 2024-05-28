package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.time.LocalDate;


/**
 * to create variables that represent a date in day/month/year
 */
public class Date implements Comparable<Date>, Cloneable {
    /**
     * The day of the Date.
     */
    private int day;
    /**
     * The month of the Date.
     */
    private int month;
    /**
     * The year of the Date.
     */
    private int year;

    /**
     * Minimum accepted Year when validating a date
     */
    private static final int MIN_ACCEPTED_YEAR = 1900;

    /**
     * Type enumerate, enumerating all months of the year with month number and number of days
     */
    public enum Month {
        JANUARY(1, 31),
        FEBRUARY(2, 28),
        MARCH(3, 31),
        APRIL(4, 30),
        MAY(5, 31),
        JUNE(6, 30),
        JULY(7, 31),
        AUGUST(8, 31),
        SEPTEMBER(9, 30),
        OCTOBER(10, 31),
        NOVEMBER(11, 30),
        DECEMBER(12, 31);

        /**
         * The number on the month
         */
        private final int number;

        /**
         * The number of days in a month
         */
        private final int days;

        /**
         * @param number the number on the month
         * @param days   the number of days in a month
         */
        Month(int number, int days) {
            this.number = number;
            this.days = days;
        }
    }

    /**
     * Constructs a Date object with given parameter.
     *
     * @param dateString the date in string format
     */
    public Date(String dateString) {
        setDate(dateString);
    }

    public Date plusDays(int daysToAdd) {
        LocalDate localDate = LocalDate.of(this.year, this.month, this.day).plusDays(daysToAdd);
        return new Date(String.format("%02d/%02d/%d", localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear()));
    }

    /**
     * Constructs a Date object representing today's date
     */
    public Date() {
        //set date as today's date
        LocalDate today = LocalDate.now();
        this.day = today.getDayOfMonth();
        this.month = today.getMonthValue();
        this.year = today.getYear();
    }

    /**
     * Sets the date from a string in the format DD/MM/YYYY after date validation.
     * If the date to be set is not valid, throws exception.
     *
     * @param date the date string to set
     * @throws IllegalArgumentException if the date is empty, invalid format, invalid day, invalid month, or invalid year
     */
    public void setDate(String date) {

        ValidationAttributeResults validateDateResults = validateDate(date);

        switch (validateDateResults) {
            case EMPTYNULL:
                throw new IllegalArgumentException("Date must not be empty!");
            case INVALID_FORMAT:
                throw new IllegalArgumentException("Date must follow the format DD/MM/YYYY");
            case INVALID_DAY:
                throw new IllegalArgumentException("The provided day is invalid");
            case INVALID_MONTH:
                throw new IllegalArgumentException("The provided month is invalid");
            case INVALID_YEAR:
                throw new IllegalArgumentException("The provided year is invalid");
            case VALID:
                String[] dateParts = date.trim().split("/");
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                this.day = day;
                this.month = month;
                this.year = year;
        }
    }
    public boolean isAfter(Date other) {
        return this.compareTo(other) > 0;
    }

    public boolean isBefore(Date other) {
        return this.compareTo(other) < 0;
    }

    /**
     * Validates if the provided date string is in a valid format.
     * Methods returns different validation types depending on the specific result.
     *
     * @param date the date string to validate
     * @return the validation result
     */
    private ValidationAttributeResults validateDate(String date) {

        if (date == null || date.trim().isEmpty()) {
            return ValidationAttributeResults.EMPTYNULL;
        }

        String[] dateParts = date.trim().split("/");

        if (dateParts.length != 3) {
            return ValidationAttributeResults.INVALID_FORMAT;
        }

        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        //Mes mes = Mes.valueOf(String.valueOf(month));
        if (day < 1 || day > 31) {
            return ValidationAttributeResults.INVALID_DAY;
        }

        if (month < 1 || month > 12) {
            return ValidationAttributeResults.INVALID_MONTH;
        }

        if (month == 2 && isLeapYear(year) && day > 29) {
            return ValidationAttributeResults.INVALID_DAY;
        }
        if (month == 2 && !isLeapYear(year) && day >= 29) {
            return ValidationAttributeResults.INVALID_DAY;
        }

        if (year <= MIN_ACCEPTED_YEAR) {
            return ValidationAttributeResults.INVALID_YEAR;
        }

        return ValidationAttributeResults.VALID;
    }

    /**
     * Checks if the year is a leap year.
     *
     * @param year the year to check
     * @return true if it's a leap year, false otherwise
     */
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * Returns the day of the Date.
     *
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Gets the month of the Date.
     *
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the year of the Date.
     *
     * @return the day
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the day of the Date to the specified day in parameter.
     *
     * @param day the day of the Date
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Sets the month of the Date to the specified month in parameter.
     *
     * @param month the month of the Date
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Sets the year of the Date to the specified year in parameter.
     *
     * @param year the year of the Date
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the date in the format day/month/year.
     *
     * @return the date string in the format day/month/year
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", day, month, year);
    }

    /**
     * Checks if the date is a past date.
     *
     * @return true if it is a past date, false otherwise
     */
    public boolean isPastDate() {
        LocalDate today = LocalDate.now();
        String todayDateString = (today.getDayOfMonth()) + "/" + today.getMonthValue() + "/" + today.getYear();
        Date todayDate = new Date(todayDateString);
        return this.compareTo(todayDate) < 0;
    }

    /**
     * Gets the difference in days between two dates.
     *
     * @param date the other date
     * @return the difference in days between two dates
     */
    public int getDateDifferenceInDays(Date date) {
        int days = 0;
        int byear, syear;
        if (date.getYear() > this.getYear()) {
            byear = date.getYear() - 1;
            syear = this.getYear();
        } else {
            byear = this.getYear() - 1;
            syear = date.getYear();
        }
        for (int i = byear; i > syear; i--) {
            days += 365;
            if (isLeapYear(i)) {
                days++;
            }
        }
        for (int month = 1; month < date.getMonth(); month++) {
            days += getDaysInMonth(month, date.getYear());
        }
        days += date.getDay();
        for (int month = 12; month > this.getMonth(); month--) {
            days += getDaysInMonth(month, this.getYear());
        }
        days += getDaysInMonth(this.getMonth(), this.getYear()) - this.getDay();
        return days;
    }

    /**
     * Gets the number of days in the given month and year.
     *
     * @param month the month
     * @param year  the year
     * @return the number of days in the month
     */
    private int getDaysInMonth(int month, int year) {
        if (month == Month.FEBRUARY.number) {
            return isLeapYear(year) ? 29 : 28;
        } else if (month == Month.APRIL.number || month == Month.JUNE.number || month == Month.SEPTEMBER.number || month == Month.NOVEMBER.number) {
            return 30;
        } else {
            return 31;
        }
    }

    /**
     * Compares two objects of type date.
     *
     * @param dateCompare the object to be compared.
     * @return 0 if they are equal, 1 if dateCompare is smaller, -1 if dateCompare is greater.
     */
    @Override
    public int compareTo(Date dateCompare) {
        if (this.getYear() != dateCompare.getYear()) {
            return this.getYear() > dateCompare.getYear() ? 1 : -1;
        }
        if (this.getMonth() != dateCompare.getMonth()) {
            return this.getMonth() > dateCompare.getMonth() ? 1 : -1;
        }
        if (this.getDay() != dateCompare.getDay()) {
            return this.getDay() > dateCompare.getDay() ? 1 : -1;
        }
        return 0;
    }

    /**
     * Creates a deep copy of the Date.
     *
     * @return the cloned Date
     */
    public Date clone() {
        Date clone = new Date(
                this.toString()
        );
        return clone;
    }

}
