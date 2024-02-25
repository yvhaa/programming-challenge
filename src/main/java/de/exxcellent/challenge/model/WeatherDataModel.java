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

public class WeatherDataModel implements Model{

    private final List<WeatherDataPoint> records;

    public WeatherDataModel(final String path) throws NoDataSourceAvailable {
        this.records = new ArrayList<WeatherDataPoint>();
        try {
            //Open the csv file under path using the Commons CSV library
            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            //Skip the header line that is not parsed properly by the library
            records.iterator().next();
            //Iterate over the records and store them as WeatherDataPoint objects in the records list
            for(CSVRecord record: records) {
                int numOfDay;
                int maxTemp;
                int minTemp;
                numOfDay = Integer.parseInt(record.get(0));
                maxTemp = Integer.parseInt(record.get(1));
                minTemp = Integer.parseInt(record.get(2));
                this.records.add(new WeatherDataPoint(numOfDay, maxTemp, minTemp));
            }

        } catch (FileNotFoundException e) {
            throw new NoDataSourceAvailable(String.format("The file at %s does not exist.\n", path));
        } catch (IOException e) {
            throw new NoDataSourceAvailable(String.format("The file at %s does not conform to the CSV format.\n", path));
        }
    }

    /**
     * This method returns a list with all weather data points. It creates a deep copy to make sure that the internal
     * representation of the data is independent of the data that is returned by this method.
     * @return a List with all weather data points
     */
    @Override
    public List<WeatherDataPoint> getWeatherData() {
        return this.records.stream().map(WeatherDataPoint::deepCopy).collect(Collectors.toList());
    }
}
