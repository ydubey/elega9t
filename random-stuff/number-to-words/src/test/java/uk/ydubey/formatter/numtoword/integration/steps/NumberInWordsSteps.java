package uk.ydubey.formatter.numtoword.integration.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;
import uk.ydubey.formatter.numtoword.NumberInWordsFormatter;

import static org.junit.Assert.assertEquals;

public class NumberInWordsSteps extends Embedder {

    private NumberInWordsFormatter test = NumberInWordsFormatter.getInstance();
    private int number;

    @When("the number is $number")
    public void theNumberIs(int number) {
        this.number = number;
    }

    @Then("in-words it should be $inWords")
    public void inWordsItShouldBe(String result) {
        assertEquals(result, test.format(number));
    }

}
