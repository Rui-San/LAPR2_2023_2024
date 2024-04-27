package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;

/**
 * to create variables that represent a date in day/month/year
 */
public class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;
    private static final int MIN_ACCEPTED_YEAR = 1900;

    public enum Mes {
        JANEIRO(1, 31),
        FEVEREIRO(2, 28),
        MARCO(3, 31),
        ABRIL(4, 30),
        MAIO(5, 31),
        JUNHO(6, 30),
        JULHO(7, 31),
        AGOSTO(8, 31),
        SETEMBRO(9, 30),
        OUTUBRO(10, 31),
        NOVEMBRO(11, 30),
        DEZEMBRO(12, 31);

        private final int numero;
        private final int dias;

        Mes(int numero, int dias) {
            this.numero = numero;
            this.dias = dias;
        }

        public int getNumero() {
            return numero;
        }

        public int getDias() {
            return dias;
        }
    }

    public enum ValidateDateResults {
        INVALID_FORMAT, VALID, EMPTY, INVALID_MONTH, INVALID_YEAR, INVALID_DAY
    }

    public Date(String birthdate) {
        setDate(birthdate);
    }

    public void setDate(String date) {

        ValidateDateResults validateDateResults = validateDate(date);

        switch (validateDateResults){
            case EMPTY:
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

    private ValidateDateResults validateDate(String date) {

        if (date == null || date.trim().isEmpty()) {
            return ValidateDateResults.EMPTY;
        }

        String[] dateParts = date.trim().split("/");

        if (dateParts.length != 3) {
            return ValidateDateResults.INVALID_FORMAT;
        }

        for (String part : dateParts) {
            int number = Integer.parseInt(part);
            if (number <= 0) {
                return ValidateDateResults.EMPTY;
            }
        }

        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        Mes mes = Mes.valueOf(String.valueOf(month));
        if (day < 1 || day > mes.getDias()) {
            return ValidateDateResults.INVALID_DAY;
        }

        if (month < 1 || month > 12) {
            return ValidateDateResults.INVALID_MONTH;
        }

        if (month == 2 && isLeapYear(year) && day > 29) {
            return ValidateDateResults.INVALID_DAY;
        }

        if (year <= MIN_ACCEPTED_YEAR) {
            return ValidateDateResults.INVALID_YEAR;
        }

        return ValidateDateResults.VALID;
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
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

    /**
     * Returns the date in the format day/month/year.
     * @return
     */
    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    /**
     * Checks if the date is a past date.
     * @return true if it is a past date, false otherwise.
     */
    public boolean isPastDate() {
        LocalDate today = LocalDate.now();
        String todayDateString = (today.getDayOfMonth()) + "/" + today.getMonthValue() + "/" + today.getYear();
        Date todayDate = new Date(todayDateString);
        return this.compareTo(todayDate) < 0;
    }

    /**
     * Compares two objects of type date.
     * @param dateCompare the object to be compared.
     * @return 0 if they are equal, 1 if dateCompare is smaller, -1 if dateCompare is greater.
     */
    @Override
    public int compareTo(Date dateCompare) {
        if(this.getYear() != dateCompare.getYear()){
            return this.getYear() > dateCompare.getYear() ? 1 : -1;
        }
        if(this.getMonth() != dateCompare.getMonth()){
            return this.getMonth() > dateCompare.getMonth() ? 1 : -1;
        }
        if(this.getDay() != dateCompare.getDay()){
            return this.getDay() > dateCompare.getDay() ? 1 : -1;
        }
        return 0;
    }

}
