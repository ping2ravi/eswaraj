package com.eswaraj.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class DateTimeUtil {

	public DateTimeUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCurrentTimeYYYYMMDDHHMMSS(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		return simpleDateFormat.format(calendar.getTime());
	}

}
