package de.exxcellent.challenge;

import de.exxcellent.challenge.controller.Controller;
import de.exxcellent.challenge.controller.WeatherDataController;
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
    private Model mockBackend1;
    private Model mockBackend2;

    private Controller controller1;

    private Controller controller2;



    @BeforeEach
    void setUp() {
        //Setup a Model for to test if the controller finds the day with the maximum temperature spread
         this.mockBackend1 = new Model<List<WeatherDataPoint>>() {
            @Override
            public List<WeatherDataPoint> getData() {
                return new ArrayList<>(Arrays.asList(
                        new WeatherDataPoint(1,30,20),
                        new WeatherDataPoint(2,25,20),
                        new WeatherDataPoint(3,25,5),
                        new WeatherDataPoint(4,40,10),
                        new WeatherDataPoint(5,20,25),
                        new WeatherDataPoint(6,25,20)
                ));
            }
        };

         //Setup a Model to test if the controller deals with no available data correctly
        this.mockBackend2 = new Model<List<WeatherDataPoint>>() {
            @Override
            public List<WeatherDataPoint> getData() {
                return new ArrayList<>();
            }
        };

        this.controller1 = new WeatherDataController(this.mockBackend1);
        this.controller2 = new WeatherDataController(this.mockBackend2);


    }

    /**
     * Test to see if the controller correctly deals with no available data by throwing a InvalidDataException
     */
    @Test
    void testMaximumTempSpreadOfEmptyArray() {
        assertThrows(InvalidDataException.class, () -> {this.controller2.calculate();});
    }

    /**
     * Test to see if the controller correctly finds the number of the day with the maximum temperature spread
     * even if that data includes cases where MnT > MxT
     */
    @Test
    void testMaximumTempSpreadOfArray() {
        try {
            assertEquals(4, this.controller1.calculate());
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        }
    }

}