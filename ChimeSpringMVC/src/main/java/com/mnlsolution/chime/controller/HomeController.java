package com.mnlsolution.chime.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}


	/**
	 * 서버 시간 리턴 
	 * @return
	 */
	public String serverTime() {

		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		return dayTime.format(new Date(time));   
		
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String osInfo() {
		
		return System.getProperty("os.name");
	}

	
	
	@ResponseBody
	@RequestMapping(value = "/jsontest1", method = RequestMethod.GET)
	public HashMap jsonTest1() {

		logger.info("json TEST1");
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		
		hashmap.put("Application Key", "000001");
		hashmap.put("ServerTime",serverTime());
		hashmap.put("OSInfo",osInfo());		
		
		return hashmap; 
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/jsontest2/{id}/{passwd}", method = RequestMethod.GET)
	public HashMap jsonTest2(@PathVariable String id, @PathVariable String passwd) {

		logger.info("json TEST2");
		
		
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		
		hashmap.put("id", id);
		hashmap.put("password",passwd);
		hashmap.put("Action","정상 처리되었습니다");		
		
		return hashmap; 
	}
	
	@RequestMapping(value = "/jsontest3", method = RequestMethod.POST)
	@ResponseBody
	public HashMap jsonTest3(@RequestBody Map body) {

		// TODO Rest post 방식의 정확한 사용법 확인할 것   
		
		logger.info("json TEST3 Post");
		
		
		logger.info("====>" + RequestMethod.POST);
		logger.info("====>" + body);		
		
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		hashmap.put("Action","정상 처리되었습니다");
		
		
		
		return hashmap; 
	}

	
	

	
	
	
}
