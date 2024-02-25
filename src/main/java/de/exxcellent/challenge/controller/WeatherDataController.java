package de.exxcellent.challenge.controller;

import de.exxcellent.challenge.enities.WeatherDataPoint;
import de.exxcellent.challenge.exceptions.InvalidDataException;
import de.exxcellent.challenge.model.Model;

import java.util.List;

public class WeatherDataController implements Controller{

    private final Model weatherDatamodel;

    public WeatherDataController(final Model model) {
        this.weatherDatamodel = model;
    }

    /**
     * Method to calculate the day with the maximum spread in temperature
     * @return the number of the day with the highest spread in temprature
     * @throws InvalidDataException when no maximum can be found, e.g. if the list of data points is empty
     */
    @Override
    public int getDayWithMaxTempSpread() throws InvalidDataException{
        List<WeatherDataPoint> dataPoints = this.weatherDatamodel.getWeatherData();
        return dataPoints.stream().max((var dp1, var dp2) -> {
            int tempDiffDp1 = dp1.getMaxTemp() - dp1.getMinTemp();
            int tempDiffDp2 = dp2.getMaxTemp() - dp2.getMinTemp();
            return Integer.compare(tempDiffDp1, tempDiffDp2);
        }).map(WeatherDataPoint::getNumOfDay).orElseThrow(() -> new InvalidDataException("No Maximum Could be found"));
    }
}
