package com.zzzkvidi4.gameserver.validator;

import java.util.LinkedList;
import java.util.List;

public class Validator {
    public static List<String> validateCharacter(int intellijence, int charysma, int strength, int leader, int success) {
        List<String> errors = new LinkedList<>();
        if (intellijence < 0 || intellijence > 255) {
            errors.add("Intellijence should be in [0, 255]");
        }

        if (charysma < 0 || charysma > 255) {
            errors.add("Intellijence should be in [0, 255]");
        }

        if (strength < 0 || strength > 255) {
            errors.add("Intellijence should be in [0, 255]");
        }

        if (leader < 0 || leader > 255) {
            errors.add("Intellijence should be in [0, 255]");
        }

        if (success < 0 || success > 255) {
            errors.add("Intellijence should be in [0, 255]");
        }

        return errors;
    }
}
