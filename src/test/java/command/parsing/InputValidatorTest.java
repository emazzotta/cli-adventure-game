package command.parsing;

import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.InputValidator;
import com.mazzotta.kuster.pointandclick.adventure.commands.parsing.exception.InvalidUserInputException;
import org.junit.Test;

public class InputValidatorTest {

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
