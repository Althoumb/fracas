package utils;

public class Timing {
	public static long getTimeMs() {
	    return System.nanoTime() / 1000000;
	}
	public static long getTimeNs() {
	    return System.nanoTime();
	}
}
