package com.centraprise.hrmodule.config;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:leavescalendar.properties")
@Configuration
public class YearLeavesConfig {

	@Value("${holidays}")
	private String publicHolidays;

	@Bean
	public List<Calendar> publicHolidaysList() throws ParseException {
		String[] days = publicHolidays.split(",");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		List<Calendar> cals = new ArrayList<Calendar>();
		for (String str : days) {
			Calendar calendar = Calendar.getInstance();
			Date date = df.parse(str);
			calendar.setTime(date);
			cals.add(calendar);
		}
		return cals;

	}

}
