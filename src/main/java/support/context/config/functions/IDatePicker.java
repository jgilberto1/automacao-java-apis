package support.context.config.functions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface IDatePicker {

    /**
     * @since 16/05/2018
     */
    String typeOfFormat = "dd/MM/yyyy HH:mm:ss";

    /**
     * get current date and time full
     */
    default String getDateTime() {
        DateFormat df = new SimpleDateFormat(typeOfFormat);
        return df.format(Calendar.getInstance().getTime());
    }

    /**
     * get current day
     */
    default String getDay() {
        DateFormat df = new SimpleDateFormat(typeOfFormat);
        return df.format(Calendar.getInstance().getTime()).split(" ")[0].split("/")[0];
    }

    /**
     * get current month
     */
    default String getMonth() {
        DateFormat df = new SimpleDateFormat(typeOfFormat);
        return df.format(Calendar.getInstance().getTime()).split(" ")[0].split("/")[1];
    }

    /**
     * get current year
     */
    default String getYear() {
        DateFormat df = new SimpleDateFormat(typeOfFormat);
        return df.format(Calendar.getInstance().getTime()).split(" ")[0].split("/")[2];
    }

    /**
     * get current hour
     */
    default String getHour() {
        DateFormat df = new SimpleDateFormat(typeOfFormat);
        return df.format(Calendar.getInstance().getTime()).split(" ")[1].split(":")[0];
    }

    /**
     * get current minute
     */
    default String getMinute() {
        DateFormat df = new SimpleDateFormat(typeOfFormat);
        return df.format(Calendar.getInstance().getTime()).split(" ")[1].split(":")[1];
    }

    /**
     * get current second
     */
    default String getSecond() {
        DateFormat df = new SimpleDateFormat(typeOfFormat);
        return df.format(Calendar.getInstance().getTime()).split(" ")[1].split(":")[2];
    }

    /**
     * get current time
     */
    default String getCurrentTime() {
        DateFormat df = new SimpleDateFormat(typeOfFormat);
        return df.format(Calendar.getInstance().getTime()).split(" ")[1];
    }

    /**
     * get current date
     */
    default String getCurrentDate() {
        DateFormat df = new SimpleDateFormat(typeOfFormat);
        return df.format(Calendar.getInstance().getTime()).split(" ")[0];
    }

    /**
     * get date Up or Down, you can pass parameter int day as negative or positive
     *
     * @param day set day to get date to up or down, can be positive or negative
     */
    default String getDateUpOrDown(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(calendar.getTime());
    }
}
