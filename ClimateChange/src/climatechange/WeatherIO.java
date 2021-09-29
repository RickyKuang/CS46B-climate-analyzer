package climatechange;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WeatherIO implements IWeatherIO
{
	// read all data from the csv file
	public ArrayList<ITemperature> readDataFromFile(String fileName)
	{
		ArrayList<ITemperature> allData = new ArrayList<>(); // array list that will hold all the data from the file
		
		try {
			File dataFile = new File(fileName); // Creates a new file with the given parameter
			Scanner readFile = new Scanner(dataFile); // Scans the file
			readFile.nextLine(); // skips the subject header
			while(readFile.hasNextLine()) // Enters loop while there are lines to scan, exits when no more
			{
				String dataLine = readFile.nextLine(); // Sets dataLine to the line being read.
				String[] dataArray = dataLine.split(", "); // Separates dataLine at the comma.
				// I'm not sure if this is supposed to be the case for everyone, but I need to add a space after the comma
				// in order to read the file properly and not run into any errors.
				
				// setting variables to each index of the array
				double temp = Double.parseDouble(dataArray[0]);
				int year = Integer.parseInt(dataArray[1]);
				String month = dataArray[2];
				String country = dataArray[3];
				String letterCode = dataArray[4];
				
				// create a new Temperature object with above variables and add to array list
				Temperature dataTemp = new Temperature(temp, year, month, country, letterCode);
				allData.add(dataTemp);
			}
			readFile.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allData;
	}
	

	public void writeSubjectHeaderInFile(String filename, String subject)
	// 1. write the subject header before dumping data returned from each ClimateAnalyzer method
	// 2. a subject header is to be written for each ClimateAnalyzer method call
	{
		try {
			File csvFile = new File(filename);
			if (csvFile.exists())
				System.out.println("Data will be appended to existing file."); // prints if the file already exists
			else if (!csvFile.exists())
				System.out.println("New file " + filename + " will be created."); // prints if the file has to be created
			
			FileWriter fw = new FileWriter(filename, true);
			fw.append(subject); // appends the subject into the csv file
			fw.append("\n"); // skips to the next line so that the data doesn't go on the same line
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void writeDataToFile(String filename, String topic, ArrayList<ITemperature> theWeatherList)
	// 1. file name should be called “taskXX_climate_info.csv” where XX will be replaced by the task id: A1, A2, etc
	// 2. use this method to store the temperature info(for each ClimateAnalyzer task)
	//     a) one row for each temperature data object (i.e. all fields in one row (each comma delimited))
	//     b) similar to the original input data file)
	// 3. temperature value should be formatted to use a maximum of2decimal places
	// 4.temperature field should also show the Fahrenheit value (using decimal rules above)
	//     a) the temperature field should look like i.e. 21.34(C) 70.42(F)
	{
		try {
			FileWriter fw = new FileWriter(filename, true); // true so that the file appends rather than overwrites
			fw.append(topic); // appends the topic line
	        fw.append("\n"); // goes to next line so data doesn't overlap
			
	        // for loop to get the elements and append to the file
			for (ITemperature i : theWeatherList) 
			{
				fw.append(i.toString()); // appends the data to the file
				fw.append("\n"); // goes to next line so data doesn't overlap
			}
			
			fw.append("\n"); // places a space in between new data
			fw.flush();
			fw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main (String[] args)
	{
		String apple = "apple   ";
		System.out.println(apple.trim() + "pie");
//		WeatherIO ha = new WeatherIO();
//		String file = "data/world_temp_2000-2016.csv";
//		ArrayList<ITemperature> dataList = ha.readDataFromFile(file);
//		
//		ArrayList<ITemperature> first = new ArrayList<>();
//		for (ITemperature i : dataList)
//		{
//			first.add(i);
//			if (first.size() == 12)
//				break;
//		}
//		
//		String first12 = "data/first_twelve.csv";
//		ha.writeSubjectHeaderInFile(first12, "First Twelve");
//		ha.writeDataToFile(first12, "Nothing", first);
//		// temp, year, month, country, code
//		for (int i = 0; i < 12; i++)
//		{
//			ITemperature a = dataList.get(i);
//			System.out.println(a.toString());
//		}
//		
//		ArrayList<ITemperature> data = new ArrayList<>();
//		
//		ITemperature ob1 = new Temperature(2, 2, "May", "Canada","CAN");
//		ITemperature ob2 = new Temperature(2, 2, "Jun", "Canada","CAN");
//		data.add(ob2);
//		data.add(ob1);
//		
//		Collections.sort(data);
//		for (ITemperature i : data)
//			System.out.println(i.toString());
	}
}
