package com.github.thfm4rt1n5.configs;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.github.thfm4rt1n5.entities.Variety;
import com.github.thfm4rt1n5.services.VarietyService;

public class VarietyFormatter implements Formatter<Variety> {

	@Autowired
	private VarietyService varietyService;

	@Override
	public String print(Variety object, Locale locale) {
		return (object != null ? object.getId().toString() : "");
	}

	@Override
	public Variety parse(String text, Locale locale) throws ParseException {
		final Integer varietyId = Integer.valueOf(text);
		return this.varietyService.findById(varietyId);
	}

}
