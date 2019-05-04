package com.centraprise.hrmodule.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateParser {

	public Date parseDate(String parsingdate) throws ParseException {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse(parsingdate);
		return date;

	}

	public String parseDateToString(Date date) {
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String stringdate = simpleDateFormat.format(date);
		return stringdate;
	}

	public Date parseDateYYYY(String parsingdate) throws ParseException {
		String pattern = "yyyy/MM/dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse(parsingdate);
		return date;

	}

}
