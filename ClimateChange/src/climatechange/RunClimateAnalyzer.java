package climatechange;

public class RunClimateAnalyzer 
{
	public static void main(String[] args)
	{
		ClimateAnalyzer ca = new ClimateAnalyzer("data/world_temp_2000-2016.csv");
		ca.runClimateAnalyzer();
	}
}
