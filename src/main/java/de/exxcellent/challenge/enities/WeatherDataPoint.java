package de.exxcellent.challenge.enities;

public class WeatherDataPoint implements DeepCopyable<WeatherDataPoint>{
    private int numOfDay;
    private int maxTemp;
    private int minTemp;

    public WeatherDataPoint(int numOfDay, int maxTemp, int minTemp) {
        this.numOfDay = numOfDay;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public int getNumOfDay() {
        return numOfDay;
    }

    public void setNumOfDay(int numOfDay) {
        this.numOfDay = numOfDay;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public WeatherDataPoint deepCopy() {
        return new WeatherDataPoint(this.numOfDay, this.maxTemp, this.minTemp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherDataPoint that = (WeatherDataPoint) o;

        if (numOfDay != that.numOfDay) return false;
        if (maxTemp != that.maxTemp) return false;
        return minTemp == that.minTemp;
    }

    @Override
    public int hashCode() {
        int result = numOfDay;
        result = 31 * result + maxTemp;
        result = 31 * result + minTemp;
        return result;
    }

    @Override
    public String toString() {
        return "WeatherDataPoint{" +
                "numOfDay=" + numOfDay +
                ", maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                '}';
    }
}
