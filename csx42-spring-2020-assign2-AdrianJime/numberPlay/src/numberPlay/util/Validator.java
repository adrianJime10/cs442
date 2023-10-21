package numberPlay.util;

import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;

public interface Validator {
	void run() throws NumberFormatException, IllegalArgumentException;
}
