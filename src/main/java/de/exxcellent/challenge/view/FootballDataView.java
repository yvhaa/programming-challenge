package de.exxcellent.challenge.view;

import de.exxcellent.challenge.controller.Controller;
import de.exxcellent.challenge.controller.FootballDataController;
import de.exxcellent.challenge.exceptions.InvalidDataException;

public class FootballDataView implements View{

    private Controller<String> footballDataController;

    public FootballDataView(Controller<String> controller) {
        this.footballDataController = controller;
    }

    @Override
    public void printData() throws InvalidDataException {
        System.out.println(this.footballDataController.calculate());
    }
}
