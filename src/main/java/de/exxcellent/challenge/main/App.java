package de.exxcellent.challenge.main;

import de.exxcellent.challenge.controller.Controller;
import de.exxcellent.challenge.controller.WeatherDataController;
import de.exxcellent.challenge.exceptions.InvalidDataException;
import de.exxcellent.challenge.exceptions.NoDataSourceAvailable;
import de.exxcellent.challenge.model.Model;
import de.exxcellent.challenge.model.WeatherDataModel;
import de.exxcellent.challenge.view.View;
import de.exxcellent.challenge.view.WeatherDataView;

import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    private Model weatherDataModel;
    private Controller weatherDataController;
    private View weatherDataView;

    public App(String path) {
        //Create all necessary components (Model, View and Controller) and connect them via dependency injection
        try {
            this.weatherDataModel = new WeatherDataModel(path);
        } catch (NoDataSourceAvailable e) {
            System.err.println("Could not access data source.");
            System.exit(1);
        }
        this.weatherDataController = new WeatherDataController(this.weatherDataModel);
        this.weatherDataView = new WeatherDataView(this.weatherDataController);
    }

    public static void main(String... args) {
        //Instantiate the app and run the app logic
        App app = new App("src/main/resources/de/exxcellent/challenge/weather.csv");
        app.run();
    }

    /**
     * Method containing the app logic
     */
    private void run() {
        try {
            this.weatherDataView.printWeatherDataTempSpreadMax();
        } catch (InvalidDataException e) {
            System.err.println("Data has invalid format.");
            System.exit(2);
        }
    }

}
