package net.thucydides.showcase.jbehave.steps;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.Inflector;
import net.thucydides.showcase.jbehave.steps.serenity.BuyerSteps;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.text.MessageFormat;
import java.text.ParseException;

public class SearchScenarioSteps {
    @Steps
    BuyerSteps buyer;

    @Given("I want to buy $article")
    public void buyerWantsToBuy(String article) {
        buyer.opens_home_page();
    }

    @When("I search for '$keyword'")
    public void searchByKeyword(String keyword) {
        buyer.searches_by_keyword(keyword);
    }

    @Then("I should see only articles related to '$keyword'")
    public void resultsForACategoryAndKeywordInARegion(String keyword) throws ParseException {
        buyer.should_see_results_summary_containing(keyword);
    }

    @Given("I want to see articles from a particular shop")
    public void givenIWantToSeeArticlesFromAParticularShop() {
        buyer.opens_home_page();
    }

    @When("I search by shop for '$shopName'")
    public void whenISearchByShopFor(String shopName) {
        buyer.searches_for_shop_called(shopName);
    }

    @Then("I should find $count shop called '$shopName'")
    @Alias("I should find $count shops called '$shopName'")
    public void thenIShouldFindShopsCall(int count, String shopName) {
        String expectedMessage = String.format("%d %s found for %s", count, pluralized(count,"shop"),shopName);
        buyer.should_see_shop_search_result_summary_of(expectedMessage);
    }

    private String pluralized(int count, String word) {
        return Inflector.getInstance().pluralize(word, count);

    }
}


