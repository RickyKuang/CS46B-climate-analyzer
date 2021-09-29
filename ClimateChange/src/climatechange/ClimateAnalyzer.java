package climatechange;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class ClimateAnalyzer implements IClimateAnalyzer
{
	private WeatherIO weather;
	private ArrayList<ITemperature> dataAL;
	private HashMap<Integer, String> monthMap;
	
	// Constructor takes a String, which is the file, as an argument
	// When constructed, a WeatherIO object is constructed, and a new array list with the given file's data is constructed as well
	// A hash map for months is also created, with the numbers 1-12 representing the months January-December
	public ClimateAnalyzer(String fileName)
	{
		this.weather = new WeatherIO();
		this.dataAL = weather.readDataFromFile(fileName);
		this.monthMap = new HashMap<Integer, String>();
		monthMap.put(1, "Jan");
		monthMap.put(2, "Feb");
		monthMap.put(3, "Mar");
		monthMap.put(4, "Apr");
		monthMap.put(5, "May");
		monthMap.put(6, "Jun");
		monthMap.put(7, "Jul");
		monthMap.put(8, "Aug");
		monthMap.put(9, "Sep");
		monthMap.put(10, "Oct");
		monthMap.put(11, "Nov");
		monthMap.put(12, "Dec");
	}
	
	
	// TASK A-1
	// for all data that matches the specified month, get the lowest temperature reading 
	public ITemperature getLowestTempByMonth(String country, int month)
	{
		String filename = "data/taskA1_climate_info.csv"; // name of the file that is created
		String topic = "Temperature,Year,Month,Country,Country_Code"; // fills the topic parameter for writeDataToFile
		String subject = "Task A1.1: Lowest Temperature for " + country + " for the Month of " + monthMap.get(month); // subject of the task
		
		// Iterate through all the data and look for whatever matches the inputed country and month.
		// If it's the first ITemperature to meet the parameters, it is automatically set to lowestTempMonth.
		// If the temperature of the ITemperature being examined is lower than the temperature of the lowestTempMonth,
		// then the examined ITemperature is set as lowestTempMonth.
		ITemperature lowestTempMonth = null;
		for (ITemperature i : dataAL)
		{
			if (i.getCountry().equalsIgnoreCase(country) && i.getMonth().equals(monthMap.get(month)))
			{
				if (lowestTempMonth == null || i.getTemperature(false) < lowestTempMonth.getTemperature(false))
					lowestTempMonth = i;
			}
		}
		
		// Create an array list and add lowestTempMonth. It will be added to a new file.
		ArrayList<ITemperature> lowestTempMonthAL = new ArrayList<>();
		lowestTempMonthAL.add(lowestTempMonth);
		
		// Adds the subject header to the file, then appends the data.
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, lowestTempMonthAL);
		
		return lowestTempMonth;
	}
	
	
	// TASK A-1
	// for all data that matches the specified month, get the highest temperature reading
	public ITemperature getHighestTempByMonth(String country, int month)
	{
		String filename = "data/taskA1_climate_info.csv"; // name of file that is created
		String topic = "Temperature,Year,Month,Country,Country_Code"; // topic parameter for writeDataToFile
		String subject = "Task A1.2: Highest Temperature for " + country + " for the Month of " + monthMap.get(month); // subject of given task
		
		// Iterate through all the data and look for whatever matches the inputed country and month.
		// If it's the first ITemperature to meet the parameters, it is automatically set to highestTempMonth.
		// If the temperature of the ITemperature being examined is higher than the temperature of the highestTempMonth,
		// then the examined ITemperature is set as highestTempMonth.
		ITemperature highestTempMonth = null;
		for (ITemperature i : dataAL)
		{
			if (i.getCountry().equalsIgnoreCase(country) && i.getMonth().equals(monthMap.get(month)))
			{
				if (highestTempMonth == null || i.getTemperature(false) > highestTempMonth.getTemperature(false))
					highestTempMonth = i;
			}
		}
		
		// Create an array list and add highestTempMonth. It will be added to a new file.
		ArrayList<ITemperature> highestTempMonthAL = new ArrayList<>();
		highestTempMonthAL.add(highestTempMonth);
		
		// Writes subject header into the file, then appends the data.
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, highestTempMonthAL);
		
		return highestTempMonth;
	}
	
	
	// TASK A-2
	// for all data that matches the specified year, get the lowest temperature reading
	public ITemperature getLowestTempByYear(String country, int year)
	{
		String filename = "data/taskA2_climate_info.csv"; // file that gets created from task
		String topic = "Temperature,Year,Month,Country,Country_Code"; // fills the topic parameter
		String subject = "Task A2.1: Lowest Temperature for " + country + " in the Year " + year; // subject of the given task
		
		// Iterate through all the data and look for whatever matches the inputed country and year.
		// If it's the first ITemperature that meets the requirements, then that's automatically set to lowestTempYear.
		// If the temperature of the ITemperature being examined is lower than the temperature of the lowestTempYear,
		// then the examined ITemperature is set as lowestTempYear.
		ITemperature lowestTempYear = null;
		for (ITemperature i : dataAL)
		{
			if (i.getCountry().equalsIgnoreCase(country) && i.getYear() == year)
			{
				if (lowestTempYear == null || i.getTemperature(false) < lowestTempYear.getTemperature(false)) // this is meant for the first temp object that meets the requirements
					lowestTempYear = i;     // this sets the first temp object to lowestTemp
			}
		}
		
		// Create an array list and add lowestTempYear. It will be added to a new file.
		ArrayList<ITemperature> lowestTempYearAL = new ArrayList<>();
		lowestTempYearAL.add(lowestTempYear);
		
		// Adds the subject header to the file, then appends all the data.
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, lowestTempYearAL);
		
		return lowestTempYear;
	}
	
	
	// TASK A-2
	// for all data that matches the specified year, get the highest temperature reading
	public ITemperature getHighestTempByYear(String country, int year)
	{
		String filename = "data/taskA2_climate_info.csv";
		String topic = "Temperature,Year,Month,Country,Country_Code";
		String subject = "Task A2.2: Highest Temperature for " + country + " in the Year " + year;
		
		// Iterate through all the data and look for whatever matches the inputed country and year.
		// If it's the first ITemperature to meet the parameters, it is automatically set to highestTempYear.
		// If the temperature of the ITemperature being examined is higher than the temperature of the highestTempYear,
		// then the examined ITemperature is set as highestTempYear.
		ITemperature highestTempYear = null;
		for (ITemperature i : dataAL)
		{
			if (i.getCountry().equalsIgnoreCase(country) && i.getYear() == year)
			{
				if (highestTempYear == null || i.getTemperature(false) > highestTempYear.getTemperature(false)) // this is meant for the first temp object that meets the requirements
					highestTempYear = i;
			}
		}
		
		// Create an array list and add highestTempYear. It will be added to a new file.
		ArrayList<ITemperature> highestTempYearAL = new ArrayList<>();
		highestTempYearAL.add(highestTempYear);
		
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, highestTempYearAL);
		
		return highestTempYear;
	}
	
	
	// TASK A-3
	// get all temperature data that fall within the given temperature range
	// the set is sorted from lowest to highest temperature
	// input parameter values are in Celsius
	public TreeSet<ITemperature> getTempWithinRange(String country, double rangeLowTemp, double rangeHighTemp)
	{
		String filename = "data/taskA3_climate_info.csv";
		String topic = "Temperature,Year,Month,Country,Country_Code";
		String subject = "Task A3: Temperatures for " + country + " within " + rangeLowTemp + " and " + rangeHighTemp + " (2000-2016)";
		
		// Iterate through all the data and check which ITemperatures meet the requirements.
		// Has to be inputed country and fall within inputed temperatures.
		// Those that meet the requirements will get added to an array list.
		ArrayList<ITemperature> dataWithinRangeAL = new ArrayList<>();
		for (ITemperature i : dataAL)
		{
			if (i.getCountry().equalsIgnoreCase(country) && i.getTemperature(false) >= rangeLowTemp && i.getTemperature(false) <= rangeHighTemp)
				dataWithinRangeAL.add(i);
		}
		
		// Create a TreeSet that takes the array list as an argument.
		TreeSet<ITemperature> sortedDataWithinRangeTS = new TreeSet<>(dataWithinRangeAL);
		
		// Create an array list that takes the TreeSet as an argument to write to a file.
		ArrayList<ITemperature> sortedDataWithinRangeAL = new ArrayList<>(sortedDataWithinRangeTS);
		
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, sortedDataWithinRangeAL);
		
		return sortedDataWithinRangeTS;
	}
	
	
	// TASK A-4
	// 1. get the lowest temperature reading amongst all data for that country
	public ITemperature getLowestTempYearByCountry(String country)
	{
		String filename = "data/taskA4_climate_info.csv";
		String topic = "Temperature,Year,Month,Country,Country_Code";
		String subject = "Task A4.1: Lowest Temperature for " + country + " (2000-2016)";
		
		// Iterate through all the data. 
		// If an ITemperature matches the requirement (the inputed country) and it's the first, then it's automatically set
		// to lowestTempYearByCountry. If the temperature of the ITemperature being examined is lower than the temperature of 
		// lowestTempYearByCountry, then lowestTempYearByCountry will be set to the examined ITemperature.
		ITemperature lowestTempYearByCountry = null;
		for (ITemperature i : dataAL)
		{
			if (i.getCountry().equalsIgnoreCase(country))
			{
				if (lowestTempYearByCountry == null || i.getTemperature(false) < lowestTempYearByCountry.getTemperature(false))
					lowestTempYearByCountry = i;
			}
		}
		
		// Create an array list and add lowestTempYearByCountry. It will be added to a new file.
		ArrayList<ITemperature> lowestTempYearByCountryAL = new ArrayList<>();
		lowestTempYearByCountryAL.add(lowestTempYearByCountry);
		
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, lowestTempYearByCountryAL);
		
		return lowestTempYearByCountry;
	}
	
	
	// TASK A-4
	// 1. get the highest temperature reading amongst all data for that country
	public ITemperature getHighestTempYearByCountry(String country)
	{
		String filename = "data/taskA4_climate_info.csv";
		String topic = "Temperature,Year,Month,Country,Country_Code";
		String subject = "Task A4.2: Highest Temperature for " + country + " (2000-2016)";
		
		// Iterate through all the data. 
		// If an ITemperature matches the requirement (the inputed country) and it's the first, then it's automatically set
		// to highestTempYearByCountry. If the temperature of the ITemperature being examined is higher than the temperature of 
		// highestTempYearByCountry, then highestTempYearByCountry will be set to the examined ITemperature.
		ITemperature highestTempYearByCountry = null;
		for (ITemperature i : dataAL)
		{
			if (i.getCountry().equalsIgnoreCase(country))
			{
				if (highestTempYearByCountry == null || i.getTemperature(false) > highestTempYearByCountry.getTemperature(false))
					highestTempYearByCountry = i;
			}
		}
		
		// Create an array list and add highestTempYearByCountry. It will be added to a new file.
		ArrayList<ITemperature> highestTempYearByCountryAL = new ArrayList<>();
		highestTempYearByCountryAL.add(highestTempYearByCountry);
		
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, highestTempYearByCountryAL);
		
		return highestTempYearByCountry;
	}
	
	
	// TASK B-1
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp(int month)
	{
		String filename = "data/taskB1_climate_info.csv";
		String topic = "Temperature,Year,Month,Country,Country_Code";
		String subject = "Task B1.1: Top 10 Countries with the Lowest Temperatures in " + monthMap.get(month) + " (2000-2016)";
		
		// Run through all the data. If the ITemperature meets the requirements (the inputed month) then it's added to
		// an array list tempGivenMonthAL.
		ArrayList<ITemperature> tempGivenMonthAL = new ArrayList<>();
		for (ITemperature i : dataAL)
		{
			if (i.getMonth().equals(monthMap.get(month))) 
				tempGivenMonthAL.add(i);
		}
		
		Collections.sort(tempGivenMonthAL); // The array list is sorted so that temperatures are from low to high.
		ArrayList<ITemperature> top10LowAL = new ArrayList<>(); // This array list will hold the 10 ITemperatures with the lowest temperatures.
		HashSet<String> countriesAdded = new HashSet<>(); // This array will hold the countries already examined so that no country is repeated.
		
		// Run through tempGivenMonthAL. If the ITemperature being examined isn't a country that's already looked at, then it will get added to top10LowAL.
		// Once top10LowAL has 10 ITemperatures, the for loop will break.
		for (ITemperature t : tempGivenMonthAL)
		{
			if (countriesAdded.contains(t.getCountry()) == false)
			{
				top10LowAL.add(t);
				countriesAdded.add(t.getCountry());
				if (top10LowAL.size() == 10)
					break;
			}
		}
		
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, top10LowAL);
		
		return top10LowAL;
	}
	
	
	// TASK B-1
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp(int month)
	{
		String filename = "data/taskB1_climate_info.csv";
		String topic = "Temperature,Year,Month,Country,Country_Code";
		String subject = "Task B1.2: Top 10 Countries with the Highest Temperatures in " + monthMap.get(month) + " (2000-2016)";
		
		// Run through all the data. If the ITemperature meets the requirements (the inputed month) then it's added to
		// an array list tempGivenMonthAL.
		ArrayList<ITemperature> tempGivenMonthAL = new ArrayList<>();
		for (ITemperature i : dataAL)
		{
			if (i.getMonth().equals(monthMap.get(month))) 
				tempGivenMonthAL.add(i);
		}
		
		Collections.sort(tempGivenMonthAL); // The array list is sorted so that temperatures are from low to high.
		ArrayList<ITemperature> top10HighAL = new ArrayList<>(); // This array list will hold the 10 ITemperatures with the highest temperatures.
		HashSet<String> countriesAdded = new HashSet<>(); // This array will hold the countries already examined so that no country is repeated.
		
		// Iterate through tempGivenMonthAL backwards. If the ITemperature being examined isn't a country that's already looked at, 
		// then it will get added to top10HighAL. The country of the added ITemperature is added to countriesAdded so that no couuntry is repeated.
		// Once top10HighAL has 10 ITemperatures, the for loop will break.
		for (int i = tempGivenMonthAL.size()-1; i > 0; i--)
		{
			ITemperature current = tempGivenMonthAL.get(i);
			if (countriesAdded.contains(current.getCountry()) == false)
			{
				top10HighAL.add(current);
				countriesAdded.add(current.getCountry());
				if (top10HighAL.size() == 10)
					break;
			}
		}
		
		Collections.reverse(top10HighAL); // top10HighAL is then reversed since the temperatures must be sorted from low to high.
		
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, top10HighAL);
		
		return top10HighAL;
	}
	
	
	// TASK B-2
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp()
	{
		String filename = "data/taskB2_climate_info.csv";
		String topic = "Temperature,Year,Month,Country,Country_Code";
		String subject = "Task B2.1: Top 10 Countries with the Lowest Temperatures (2000-2016)";
		
		TreeSet<ITemperature> sortedDataTS = new TreeSet<>(dataAL); // Sort all the data with a TreeSet
		ArrayList<ITemperature> sortedDataAL = new ArrayList<>(sortedDataTS); // Create an array list with the tree set as the argument.
		HashSet<String> countriesAdded = new HashSet<>(); // This array list will hold the countries that have already been examined.
		ArrayList<ITemperature> top10LowTempAL = new ArrayList<>(); // This array will hold the ITemperatures with the lowest temperatures.
		
		// Run through all the data. If the ITemperature being examined isn't a country that's already looked at, then it will get added to top10HighAL.
		// The country of the added ITemperature is added to countriesAdded so that the countries won't repeat.
		// Once top10HighAL has 10 ITemperatures, the for loop will break.
		for (ITemperature i : sortedDataAL)
		{
			if (countriesAdded.contains(i.getCountry()) == false)
			{
				top10LowTempAL.add(i);
				countriesAdded.add(i.getCountry());
				if (top10LowTempAL.size() == 10)
					break;
			}
		}
		
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, top10LowTempAL);
		
		return top10LowTempAL;
	}
	
	
	// TASK B-2
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp()
	{
		String filename = "data/taskB2_climate_info.csv";
		String topic = "Temperature,Year,Month,Country,Country_Code";
		String subject = "Task B2.2: Top 10 Countries with the Highest Temperatures (2000-2016)";
		
		TreeSet<ITemperature> sortedDataTS = new TreeSet<>(dataAL); // Sort all the data with a Tree Set
		ArrayList<ITemperature> sortedDataAL = new ArrayList<>(sortedDataTS); // Store the TreeSet in an array list
		HashSet<String> countriesAdded = new HashSet<>(); // This array list will hold the countries that have already been examined.
		ArrayList<ITemperature> top10HighTempAL = new ArrayList<>(); // This array will hold the ITemperatures with the highest temperatures.
		
		// Iterate through data backwards. If the ITemperature being examined isn't a country that's already looked at, 
		// then it will get added to top10HighTempAL. The country of the added ITemperature is added to countriesAdded so that the countries won't repeat.
		// Once top10HighTempAL has 10 ITemperatures, the for loop will break.
		for (int i = sortedDataAL.size()-1; i > 0; i--)
		{
			ITemperature current = sortedDataAL.get(i);
			if (!countriesAdded.contains(current.getCountry()))
			{
				top10HighTempAL.add(current);
				countriesAdded.add(current.getCountry());
				if (top10HighTempAL.size() == 10)
					break;
			}
		}
		
		Collections.reverse(top10HighTempAL); // top10HighTempAL is reversed so that it's sorted from low to high
		
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, top10HighTempAL);
		
		return top10HighTempAL;
	}
	
	
	// TASK B-3
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetAllDataWithinTempRange(double lowRangeTemp, double highRangeTemp)
	{
		String filename = "data/taskB3_climate_info.csv";
		String topic = "Temperature,Year,Month,Country,Country_Code";
		String subject = "Task B3: Countries with Temperatures within " + lowRangeTemp + " and " + highRangeTemp + " (C)";
		
		ArrayList<ITemperature> allWithinRangeAL = new ArrayList<>(); // This array list will hold the ITemperatures that fall within the range.
		// Run through all the data. If the ITemperature is within range, then it's added to allWithinRangeAL.
		for (ITemperature i : dataAL)
		{
			if (i.getTemperature(false) >= lowRangeTemp && i.getTemperature(false) <= highRangeTemp)
				allWithinRangeAL.add(i);
		}
		
		Collections.sort(allWithinRangeAL); // allWithinRangeAL is sorted from low to high
		
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, allWithinRangeAL);
		
		return allWithinRangeAL;
	}
	
	
	// TASK C-1
	// 1. the countries with the largest temperature differences (absolute value) of the same month between 2 given years. 
	// 2. the return list is sorted from lowest to highest temperature delta
	public ArrayList<ITemperature> allCountriesTop10TempDelta(int month, int year1, int year2)
	{
		String filename = "data/taskC1_climate_info.csv";
		String topic = "Temperature,Year,Month,Country,Country_Code";
		String subject = "Task C1: Top 10 Countries with Biggest Temperature Delta in " + monthMap.get(month) + " (" + year1 + "-" + year2 + ")";
		
		ArrayList<ITemperature> validTempsAL = new ArrayList<>(); // This array list will hold the ITemperatures that meet the requirements (month, year1, year2).
		ArrayList<ITemperature> tempDeltaAL = new ArrayList<>(); // This array list will hold the newly created ITemperatures with the tempDelta and yearDifference.
		int yearDifference = Math.abs(year2-year1); // The difference between year1 and year2.
		double tempDelta = 0; // The difference between the temperatures of the 2 years.
		
		// Run through all the data. If the ITemperature meets the requirements, then it will get added to validTempsAL.
		for (ITemperature i : dataAL)
		{
			if (i.getMonth().equals(monthMap.get(month)) && (i.getYear() == year1 || i.getYear() == year2))
				validTempsAL.add(i);
		}
		// Run through validTempsAL. Increment by 2 since the temperatures of country in 2 years must be calculated.
		// Since one country's data for 2 years will be next to each other, can increment by 2 for each country
		// Then set two ITemperatures, one being the current i and the other being i+1
		for (int i = 0; i < validTempsAL.size(); i+=2)
		{
			ITemperature year1Temp = validTempsAL.get(i); // ITemperature for year1
			ITemperature year2Temp = validTempsAL.get(i+1); // ITemperature for year2
			tempDelta = Math.abs(year2Temp.getTemperature(false) - year1Temp.getTemperature(false)); // Temperature of year2Temp minus temperature of year1Temp
			
			// Create a new ITemperature that takes tempDelta as the temperature and yearDifference as the year parameters.
			ITemperature deltaTemp = new Temperature(tempDelta, yearDifference, year1Temp.getMonth(), year1Temp.getCountry(), year1Temp.getCountry3LetterCode());
			tempDeltaAL.add(deltaTemp); // The newly created ITemperature is added to tempDeltaAL.
		}
		
		Collections.sort(tempDeltaAL); // tempDeltaAL is sorted from low to high
		ArrayList<ITemperature> top10DeltaAL = new ArrayList<>();
		
		// Iterate through tempDeltaAL backwards to get the highest values first.
		// Once top10DeltaAL has 10 ITemperatures, the for loop will break.
		for (int i = tempDeltaAL.size()-1; i > 0; i--)
		{
			ITemperature current = tempDeltaAL.get(i);
			top10DeltaAL.add(current);
			if (top10DeltaAL.size() == 10)
				break;
		}
		
		Collections.reverse(top10DeltaAL); // Reverse top10DeltaAL so that it's sorted from low to high.
		
		weather.writeSubjectHeaderInFile(filename, subject);
		weather.writeDataToFile(filename, topic, top10DeltaAL);
		
		return top10DeltaAL;
	}

	
	// 1. This method starts the climate-change task activities
	// 2. The ClimateChange methods must be called in the order as listed in the [description section], (first with the Task A 
	//    methods, second are the Task B methods, and third are the Task C methods)
	// 3. For each of the ClimateChange methods that require input parameters, this method must ask the user to
	//    enter the required information for each of the tasks.
	// 4. Each ClimateAnalyzer method returns data, so the data results must be written to data files
	public void runClimateAnalyzer()
	{
		Scanner scanInput = new Scanner(System.in); // Initialize a scanner so the user can input things.
		
		// Brief introduction of the Climate Analyzer.
		System.out.println("This is the Climate Analyzer!\n\n"
				+ "We have data spanning from 2000-2016!\n"
				+ "You will be given a set of Tasks, some of which you must fill out yourself.\n"
				+ "Once you fill out the parameters, I will find the data for you!\n"
				+ "As we go through each task, I will write the collected data to a csv file.");
		
		// Asterisk lines indicate the start of a new task.
		// Hyphen lines indicate the start of the SECOND part of the task.
		System.out.println("\n******************************************************************************************************************************************");
	    
		
		///////////////////////////////////////////////////////
		////////////////////// Task A1 ////////////////////////
		///////////////////////////////////////////////////////
		System.out.println("\nLet's start with Task A1, which has two parts.");
		
		// Task A1.1 prompt
		System.out.println("\nTask A1.1: Find the LOWEST temperature reading for the MONTH of your choosing in the country of your choosing.");
		this.getLowestTempByMonth(this.inputForCountry(scanInput), this.inputForMonth(scanInput));
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
		
		// Task A1.2 prompt
		System.out.println("\nTask A1.2: Find the HIGHEST temperature of the MONTH of your choosing in the country of your choosing.");
		this.getHighestTempByMonth(this.inputForCountry(scanInput), this.inputForMonth(scanInput));
		System.out.println("\n******************************************************************************************************************************************");		
		
		
		///////////////////////////////////////////////////////
		////////////////////// Task A2 ////////////////////////
		///////////////////////////////////////////////////////
		System.out.println("\nLet's move on to Task A2, which has two parts.");
		
		// Task A2.1 prompt
		System.out.println("\nTask A2.1: Find the LOWEST temperature of the YEAR of your choosing in the country of your choosing.");
		this.getLowestTempByYear(this.inputForCountry(scanInput), this.inputForYear(scanInput, "a"));
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
		
		// Task A2.2 prompt
		System.out.println("\nTask A2.2: Find the HIGHEST temperature of the YEAR of your choosing in the country of your choosing.");
		this.getHighestTempByYear(this.inputForCountry(scanInput), this.inputForYear(scanInput, "a"));
		System.out.println("\n******************************************************************************************************************************************");		
		
		
		///////////////////////////////////////////////////////
		////////////////////// Task A3 ////////////////////////
		///////////////////////////////////////////////////////
		System.out.println("\nLet's move on to Task A3.");
		System.out.println("\nTask A3: Find the temperatures of a country of your choosing that fall within 2 temperatures (in Celsius) of your choosing.");
		String countryInput = this.inputForCountry(scanInput); // First ask user for country.
		
		// The following checks to make sure that the user inputs valid temperature ranges.
		boolean done = false;
		double lowerTempInput = 0;
		double higherTempInput = 0;
		
		while (!done) // Enter a while loop, exits when inputs are valid.
		{
			lowerTempInput = this.inputForTemperature(scanInput, "lower"); // First asks for the lower temperature
			higherTempInput = this.inputForTemperature(scanInput, "upper"); // Then asks for the upper temperature
			
			if (higherTempInput <= lowerTempInput) // The user is prompted to enter valid temperatures if they aren't already so
				System.out.println("\nPlease make sure that the first temperature is lower than the second temperature.");
			else
				done = true; // If the inputs are valid, then the while loop is exited
		}
		this.getTempWithinRange(countryInput, lowerTempInput, higherTempInput);
		System.out.println("\n******************************************************************************************************************************************");		
		
			
		///////////////////////////////////////////////////////
		////////////////////// Task A4 ////////////////////////
		///////////////////////////////////////////////////////
		System.out.println("\nLet's move on to Task A4, which has two parts.");
		
		// Task A4.1 prompt
		System.out.println("\nTask A4.1: Find the LOWEST Temperatures of the country of your choosing.");
		this.getLowestTempYearByCountry(this.inputForCountry(scanInput));
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
		
		// Task A4.2 prompt
		System.out.println("\nTask A4.2: Find the HIGHEST Temperatures of the country of you choosing.");
		this.getHighestTempYearByCountry(this.inputForCountry(scanInput));
		System.out.println("\n******************************************************************************************************************************************");		
		
		
		///////////////////////////////////////////////////////
		////////////////////// Task B1 ////////////////////////
		///////////////////////////////////////////////////////
		System.out.println("\nLet's move on to Task B1, which has two parts.");
		
		// Task B1.1 prompt
		System.out.println("\nTask B1.1: Find the countries with the LOWEST temperatures in a given MONTH");
		this.allCountriesGetTop10LowestTemp(this.inputForMonth(scanInput));
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
		
		// Task B1.2 prompt
		System.out.println("\nTask B1.2: Find the countries with the HIGHEST temperatures in a given MONTH");
		this.allCountriesGetTop10HighestTemp(this.inputForMonth(scanInput));
		System.out.println("\n******************************************************************************************************************************************");		
		
		
		
		///////////////////////////////////////////////////////
		////////////////////// Task B2 ////////////////////////
		///////////////////////////////////////////////////////
		// Task B2.1 prompt
		System.out.println("\nLet's move on to Task B2, which has two parts."
				+ "\nYou don't have to input anything, we have the data right here!"); // This is just to let the user know that no input is needed.
		// I implemented Thread.sleep to pause and let user know that no input is needed for B2
		try {
			Thread.sleep(3000);
		} catch (Exception e) {}
		
		// Task B2.1 prompt
		System.out.println("\nTask B2.1: Find the countries with the LOWEST temperatures");
		this.allCountriesGetTop10LowestTemp();
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
		
		// Task B2.2 prompt
		System.out.println("\nTask B2.2: Find the countries with the HIGHEST temperatures");
		this.allCountriesGetTop10HighestTemp();
		System.out.println("\n******************************************************************************************************************************************");		
		
				
		///////////////////////////////////////////////////////
		////////////////////// Task B3 ////////////////////////
		///////////////////////////////////////////////////////
		System.out.println("\nLet's move on to Task B3.");
		System.out.println("\nTask B3: Find the temperatures that fall within the range of your choosing.");
		
		// The following checks to make sure that the user inputs valid ranges.
		done = false;
		lowerTempInput = 0;
		higherTempInput = 0;
		
		while (!done) // Enters a while loop, exits when inputs are valid.
		{
			lowerTempInput = this.inputForTemperature(scanInput, "lower");
			higherTempInput = this.inputForTemperature(scanInput, "upper");
			
			if (higherTempInput < lowerTempInput) // Prompts the user to enter valid temperature if not done so already.
				System.out.println("\nPlease make sure that the first temperature is lower than the second temperature.");
			else
				done = true; // Exits the while loop when inputs are valid.
		}
		this.allCountriesGetAllDataWithinTempRange(lowerTempInput, higherTempInput);
		System.out.println("\n******************************************************************************************************************************************");		
		
		
		///////////////////////////////////////////////////////
		////////////////////// Task C1 ////////////////////////
		///////////////////////////////////////////////////////
		System.out.println("\nLet's move on to Task C1... Our last task!");
		System.out.println("\nTask C1: Find the Countries with the biggest temperature changes"
				+ " in the month of your choosing within the YEARS of your choosing.");
		
		int monthInput = this.inputForMonth(scanInput); // Asks the user to input a month.
		
		// The following ensures that the inputs for the years are valid.
		done = false;
		int lowerYear = 0;
		int upperYear = 0;
		
		while (!done) // Enter while loop, exits when inputs are valid.
		{
			lowerYear = this.inputForYear(scanInput, "the first");
			upperYear = this.inputForYear(scanInput, "the second");
			
			if (upperYear <= lowerYear) // If the second year is less than or equal to first year, inputs are invalid, must reenter.
				System.out.println("\nPlease make sure that the second year is later than the first year."
						+ "\nBut in case you were wondering, the highest delta temperature would be... 0.");
			else
				done = true; // Exits when inputs are valid.
		}
		this.allCountriesTop10TempDelta(monthInput, lowerYear, upperYear);
		
		// Closing program
		System.out.println("\nCongratulations! You've completed all the tasks.\nAll the data should have been written to new files."
				+ "\nThank you for using me!");
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////// These are additional methods that I added for runClimateAnalyzer() ////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// This method is for whenever the user has to input a country.
	public String inputForCountry(Scanner input)
	{
		boolean done = false;
		String country = null;
		System.out.println("\nEnter a country: "); // Prompts the user to enter a country.
		while (!done) // Enter while loop, exits when input valid
		{
			country = input.nextLine(); // country set to the user's input
			for (ITemperature i : dataAL) // goes through all the data to see if the country is in the records
			{
				if (country.equalsIgnoreCase(i.getCountry()))
					done = true; // if so, the while loop is exited
			}
			if (!done) // if the while loop has not been exited, let the user know that the input is invalid
				System.out.println("\n" + country + " isn't a country. Please enter a valid country.");
		}
		return country;
	}
	
	
	// This method is for whenever the user has to input a month
	public int inputForMonth(Scanner input)
	{
		boolean done = false;
		int month = 0;
		
		// I printed out the months and their corresponding numbers (in case the user doesn't remember)
		System.out.println("\n1. January"
				+ "\n2. February"
				+ "\n3. March"
				+ "\n4. April"
				+ "\n5. May"
				+ "\n6. June"
				+ "\n7. July"
				+ "\n8. August"
				+ "\n9. September"
				+ "\n10. October"
				+ "\n11. November"
				+ "\n12. December");
		System.out.println("Enter a month's corresponding number: "); // prompt user to input number
		while (!done) // enter while loop
		{
			// Tries to set month to the user's input (only works if it's an integer)
			// If it is, then it checks if it's a valid input (from 1 to 12)
			// If so, exit loop. If not, reenter
			// If the input isn't an integer, then the exception is caught.
			try {
				month = Integer.valueOf(input.nextLine());
				if (month >= 1 && month <= 12)
					done = true;
				else
					System.out.println("\nPlease enter a number between 1 and 12.");
			} catch (NumberFormatException e) {System.out.println("\nNot a valid input.");} 
		}
		return month;
	}
	
	
	// This method is for whenever the user has to input a year
	// First parameter is a scanner
	// I added the string parameter so that the user won't get confused about whether an upper or lower year is needed
	public int inputForYear(Scanner input, String upperOrLower)
	{
		boolean done = false;
		int year = 0;
		System.out.println("\nEnter " + upperOrLower + " year from 2000-2016: ");
		while(!done) // Enter loop
		{
			// Tries to set year to user's input (only works for integers)
			// If it is, then it checks if the input is valid (from 2000 to 2016)
			// If so, exit loop. If not, lets user know what years are valid and reenter
			// If input isn't integer, then exception is caught.
			try {
				year = Integer.valueOf(input.nextLine());
				if (year >= 2000 && year <= 2016)
					done = true;
				else
					System.out.println("\nSorry, we only have records from 2000-2016.");
			} catch (NumberFormatException e) {System.out.println("\nNot a valid input. Please enter a valid year.");}
		}
		return year;
	}
	
	
	// This method is for whenever a temperature must be inputed
	// First parameter is a scanner
	// I added the second parameter so the user won't get confused about whether an upper or lower temp is needed
	public double inputForTemperature(Scanner input, String upperOrLower)
	{
		boolean done = false;
		double temp = 0;
		System.out.println("\nEnter " + upperOrLower + " temperature (in Celsius): "); // Prompt user to input temperature
		while (!done)
		{
			// Tries to set temp to input (only works for double)
			// If it works, loop is exited
			// If not, the exception will be caught.
			try {
				temp = Double.valueOf(input.nextLine());
				done = true;
			} catch (NumberFormatException e) {System.out.println("\nNot a valid input. Please enter a valid temperature.");}
		}
		return temp;
	}

	
	public static void main(String[] args)
	{
		ClimateAnalyzer ca = new ClimateAnalyzer("data/world_temp_2000-2016.csv");
		
//		System.out.println("A1");
//		ca.getLowestTempByMonth("United States", 7);
//		System.out.println("------------------------------------------------------------------------");
//		
//		System.out.println("A1");
//		ca.getHighestTempByMonth("United States", 7);
//		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("A2");
		ca.getLowestTempByYear("United States", 2016);
		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("A2");
		ca.getHighestTempByYear("United States", 2016);
		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("A3");
		ca.getTempWithinRange("United States", 0, 30);
		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("A4");
		ca.getLowestTempYearByCountry("United States");
		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("A4");
		ca.getHighestTempYearByCountry("United States");
		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("B1");
		ca.allCountriesGetTop10LowestTemp(7);
		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("B1");
		ca.allCountriesGetTop10HighestTemp(7);
		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("B2");
		ca.allCountriesGetTop10LowestTemp();
		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("B2");
		ca.allCountriesGetTop10HighestTemp();
		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("B3");
		ca.allCountriesGetAllDataWithinTempRange(0, 30);
		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("C1");
		ca.allCountriesTop10TempDelta(7, 2006, 2007);
		System.out.println("------------------------------------------------------------------------");
		
//		ca.runClimateAnalyzer();
	}
}
