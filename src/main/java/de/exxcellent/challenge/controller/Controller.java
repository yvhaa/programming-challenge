package de.exxcellent.challenge.controller;

import de.exxcellent.challenge.exceptions.InvalidDataException;

public interface Controller<T> {

    public T calculate()  throws InvalidDataException;

}
