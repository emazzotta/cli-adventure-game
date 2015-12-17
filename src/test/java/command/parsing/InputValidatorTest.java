package command.parsing;

import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.InputValidator;
import com.mazzotta.kuster.pointandclick.adventure.exceptions.InvalidUserInputException;
import org.junit.Test;

public class InputValidatorTest {

    @Test
    public void testThatValidateActionFragmentsSucceedsWithCorrectCommands() throws InvalidUserInputException {
        InputValidator.validateCommandActionFragments(new String[]{"help"});
        InputValidator.validateCommandActionFragments(new String[]{"collect",  "items"});
        InputValidator.validateCommandActionFragments(new String[]{"use", "weapon",  "1"});
    }

    @Test
    public void testThatValidateActionFragmentsCanHandleCommandsThatAreTooLong() throws InvalidUserInputException {
        InputValidator.validateCommandActionFragments(new String[]{"collect", "items", "1", "2", "3"});
    }

    @Test(expected = InvalidUserInputException.class)
    public void testThatValidateLengthComplainsIfTheCommandIsEmpty() throws InvalidUserInputException {
        InputValidator.validateLength(new String[]{});
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
