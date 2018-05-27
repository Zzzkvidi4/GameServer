package com.zzzkvidi4.gameserver.validator;

import com.zzzkvidi4.gameserver.model.Criminal;
import com.zzzkvidi4.gameserver.model.GameCharacter;
import com.zzzkvidi4.gameserver.response.Error;

import java.util.LinkedList;
import java.util.List;

public class Validator {
    public static List<Error> validateCharacter(int intellijence, int charysma, int strength, int leader, int success) {
        List<Error> errors = new LinkedList<>();
        if (intellijence < 0 || intellijence > 255) {
            errors.add(new Error("Intellijence", "Intellijence should be in [0, 255]"));
        }

        if (charysma < 0 || charysma > 255) {
            errors.add(new Error("Charysma", "Charysma should be in [0, 255]"));
        }

        if (strength < 0 || strength > 255) {
            errors.add(new Error("Strength", "Strength should be in [0, 255]"));
        }

        if (leader < 0 || leader > 255) {
            errors.add(new Error("Leader", "Leader should be in [0, 255]"));
        }

        if (success < 0 || success > 255) {
            errors.add(new Error("Success", "Success should be in [0, 255]"));
        }

        return errors;
    }

    public static List<Error> validateCharacter(GameCharacter character){
        return validateCharacter(
                character.getIntellijence(),
                character.getCharysma(),
                character.getStrength(),
                character.getLeader(),
                character.getSuccess()
        );
    }

    public static List<Error> validateCriminal(Criminal criminal){
        List<Error> errors = new LinkedList<>();
        if (criminal.getName() == null) {
            errors.add(new Error("name", "name is required"));
        } else if (criminal.getName().length() < 5) {
            errors.add(new Error("name", "name should be at least 5 symbols"));
        }

        if (criminal.getSurname() == null) {
            errors.add(new Error("surname", "surname is required"));
        } else if (criminal.getSurname().length() < 5) {
            errors.add(new Error("surname", "surname should be at least 5 symbols"));
        }
        return errors;
    }
}
