package de.exxcellent.challenge.model;


import de.exxcellent.challenge.enities.DeepCopyable;
import de.exxcellent.challenge.exceptions.NoDataSourceAvailable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract class CSVModel<T extends DeepCopyable<T>> implements Model<List<T>> {

    public List<T> records;

    public CSVModel(final String path) throws NoDataSourceAvailable {
        this.records = new ArrayList<T>();
        try {
            //Open the csv file under path using the Commons CSV library
            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            //Skip the header line that is not parsed properly by the library
            records.iterator().next();
            //Iterate over the records and store them as WeatherDataPoint objects in the records list
            for(CSVRecord record: records) {
                this.records.add(this.convertCSVRecordToEntity(record));
            }

        } catch (FileNotFoundException e) {
            throw new NoDataSourceAvailable(String.format("The file at %s does not exist.\n", path));
        } catch (IOException e) {
            throw new NoDataSourceAvailable(String.format("The file at %s does not conform to the CSV format.\n", path));
        }
    }

    /**
     * This method returns a list with all data points. It creates a deep copy to make sure that the internal
     * representation of the data is independent of the data that is returned by this method.
     * @return a List with all data points
     */
    public List<T> getData() {
        return this.records.stream().map(T::deepCopy).collect(Collectors.toList());
    };

    abstract T convertCSVRecordToEntity(CSVRecord record);

}
