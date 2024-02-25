package de.exxcellent.challenge.view;

import de.exxcellent.challenge.controller.Controller;
import de.exxcellent.challenge.exceptions.InvalidDataException;

public class WeatherDataView implements View{

    private Controller controller;

    public WeatherDataView(Controller controller) {
        this.controller = controller;
    }


    /**
     * This method prints the number of the day with the highest spread in temperature
     * @throws InvalidDataException when the Controller::getDayWithMaxTempSpread() method throws an InvalidDataException
     */
    @Override
    public void printWeatherDataTempSpreadMax() throws InvalidDataException {
        System.out.println(this.controller.getDayWithMaxTempSpread());
    }
}
