package com.github.thfm4rt1n5.controllers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.thfm4rt1n5.entities.Feature;
import com.github.thfm4rt1n5.entities.SeedStarter;
import com.github.thfm4rt1n5.entities.Type;
import com.github.thfm4rt1n5.entities.Variety;
import com.github.thfm4rt1n5.services.SeedStarterService;
import com.github.thfm4rt1n5.services.VarietyService;

public class SeedStarterController {

	private VarietyService varietyService;
	private SeedStarterService seedStarterService;

	public SeedStarterController(VarietyService varietyService, SeedStarterService seedStarterService) {
		this.varietyService = varietyService;
		this.seedStarterService = seedStarterService;
	}

	@ModelAttribute("allTypes")
	public List<Type> populateTypes() {
		return Arrays.asList(Type.ALL);
	}

	@ModelAttribute("allFeatures")
	public List<Feature> populateFeatures() {
		return Arrays.asList(Feature.ALL);
	}

	@ModelAttribute("allVarieties")
	public List<Variety> populateVarieties() {
		return varietyService.findAll();
	}

	@ModelAttribute("allSeedStarters")
	public List<SeedStarter> populateSeedstarters() {
		return seedStarterService.findAll();
	}

	@RequestMapping({ "/", "/seedstartermng" })
	public String showSeedStarters(final SeedStarter seedStarter) {
		seedStarter.setDatePlanted(Calendar.getInstance().getTime());
		return "seedstartermng";
	}

	@RequestMapping(value = "/seedstartermng", params = { "save" })
	public String saveSeedStarter(final SeedStarter seedStarter, final BindingResult bindingResult,
			final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "seedstartermng";
		}

		this.seedStarterService.add(seedStarter);
		model.clear();
		return "redirect:/seedstartermng";
	}
}
