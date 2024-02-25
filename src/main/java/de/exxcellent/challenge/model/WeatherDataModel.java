package de.exxcellent.challenge.model;

import de.exxcellent.challenge.enities.WeatherDataPoint;
import de.exxcellent.challenge.exceptions.NoDataSourceAvailable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class WeatherDataModel extends CSVModel<WeatherDataPoint>{

    public WeatherDataModel(String path) throws NoDataSourceAvailable {
        super(path);
    }

    @Override
    WeatherDataPoint convertCSVRecordToEntity(CSVRecord record) {
        int numOfDay;
        int maxTemp;
        int minTemp;
        numOfDay = Integer.parseInt(record.get(0));
        maxTemp = Integer.parseInt(record.get(1));
        minTemp = Integer.parseInt(record.get(2));
        return new WeatherDataPoint(numOfDay, maxTemp, minTemp);
    }
}
