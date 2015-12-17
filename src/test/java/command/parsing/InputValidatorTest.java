package command.parsing;

import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.InputValidator;
import com.mazzotta.kuster.pointandclick.adventure.exceptions.InvalidUserInputException;
import org.junit.Test;

import java.util.ArrayList;

public class InputValidatorTest {

    @Test
    public void testThatValidateActionFragmentsSucceedsWithCorrectCommands() throws InvalidUserInputException {
        ArrayList<String> commandAction = new ArrayList<>();
        commandAction.add("collect");
        InputValidator.validateCommandActionFragments(commandAction);
        commandAction.add("items");
        InputValidator.validateCommandActionFragments(commandAction);
        commandAction.add("1");
        InputValidator.validateCommandActionFragments(commandAction);
    }

    @Test
    public void testThatValidateActionFragmentsCanHandleCommandsThatAreTooLong() throws InvalidUserInputException {
        ArrayList<String> commandAction = new ArrayList<>();
        commandAction.add("collect");
        commandAction.add("items");
        commandAction.add("1");
        commandAction.add("2");
        commandAction.add("3");
        InputValidator.validateCommandActionFragments(commandAction);
    }

    @Test(expected = InvalidUserInputException.class)
    public void testThatValidateLengthComplainsIfTheCommandIsEmpty() throws InvalidUserInputException {
        ArrayList<String> commandAction = new ArrayList<>();
        InputValidator.validateLength(commandAction);
    }

    @Test
    public void testThatValidCommandIsValidatedWithoutIssues() throws InvalidUserInputException {
        InputValidator.validateCommand("help");
    }

    @Test(expected = InvalidUserInputException.class)
    public void testThatInvalidCommandThrowsAnInvalidUserInputException() throws InvalidUserInputException {
        InputValidator.validateCommand("asdf");
    }

    @Test
    public void testThatValidActionTypeIsValidatedWithoutIssues() throws InvalidUserInputException {
        InputValidator.validateActionType("items");
    }

    @Test(expected = InvalidUserInputException.class)
    public void testThatInvalidActionTypeThrowsAnInvalidUserInputException() throws InvalidUserInputException {
        InputValidator.validateActionType("asdf");
    }
}
