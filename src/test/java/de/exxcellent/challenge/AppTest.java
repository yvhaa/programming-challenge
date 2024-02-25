package de.exxcellent.challenge;

import de.exxcellent.challenge.controller.Controller;
import de.exxcellent.challenge.controller.FootballDataController;
import de.exxcellent.challenge.controller.WeatherDataController;
import de.exxcellent.challenge.enities.FootballDataPoint;
import de.exxcellent.challenge.enities.WeatherDataPoint;
import de.exxcellent.challenge.exceptions.InvalidDataException;
import de.exxcellent.challenge.model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {


    private Controller<Integer> weatherController1;
    private Controller<Integer> weatherController2;
    private Controller<String> footballController1;
    private Controller<String> footballController2;




    @BeforeEach
    void setUp() {
        //Setup a weather model for to test if the weather controller finds the day with the maximum temperature spread
        Model<List<WeatherDataPoint>> weatherMockBackend1 = new Model<List<WeatherDataPoint>>() {
            @Override
            public List<WeatherDataPoint> getData() {
                return new ArrayList<>(Arrays.asList(
                        new WeatherDataPoint(1, 30, 20),
                        new WeatherDataPoint(2, 25, 20),
                        new WeatherDataPoint(3, 25, 5),
                        new WeatherDataPoint(4, 40, 10),
                        new WeatherDataPoint(5, 20, 25),
                        new WeatherDataPoint(6, 25, 20)
                ));
            }
        };

         //Setup a weather model to test if the weather controller deals with no available data correctly
        Model<List<WeatherDataPoint>> weatherMockBackend2 = new Model<List<WeatherDataPoint>>() {
            @Override
            public List<WeatherDataPoint> getData() {
                return new ArrayList<>();
            }
        };

        this.weatherController1 = new WeatherDataController(weatherMockBackend1);
        this.weatherController2 = new WeatherDataController(weatherMockBackend2);


        //Setup a football model for to test if the football controller finds the team with the lowest goal difference
        Model<List<FootballDataPoint>> footballMockBackend1 = new Model<List<FootballDataPoint>>() {
            @Override
            public List<FootballDataPoint> getData() {
                return new ArrayList<>(Arrays.asList(
                        new FootballDataPoint("a", 120, 100),
                        new FootballDataPoint("b", 100, 110),
                        new FootballDataPoint("c", 180, 100),
                        new FootballDataPoint("d", 200, 100)
                ));
            }
        };

        //Setup a football model to test if the football controller deals with no available data correctly
        Model<List<FootballDataPoint>> footballMockBackend2 = new Model<List<FootballDataPoint>>() {
            @Override
            public List<FootballDataPoint> getData() {
                return new ArrayList<>();
            }
        };

        this.footballController1 = new FootballDataController(footballMockBackend1);
        this.footballController2 = new FootballDataController(footballMockBackend2);

    }

    /**
     * Test to see if the weather controller correctly deals with no available data by throwing a InvalidDataException
     */
    @Test
    void testMaximumTempSpreadOfEmptyArray() {
        assertThrows(InvalidDataException.class, () -> {this.weatherController2.calculate();});
    }

    /**
     * Test to see if the weather controller correctly finds the number of the day with the maximum temperature spread
     * even if that data includes cases where MnT > MxT
     */
    @Test
    void testMaximumTempSpreadOfArray() {
        try {
            assertEquals(4, this.weatherController1.calculate());
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test to see if the football controller correctly deals with no available data by throwing a InvalidDataException
     */
    @Test
    void testMinimumGoalDifferenceEmptyArray() {
        assertThrows(InvalidDataException.class, () -> {this.footballController2.calculate();});
    }

    /**
     * Test to see if the football controller correctly finds the team with the lowest absolute goal difference.
     */
    @Test
    void testMinimumGoalDifference() {
        try {
            assertEquals("b", this.footballController1.calculate());
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        }
    }

}