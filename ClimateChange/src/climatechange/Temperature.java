package climatechange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Temperature implements ITemperature
{
	private String country;
	private String threeLetterCode;
	private String month;
	private int year;
	private double celsiusTemp;
	
	//temp, year, month, country, code
	public Temperature(double celsiusTemp, int year, String month, String country, String threeLetterCode)	
	{
		this.celsiusTemp = celsiusTemp;
		this.year = year;
		this.month = month;
		this.country = country;
		this.threeLetterCode = threeLetterCode;
	}
	
	
	public String getCountry() // get the name of the country
	{
		return country;
	}
	
	
	public String getCountry3LetterCode() // get the 3-letter code of the country
	{
		return threeLetterCode;
	}
	
	
	public String getMonth() // get the month
	{
		return month;
	}
	
	
	public int getYear() // get the year
	{
		return year;
	}
	
	
	public double getTemperature(boolean getFahrenheit) // get temperature; input parameter of false = return Celsius value)
	{
		if (getFahrenheit == true) // if true, then convert from Celsius to Fahrenheit and return
		{
			// Fahrenheit = Celsius * (9/5) + 32
			double fahrenheitTemp = (celsiusTemp * 9 / 5) + 32;
			return fahrenheitTemp;
		}
		return celsiusTemp;
	}
	
	
	@Override
	public boolean equals(Object x)
	{
		// First checks for temperature, then country, then year, and then month.
		ITemperature that = (ITemperature) x;
		
		if (this.compareTo(that) != 0)
			return false;
		
		return true;
	}
	
	
	// This switch block is supposed to map a month to an integer so that the months can be compared
	// If the month inputed is equal to the case, then it will be set to that corresponding number.
	 public int monthToInt(String month) 
	 {
	        int monthNum = 0;
	        if (month == null)
	            return monthNum;
	        
	        switch (month) 
	        {
	            case "Jan":
	                monthNum = 1;
	                break;
	            case "Feb":
	                monthNum = 2;
	                break;
	            case "Mar":
	                monthNum = 3;
	                break;
	            case "Apr":
	                monthNum = 4;
	                break;
	            case "May":
	                monthNum = 5;
	                break;
	            case "Jun":
	                monthNum = 6;
	                break;
	            case "Jul":
	                monthNum = 7;
	                break;
	            case "Aug":
	                monthNum = 8;
	                break;
	            case "Sep":
	                monthNum = 9;
	                break;
	            case "Oct":
	                monthNum = 10;
	                break;
	            case "Nov":
	                monthNum = 11;
	                break;
	            case "Dec":
	                monthNum = 12;
	                break;
	            default: 
	                monthNum = 0;
	        }

	        return monthNum;
	    }
	
	
	@Override
	public int compareTo(ITemperature that) 
	{
		// First compare temperature, then country, then year, and then month
		if (this.getTemperature(false) != that.getTemperature(false))
			return (int)Math.signum(this.getTemperature(false) - that.getTemperature(false));
		
		else if (this.getCountry().compareTo(that.getCountry()) != 0)
			return this.getCountry().compareTo(that.getCountry());
		
		else if (this.getYear() != that.getYear())
			return (int)Math.signum(this.getYear() - that.getYear());
		
		else if (this.monthToInt(this.getMonth()) - this.monthToInt(that.getMonth()) != 0)
			return (int)Math.signum(this.monthToInt(this.getMonth()) - this.monthToInt(that.getMonth()));

		return 0;
	}
	
	
	// This method is supposed to round the temperature to 2 decimal places.
	public double roundTemperature(double temperature)
	{
		return Math.round(temperature * 100.00) / 100.00;
	}
	
	
	// Writes temperature as (celsiusTemp(C) fahrenheit(F),year,month,country,countryCode)
	@Override
	public String toString()
	{
		return roundTemperature(this.getTemperature(false)) + "(C) " + roundTemperature(this.getTemperature(true)) + "(F)," + this.getYear() + "," + this.getMonth() + "," + this.getCountry() + "," + this.getCountry3LetterCode();
	}
	
	
	// Returns the hash code of the ITemperature
	public int hashCode()
	{
		return (int)this.getTemperature(false) + this.getYear() + this.getMonth().hashCode() + this.getCountry().hashCode() + this.getCountry3LetterCode().hashCode();
	}
	
	
	public static void main(String[] args)
	{
		ITemperature test = new Temperature(25, 2003, "May", "China", "CHI");
		ITemperature test2 = new Temperature(25, 2003, "May", "China", "CHI");
		System.out.println(test.equals(test2));
//		ArrayList<ITemperature> aaa = new ArrayList<>();
//		aaa.add(test);
//		aaa.add(test2);
//		Collections.sort(aaa);
//		for (ITemperature i : aaa)
//			System.out.println(i.toString());
	}
}
