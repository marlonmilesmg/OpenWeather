package za.co.marlonmagonjo.openweather;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherDataFiveDayModel {

    // Member variables that hold our relevant weather information.
    private String mTemperature;
    private String mCity;
    private String mIconName;
    private int mCondition;

    // Create a WeatherDataModel from a JSON.
    // We will call this instead of the standard constructor.
    public static WeatherDataFiveDayModel fromJson(JSONObject jsonObject) {

        // JSON parsing is risky business. Need to surround the parsing code with a try-catch block.
        try {
            WeatherDataFiveDayModel weatherDataFive = new WeatherDataFiveDayModel();

            for(int i = 1; i<40; i++){

                ArrayList<String> fiveDays = new ArrayList<>();
                double tempResult = jsonObject.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp") - 273.15;
                int roundedValue = (int) Math.rint(tempResult);
                weatherDataFive.mTemperature = Integer.toString(roundedValue);

                fiveDays.add(Integer.toString(roundedValue));
                i+=8;

                System.out.println("iii counter : "+ i);
                System.out.println("days in counter are : "+fiveDays);
                System.out.println("weather forecast : "+weatherDataFive.mTemperature);
            }

            return weatherDataFive;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get the weather image name from OpenWeatherMap's condition (marked by a number code)
    private static String updateWeatherIcon(int condition) {

        if (condition >= 0 && condition < 300) {
            return "tstorm1";
        } else if (condition >= 300 && condition < 500) {
            return "light_rain";
        } else if (condition >= 500 && condition < 600) {
            return "shower3";
        } else if (condition >= 600 && condition <= 700) {
            return "snow4";
        } else if (condition >= 701 && condition <= 771) {
            return "fog";
        } else if (condition >= 772 && condition < 800) {
            return "tstorm3";
        } else if (condition == 800) {
            return "sunny";
        } else if (condition >= 801 && condition <= 804) {
            return "cloudy2";
        } else if (condition >= 900 && condition <= 902) {
            return "tstorm3";
        } else if (condition == 903) {
            return "snow5";
        } else if (condition == 904) {
            return "sunny";
        } else if (condition >= 905 && condition <= 1000) {
            return "tstorm3";
        }

        return "dunno";
    }

    // Getter methods for temperature, city, and icon name:
    public String[] getTemperature() {
        return new String[]{mTemperature + "°"};
    }

    public String getCity() {
        return mCity;
    }

    public String getIconName() {
        return mIconName;
    }
}
