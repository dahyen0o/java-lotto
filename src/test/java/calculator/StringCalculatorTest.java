package calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringCalculatorTest {

    @ParameterizedTest(name = "덧셈 - input: {0}, expected: {1}")
    @CsvSource(value = {"1 = 1", "1 + 1 = 2", "1 + 2 + 3 = 6"}, delimiterString = " = ")
    void add(String input, String expected) {
        // given
        // when
        int result = StringCalculator.calculate(input);

        // then
        Assertions.assertEquals(result, Integer.parseInt(expected));
    }

    @ParameterizedTest(name = "뺄셈 - input: {0}, expected: {1}")
    @CsvSource(value = {"7 = 7", "1 - 2 = -1", "1 - 1 = 0", "2 - 1 = 1"}, delimiterString = " = ")
    void sub(String input, String expected) {
        // given
        // when
        int result = StringCalculator.calculate(input);

        // then
        Assertions.assertEquals(result, Integer.parseInt(expected));
    }

    @ParameterizedTest(name = "곱셈 - input: {0}, expected: {1}")
    @CsvSource(value = {"3 = 3", "1 * 0 = 0", "0 * 1 = 0", "1 * 1 = 1", "1 * 2 = 2"}, delimiterString = " = ")
    void mul(String input, String expected) {
        // given
        // when
        int result = StringCalculator.calculate(input);

        // then
        Assertions.assertEquals(result, Integer.parseInt(expected));
    }

    @ParameterizedTest(name = "나눗셈 - input: {0}, expected: {1}")
    @CsvSource(value = {"100 = 100", "1 / 1 = 1", "2 / 2 = 1", "6 / 2 = 3"}, delimiterString = " = ")
    void div(String input, String expected) {
        // given
        // when
        int result = StringCalculator.calculate(input);

        // then
        Assertions.assertEquals(result, Integer.parseInt(expected));
    }

    @ParameterizedTest(name = "복합 - input: {0}, expected: {1}")
    @CsvSource(value = {"1 = 1", "1 + 1 - 2 = 0", "2 + 3 * 4 / 2 = 10"}, delimiterString = " = ")
    void complex(String input, String expected) {
        // given
        // when
        int result = StringCalculator.calculate(input);

        // then
        Assertions.assertEquals(result, Integer.parseInt(expected));
    }
}