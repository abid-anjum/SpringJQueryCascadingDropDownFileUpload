package com.abid.frontenddeveloper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.abid.frontend.model.City;
import com.abid.frontend.model.User;

@Controller
public class HomeController {

	@RequestMapping(value = { "/home" })
	public String load(@ModelAttribute("userForm") User user, Map<String, Object> model) {

		User userForm = new User();
		// userForm.setJavaSkills("Struts");
		// userForm.setCountry("MY");
		model.put("userForm", userForm);

		Map<String, String> country = new LinkedHashMap<String, String>();
		country.put("US", "United Stated");
		country.put("CHINA", "China");
		country.put("SG", "Singapore");
		country.put("MY", "Malaysia");
		model.put("countryList", country);

		Map<String, String> javaSkill = new LinkedHashMap<String, String>();
		javaSkill.put("Hibernate", "Hibernate");
		javaSkill.put("Spring", "Spring");
		javaSkill.put("Apache Wicket", "Apache Wicket");
		javaSkill.put("Struts", "Struts2");
		model.put("javaSkillsList", javaSkill);

		//call when drop down Postback
		UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
		String uri = builder.buildAndExpand().getPath();

		Map<String, String> city = new LinkedHashMap<String, String>();
		if (user.getCountry() != null && user.getCountry().equalsIgnoreCase("US")) {
			city.put("NYK", "New York");

		} else {
			city.put("City1", "City");
		}

		// maintain state
		model.put("cityList", city);
		model.put("userForm", user);

		String redirectUrl = "home";
		return redirectUrl;

	}

	@RequestMapping(value = "/register")
	public String viewRegistration(@ModelAttribute("userForm") User user, Map<String, Object> model) {

		return "registration";
	}

	@RequestMapping(path = "/findCity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Set<City> findCity(@RequestParam(value = "stateName", required = true) String country) {
		Set<City> hash_Set = new HashSet<City>();

		String countryprm = country;

		City ct = null;

		if (countryprm.equalsIgnoreCase("US")) {
			ct = new City();
			ct.setId("NYK");
			ct.setName("NewYork");
		} else {
			ct = new City();
			ct.setId("CT1");
			ct.setName("City 1");
		}
		hash_Set.add(ct);

		return hash_Set;
	}

	@RequestMapping(path = "/findCity1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Set<String> findCity1() {
		Set<String> hash_Set = new HashSet<String>();
		hash_Set.add("NewYork");
		hash_Set.add("London");
		return hash_Set;
	}

	@RequestMapping(value = "/getCity")
	public Map<String, String> getCity() {
		Map<String, String> city = new LinkedHashMap<String, String>();
		city.put("LHR", "Lahore");
		return city;
	}

}
