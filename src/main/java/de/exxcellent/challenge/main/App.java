package de.exxcellent.challenge.main;

import de.exxcellent.challenge.controller.Controller;
import de.exxcellent.challenge.controller.FootballDataController;
import de.exxcellent.challenge.controller.WeatherDataController;
import de.exxcellent.challenge.enities.FootballDataPoint;
import de.exxcellent.challenge.enities.WeatherDataPoint;
import de.exxcellent.challenge.exceptions.InvalidDataException;
import de.exxcellent.challenge.exceptions.NoDataSourceAvailable;
import de.exxcellent.challenge.model.FootballDataModel;
import de.exxcellent.challenge.model.Model;
import de.exxcellent.challenge.model.WeatherDataModel;
import de.exxcellent.challenge.view.FootballDataView;
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

    private Model<List<WeatherDataPoint>> weatherDataModel;
    private Model<List<FootballDataPoint>> footballDataModel;
    private Controller<Integer> weatherDataController;
    private Controller<String> footballDataController;
    private View weatherDataView;
    private View footballDataView;

    public App(String pathWeatherData, String pathFootballData) {
        //Create all necessary components for the weather data functionality(Model, View and Controller) and
        //connect them via dependency injection
        try {
            this.weatherDataModel = new WeatherDataModel(pathWeatherData);
        } catch (NoDataSourceAvailable e) {
            System.err.println("Could not access weather data source.");
            System.exit(1);
        }
        this.weatherDataController = new WeatherDataController(this.weatherDataModel);
        this.weatherDataView = new WeatherDataView(this.weatherDataController);

        //Create all necessary components for the football data functionality(Model, View and Controller) and
        //connect them via dependency injection
        try {
            this.footballDataModel = new FootballDataModel(pathFootballData);
        } catch (NoDataSourceAvailable e) {
            System.err.println("Could not access football data source.");
            System.exit(1);
        }
        this.footballDataController = new FootballDataController(this.footballDataModel);
        this.footballDataView = new FootballDataView(this.footballDataController);
    }

    public static void main(String... args) {
        //Instantiate the app and run the app logic
        App app = new App("src/main/resources/de/exxcellent/challenge/weather.csv",
        "src/main/resources/de/exxcellent/challenge/football.csv");
        app.run();
    }

    /**
     * Method containing the app logic
     */
    private void run() {
        try {
            this.weatherDataView.printData();
            this.footballDataView.printData();
        } catch (InvalidDataException e) {
            System.err.println("Data has invalid format.");
            System.exit(2);
        }
    }

}
