
// FIXME: replace XYZ with the package name for the assignment
package primePlay.util;

public class MyLogger{

    // FIXME: Add more enum values as needed for the assignment
    public static enum DebugLevel {CONSTRUCTOR, RUN, ENTRY, RESULTS, SUM, NONE};

    private static DebugLevel debugLevel;


    // FIXME: Add switch cases for all the levels
    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
	case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
	case 3: debugLevel = DebugLevel.RUN; break;
	case 2: debugLevel = DebugLevel.ENTRY; break;
	case 1: debugLevel = DebugLevel.RESULTS; break;
	case 0: debugLevel = DebugLevel.SUM; break;
	default: debugLevel = DebugLevel.NONE; break;
	}
    }

    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    public static void writeMessage (String message, DebugLevel levelIn) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    @Override
    public String toString() {
	return "The debug level has been set to the following " + debugLevel;
    }

	@Override
	public int hashCode() {
		return 3;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
   	}

}
