package org.frontear.elynia.helper;

public class Timer {
    private long milliseconds;

    public Timer() {
        milliseconds = System.nanoTime() / 1000000L;
    }

    public boolean timeElapsed(long time) {
        return getMilliseconds() >= time;
    }
    public long getMilliseconds() {
        return (System.nanoTime() / 1000000L) - milliseconds;
    }
    public void resetTime() {
        milliseconds = System.nanoTime() / 1000000L;
    }

    @Override public String toString() {
        // note: this doesn't limit the hours at 24
        long seconds = getMilliseconds() / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        seconds -= (minutes * 60);
        minutes -= (hours * 60);

        return format(hours) + ":" + format(minutes) + ":" + format(seconds);
    }

    private String format(long value) {
        // this will force the 'time' to display with 2 digits at all times.
        return (value == 0 ? "00" : value < 10 ? "0" + value : value) + "";
    }
}
