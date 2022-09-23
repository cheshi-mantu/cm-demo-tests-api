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
@Feature("Payments widget on main page")
public class PaymentRestTest {

    private static final String OWNER = "Mr. Order Randomson";

    private static final String TITLE = "confirmation string";
    private static final int ID = 11;

    private final RestSteps steps = new RestSteps();

    @Story("Manage payments via main page widget")
    @Microservice("PaymentsWidget")
    @Tags({@Tag("api"), @Tag("smoke"), @Tag("critical")})
    @JiraIssues({@JiraIssue("AD-2")})
    @ParameterizedTest(name = "Create a new payment via payments widget")
    @ValueSource(strings = {"First item added", "Second item added"})
    public void shouldCreateNewIssue(String title) {
        parameter("requester", OWNER);
        parameter("title", title);

        steps.createNewPayment(OWNER, title);
        steps.shouldSeeCreatedPayment(OWNER, title);
    }

    @Test
    @Story("Manage payments via main page widget")
    @Microservice("PaymentsWidget")
    @Tags({@Tag("api"), @Tag("regress"), @Tag("nightly")})
    @JiraIssues({@JiraIssue("AD-3")})
    @DisplayName("API - closing an existing support issue")
    public void shouldDeleteCreatedIssue() {
        parameter("requester", OWNER);
        parameter("id", ID);

        steps.createNewPayment(OWNER, TITLE);
        steps.cancelExistingPayment(OWNER, TITLE);
    }


}
