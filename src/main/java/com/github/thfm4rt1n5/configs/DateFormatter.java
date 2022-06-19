package com.github.thfm4rt1n5.configs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {

	@Autowired
	private MessageSource messageSource;

	private SimpleDateFormat createDateFormat(final Locale locale) {
		final String format = this.messageSource.getMessage("date.format", null, locale);
		final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		return dateFormat;
	}

	@Override
	public String print(final Date object, final Locale locale) {
		final SimpleDateFormat dateFormat = createDateFormat(locale);
		return dateFormat.format(object);
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		final SimpleDateFormat dateFormat = createDateFormat(locale);
		return dateFormat.parse(text);
	}

}
