package com.Trapeze.NOVUS.Selenium;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parallelized.class)
public class GridTesting {
	@SuppressWarnings("unused")
	private String ss;
	 public GridTesting(String d){
		 this.ss = d;
	 }
	 
	 @Parameters
	    public static Collection<Object[]> getParameters()     
	    {         
	        List<Object> features = classes();
	        List<Object[]> parameters = new ArrayList<Object[]>(features.size());
	        for (Object feature : features)         
	        {
	             parameters.add(new Object[] { feature });
	        }
	        return parameters;
	    }
	 
	 @Test 
	 public void testGrid() throws ClassNotFoundException{
	 }
	 
	private static List<Object> classes(){
		 ArrayList<Object> classArray = new ArrayList<Object> ();
		 classArray.add("com.Trapeze.Selenium.Novus.AllTests");
		 classArray.add("com.Trapeze.Selenium.Novus.SUITE1");
		 return classArray;
	 }
}
