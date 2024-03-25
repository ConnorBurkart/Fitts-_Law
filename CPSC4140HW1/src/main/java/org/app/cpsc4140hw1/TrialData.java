package org.app.cpsc4140hw1;
public class TrialData {
    private int trialNumber;
    private double targetSize;
    private double distanceToTarget;
    private long timeToClick;

    public TrialData(int trialNumber, double targetSize, double distanceToTarget) {
        this.trialNumber = trialNumber;
        this.targetSize = targetSize;
        this.distanceToTarget = distanceToTarget;
        this.timeToClick = 0; // Initialize with 0, will be set when the target is clicked
    }

    public void setTimeToClick(long timeToClick) {
        this.timeToClick = timeToClick;
    }

    public String toCSVString() {
        return Math.round(trialNumber) + "," + Math.round(targetSize) + "," + Math.round(distanceToTarget) + "," + Math.round(timeToClick);
    }
}

