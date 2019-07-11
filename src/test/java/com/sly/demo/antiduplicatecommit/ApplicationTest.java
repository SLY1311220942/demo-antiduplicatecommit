package com.sly.demo.antiduplicatecommit;

import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApplicationTest {

	@Test
	public void contextLoads() {
		double base = 1;
		double baseTotal = 0;
		double total = 30;
		double increase = 1.25;
		int year = 0;
		while(baseTotal < total) {
			baseTotal += base;
			base *= increase;
			year ++;
		}
		
		System.out.println(year);
		System.out.println(base);
		System.out.println(baseTotal);
	}

}
