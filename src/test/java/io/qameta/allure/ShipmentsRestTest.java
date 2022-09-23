package io.qameta.allure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.qameta.allure.Allure.parameter;

@Layer("rest")
@Owner("baev")
@Feature("Shipment Requests")
public class ShipmentsRestTest {

    private static final String OWNER = "Mr. Order Randomson";

    private static final String TITLE = "confirmation string";
    private static final int ID = 11;

    private final RestSteps steps = new RestSteps();

    @Story("Manage shipments")
    @Microservice("Shipment")
    @Tags({@Tag("api"), @Tag("smoke"), @Tag("critical"), @Tag("nightly")})
    @JiraIssues({@JiraIssue("AD-7")})
    @ParameterizedTest(name = "Create a new shipment")
    @ValueSource(strings = {"First item added", "Second item added"})
    public void shouldCreateNewShipment(String title) {
        parameter("requester", OWNER);
        parameter("title", title);

        steps.createNewShipment(OWNER, title);
        steps.shouldSeeCreatedShipment(OWNER, title);
    }

    @Test
    @Story("Manage shipments")
    @Microservice("Shipment")
    @Tags({@Tag("api"), @Tag("regress"), @Tag("nightly")})
    @JiraIssues({@JiraIssue("AD-8")})
    @DisplayName("Closing an existing support issue")
    public void shouldDeleteCreatedIssue() {
        parameter("requester", OWNER);
        parameter("id", ID);

        steps.createNewShipment(OWNER, TITLE);
        steps.cancelExistingShipment(OWNER,TITLE);
    }


}
