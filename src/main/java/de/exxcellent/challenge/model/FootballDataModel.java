package de.exxcellent.challenge.model;

import de.exxcellent.challenge.enities.FootballDataPoint;
import de.exxcellent.challenge.enities.WeatherDataPoint;
import de.exxcellent.challenge.exceptions.NoDataSourceAvailable;
import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.stream.Collectors;

public class FootballDataModel extends CSVModel<FootballDataPoint> {

    public FootballDataModel(String path) throws NoDataSourceAvailable {
        super(path);
    }

    @Override
    FootballDataPoint convertCSVRecordToEntity(CSVRecord record) {
        String teamName = record.get(0);
        int goalsScored = Integer.parseInt(record.get(1));
        int goalsAllowed = Integer.parseInt(record.get(2));
        return new FootballDataPoint(teamName, goalsScored, goalsAllowed);
    }
}
