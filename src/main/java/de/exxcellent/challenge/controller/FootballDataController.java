package de.exxcellent.challenge.controller;

import de.exxcellent.challenge.enities.FootballDataPoint;
import de.exxcellent.challenge.enities.WeatherDataPoint;
import de.exxcellent.challenge.exceptions.InvalidDataException;
import de.exxcellent.challenge.model.Model;

import java.util.List;

public class FootballDataController implements Controller<String>{

    private final Model<List<FootballDataPoint>> footballDataModel;

    public FootballDataController(final Model<List<FootballDataPoint>> model) {
        this.footballDataModel = model;
    }

    @Override
    public String calculate() throws InvalidDataException {
        List<FootballDataPoint> footballData = this.footballDataModel.getData();
        return footballData.stream().min((var fdp1, var fdp2) -> {
            int goalDiffFdp1 = Math.abs(fdp1.getGoalsAllowed() - fdp1.getGoalsScored());
            int goalDiffFdp2 = Math.abs(fdp2.getGoalsAllowed() - fdp2.getGoalsScored());
            return Integer.compare(goalDiffFdp1, goalDiffFdp2);
        }).map(FootballDataPoint::getTeamName).orElseThrow(() -> new InvalidDataException("No Minimum Could be found"));
    }
}
