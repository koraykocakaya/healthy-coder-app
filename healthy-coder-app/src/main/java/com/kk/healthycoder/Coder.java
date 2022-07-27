package com.kk.healthycoder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Coder {
	
	private double height;
	private double weight;
	private int age;
	private Gender gender;
		
	public Coder(double height, double weight) {
		super();
		this.height = height;
		this.weight = weight;
	}
	
}
