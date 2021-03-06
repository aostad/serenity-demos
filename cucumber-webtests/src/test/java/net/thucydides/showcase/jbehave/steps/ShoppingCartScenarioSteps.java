package net.thucydides.showcase.jbehave.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.Serenity;
import net.thucydides.core.annotations.Steps;
import net.thucydides.showcase.jbehave.model.ListingItem;
import net.thucydides.showcase.jbehave.steps.serenity.BuyerSteps;
import static net.thucydides.showcase.jbehave.model.SessionVariables.SELECTED_LISTING;

/**
 * Created by john on 12/11/14.
 */
public class ShoppingCartScenarioSteps {

    @Steps
    BuyerSteps buyer;

    @When("I add it to the cart")
    public void addToCart() {
        buyer.adds_current_listing_to_cart();
    }

    @Then("the item should appear in the cart")
    public void shouldSeeSelectedItemInCart() {
        ListingItem selectedItem = (ListingItem) Serenity.getCurrentSession().get(SELECTED_LISTING);
        buyer.should_see_item_in_cart(selectedItem);
    }

    @Then("the shipping cost should be included in the total price")
    public void shouldIncludeShippingCost() {
        ListingItem selectedItem = (ListingItem) Serenity.getCurrentSession().get(SELECTED_LISTING);
        buyer.should_see_total_including_shipping_for(selectedItem);
    }


}
