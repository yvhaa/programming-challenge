package de.exxcellent.challenge.controller;

import de.exxcellent.challenge.exceptions.InvalidDataException;

public interface Controller {

    public int getDayWithMaxTempSpread()  throws InvalidDataException;

}
