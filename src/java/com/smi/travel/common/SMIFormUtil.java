package com.smi.travel.common;

import java.lang.reflect.Field;

public class SMIFormUtil {
	
	public static <T> T getForm(T t){
		Class clsHead = t.getClass();
		Field [] fields = clsHead.getDeclaredFields();
		
		return t;
	}
	
	public static void main(String args[]){
		
		getForm("");
		getForm(new Object());
		
	}

}
