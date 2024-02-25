package de.exxcellent.challenge.enities;

import java.util.Objects;

public class FootballDataPoint implements DeepCopyable<FootballDataPoint>{

    private String teamName;
    private int goalsScored;
    private int goalsAllowed;

    public FootballDataPoint(String teamName, int goalsScored, int goalsAllowed) {
        this.teamName = teamName;
        this.goalsScored = goalsScored;
        this.goalsAllowed = goalsAllowed;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsAllowed() {
        return goalsAllowed;
    }

    public void setGoalsAllowed(int goalsAllowed) {
        this.goalsAllowed = goalsAllowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FootballDataPoint that = (FootballDataPoint) o;

        if (goalsScored != that.goalsScored) return false;
        if (goalsAllowed != that.goalsAllowed) return false;
        return Objects.equals(teamName, that.teamName);
    }

    @Override
    public int hashCode() {
        int result = teamName != null ? teamName.hashCode() : 0;
        result = 31 * result + goalsScored;
        result = 31 * result + goalsAllowed;
        return result;
    }

    @Override
    public String toString() {
        return "FootballDataPoint{" +
                "teamName='" + teamName + '\'' +
                ", goalsScored=" + goalsScored +
                ", goalsAllowed=" + goalsAllowed +
                '}';
    }

    @Override
    public FootballDataPoint deepCopy() {
        return new FootballDataPoint(this.teamName, this.goalsScored, this.goalsAllowed);
    }
}
