package dao;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class TimestampConverter {
	public static GregorianCalendar toGregorianCalendar(Timestamp timestamp) {
		GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		return calendar;
	}
}
