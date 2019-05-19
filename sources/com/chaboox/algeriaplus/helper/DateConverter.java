package com.chaboox.algeriaplus.helper;

public class DateConverter {
    public String getDate(String unformatedDate) {
        return unformatedDate.substring(8, 10);
    }

    public String getMonth(String unformatedDate) {
        String month = unformatedDate.substring(5, 7);
        int i = -1;
        switch (month.hashCode()) {
            case 1537:
                if (month.equals("01")) {
                    i = 0;
                    break;
                }
                break;
            case 1538:
                if (month.equals("02")) {
                    i = 1;
                    break;
                }
                break;
            case 1539:
                if (month.equals("03")) {
                    i = 2;
                    break;
                }
                break;
            case 1540:
                if (month.equals("04")) {
                    i = 3;
                    break;
                }
                break;
            case 1541:
                if (month.equals("05")) {
                    i = 4;
                    break;
                }
                break;
            case 1542:
                if (month.equals("06")) {
                    i = 5;
                    break;
                }
                break;
            case 1543:
                if (month.equals("07")) {
                    i = 6;
                    break;
                }
                break;
            case 1544:
                if (month.equals("08")) {
                    i = 7;
                    break;
                }
                break;
            case 1545:
                if (month.equals("09")) {
                    i = 8;
                    break;
                }
                break;
            case 1567:
                if (month.equals("10")) {
                    i = 9;
                    break;
                }
                break;
            case 1568:
                if (month.equals("11")) {
                    i = 10;
                    break;
                }
                break;
            case 1569:
                if (month.equals("12")) {
                    i = 11;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return month;
        }
    }

    public String getYear(String unformatedDate) {
        return unformatedDate.substring(0, 4);
    }
}
