package com.glowik.Decorator;

import org.apache.log4j.Logger;

/**
 * @date August 26,2012
 * 
 */

// implementation of a simple coffee without any extra ingredients
public class SimpleCoffee implements Coffee {
	
	static Logger logger = Logger.getLogger(SimpleCoffee.class);
	
	public double getCost() {
		return 1;
	}

	public String getIngredients() {
		return "Coffee";
	}

	public static void main(String[] args) {
		System.out.println("Hello Coffee!");
		Coffee c = new SimpleCoffee();
        logger.debug("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
 
        c = new Milk(c);
        logger.debug("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
 
        c = new Sprinkles(c);
        logger.debug("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
 
        c = new Whip(c);
        logger.debug("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
 
        // Note that you can also stack more than one decorator of the same type
        c = new Sprinkles(c);
        logger.debug("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
        
        
        Coffee c3 = new Sprinkles(new SimpleCoffee());
        logger.debug("Cost3: " + c3.getCost() + "; Ingredients: " + c3.getIngredients());
        
	}
}
