package classes;

import modules.menu.model.Config;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Date_class")
public class date implements Serializable {

    @XStreamAlias("Date")
    private String date;
    @XStreamAlias("Day")
    private int day;
    @XStreamAlias("Month")
    private int month;
    @XStreamAlias("Year")
    private int year;

    // Constructor fecha formato
    public date(String date) {
        String[] introducefecha = null;
        String formatdate = "";

        switch (Config.getInstance().getFormatDate()) {

            case "dd/MM/yyyy":
                introducefecha = date.split("/");

                this.day = Integer.parseInt(introducefecha[0]);
                this.month = Integer.parseInt(introducefecha[1]);
                this.year = Integer.parseInt(introducefecha[2]);
                formatdate = this.day + "/" + this.month + "/" + this.year;
                break;

            case "dd-MM-yyyy":
                introducefecha = date.split("-");

                this.day = Integer.parseInt(introducefecha[0]);
                this.month = Integer.parseInt(introducefecha[1]);
                this.year = Integer.parseInt(introducefecha[2]);
                formatdate = this.day + "-" + this.month + "-" + this.year;
                break;
            case "yyyy/MM/dd":
                introducefecha = date.split("/");

                this.day = Integer.parseInt(introducefecha[2]);
                this.month = Integer.parseInt(introducefecha[1]);
                this.year = Integer.parseInt(introducefecha[0]);
                formatdate = this.year + "/" + this.month + "/" + this.day;
                break;
            case "yyyy-MM-dd":
                introducefecha = date.split("-");

                this.day = Integer.parseInt(introducefecha[2]);
                this.month = Integer.parseInt(introducefecha[1]);
                this.year = Integer.parseInt(introducefecha[0]);
                formatdate = this.year + "-" + this.month + "-" + this.day;
                break;
        }
        this.date = formatdate;
    }

    // comprobar dia y mes
    public boolean checkday_month() {
        boolean result = false;
        GregorianCalendar date = new GregorianCalendar();
        int days_month[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (date.isLeapYear(this.year)) {
            days_month[2] = 29;

        }
        if (((this.month) >= 1) && ((this.month) <= 12)) {
            result = true;
        }
        if (result == true) {
            if ((this.day >= 1) && (this.day <= days_month[this.month])) {
                result = true;
            } else {
                result = false;

            }
        }
        return result;
    }

    // Comprobar a�o
    public boolean checkyear() {
        if (((this.year) >= 1900) && ((this.year) <= 2030)) {
            return true;
        } else {
            return false;
        }
    }

    // comprobar fecha
    public boolean checkdate() {
        if ((checkday_month() == true) && ((checkyear()) == true)) {
            return true;
        } else {
            return false;
        }

    }

    public Calendar StringtoCalendar() {

        Calendar dateCalendar = Calendar.getInstance();
        try {

            dateCalendar.set(this.getYear(), this.getMonth()-1, this.getDay());

        } catch (Exception e) {
            return dateCalendar;
        }
        return dateCalendar;
    }

    public String CalendartoString() {

        Calendar c = Calendar.getInstance();
        return (c.getTime().toString());
    }

    // coger la fecha del sistema
    public static String datesystem() {

        Calendar c = Calendar.getInstance();
        String day = Integer.toString(c.get(Calendar.DATE));
        String month = Integer.toString(c.get(Calendar.MONTH) + 1);
        String year = Integer.toString(c.get(Calendar.YEAR));
        String date = day + "/" + month + "/" + year;
        return date;
    }

    // comparar fecha del sistema
    public int comparedatesystem() {

        Calendar c1 = this.StringtoCalendar();
        Calendar c2 = Calendar.getInstance();

        c1.set(this.year, this.month - 1, this.day);

        return c1.compareTo(c2);

    }

    // Comparar fechas
    public int comparedate(date date2) {
       int compare = 0;
		Calendar c1 = this.StringtoCalendar();
		Calendar c2 = null;
		c2 = date2.StringtoCalendar();

		if (c1.before(c2)) {
			compare = 0;
		} else if (c1.equals(c2)) {
			compare = 1;
		} else if (c1.after(c2)) {
			compare = 2;
		}
		return compare;
    }

    // restar fecha sistema
    public int substractdatesystem() {
        int result = 0;
        Calendar currentdate = Calendar.getInstance();
        int currentday = currentdate.get(Calendar.DATE);
        int currentmonth = currentdate.get(Calendar.MONTH) + 1;
        int currentyear = currentdate.get(Calendar.YEAR);

        result = currentyear - this.year;

        if (currentmonth < this.month) {
            result = result - 1;
        } else if (currentmonth == this.month) {
            if (currentday < this.day) {
                result = result - 1;
            }
        }

        return result;
    }

    // restar 2 fechas
    public int substractdate(date date1) {
        int result = 0;

        Calendar c1 = StringtoCalendar();
        int day = c1.get(Calendar.DATE);
        int month = c1.get(Calendar.MONTH) + 1;
        int year = c1.get(Calendar.YEAR);

        Calendar c2 = date1.StringtoCalendar();
        int day2 = c2.get(Calendar.DATE);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int year2 = c2.get(Calendar.YEAR);

        result = (year2 - year);

        if (month2 < month) {
            result = result - 1;
        } else if (month2 == month) {
            if (day2 < day) {
                result = result - 1;
            }
        }
        return result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // toString
    public String toString() {

        return this.date;
    }

    // toString format date
    public String toString(String format) {
        String dateformat = "";

        switch (format) {

            case "dd/MM/yyyy":
                // dd/mm/yyyy
                dateformat = this.day + "/" + this.month + "/" + this.year;
                break;
            case "dd-MM-yyyy":
                // dd-mm-yyyy
                dateformat = this.day + "-" + this.month + "-" + this.year;
                break;
            case "yyyy/MM/dd":
                // yyyy/mm/dd
                dateformat = this.year + "/" + this.month + "/" + this.day;
                break;
            case "yyyy-MM-dd":
                // yyyy-mm-dd
                dateformat = this.year + "-" + this.month + "-" + this.day;
                break;
        }
        return dateformat;
    }

}
