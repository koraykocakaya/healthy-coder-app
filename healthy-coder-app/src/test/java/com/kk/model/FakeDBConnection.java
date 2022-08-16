package com.kk.model;

import java.util.Arrays;
import java.util.List;

public class FakeDBConnection {
	public List<String> getAllUserNames() {
		// here your code for - get results from database (just for test, returning
		// dummy values)
		return Arrays.asList("ahmet", "mehmet", "koray");
	}
}