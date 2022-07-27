package com.kk.healthycoder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class DietPlan {
	
	private int calories;
	private int protein;
	private int fat;
	private int carbohydrate;	
}
