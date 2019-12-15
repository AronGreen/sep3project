package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TripDetails {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;
    private ArrayList<String> stopAdresses;
    private ArrayList<LocalDateTime> stopTimes;

    public TripDetails() {
    }

    public TripDetails(LocalDateTime startTime, LocalDateTime endTime, int duration, ArrayList<String> stopAdresses, ArrayList<LocalDateTime> stopTimes) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.stopAdresses = stopAdresses;
        this.stopTimes = stopTimes;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getStopAdresses() {
        return stopAdresses;
    }

    public void setStopAdresses(ArrayList<String> stopAdresses) {
        this.stopAdresses = stopAdresses;
    }

    public ArrayList<LocalDateTime> getStopTimes() {
        return stopTimes;
    }

    public void setStopTimes(ArrayList<LocalDateTime> stopTimes) {
        this.stopTimes = stopTimes;
    }
}
