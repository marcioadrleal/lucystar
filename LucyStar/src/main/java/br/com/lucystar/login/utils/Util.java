package br.com.lucystar.login.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class Util {

	public static final String SYSTEM_ADMIN = "admin";

	public static String generateId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static String convertDate(Date date) {
		if (date == null) {
			return "";
		}
		LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

}
