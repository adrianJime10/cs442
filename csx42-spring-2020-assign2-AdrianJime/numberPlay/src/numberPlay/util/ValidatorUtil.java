package numberPlay.util;

import java.lang.IllegalArgumentException;
import java.lang.NumberFormatException;

public final class ValidatorUtil {

	public static void validate(String baseErrMsg, Validator... validators) throws NumberFormatException, IllegalArgumentException {
		for (Validator v : validators) {
			try {
				v.run();
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(baseErrMsg.concat(": " + e.getMessage()), e);
			}
		}
		return;

	}
}
