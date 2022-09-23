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
@Feature("Support requests handling")
public class IssuesRestTest {

    private static final String OWNER = "Mr. Random Randomson";

    private static final String TITLE = "Troubles needs to be fixed";
    private static final int ID = 10;

    private final RestSteps steps = new RestSteps();

    @Story("Managing support requests with type Issue")
    @Microservice("Support")
    @Tags({@Tag("api"), @Tag("smoke"), @Tag("critical")})
    @JiraIssues({@JiraIssue("AD-5")})
    @ParameterizedTest(name = "Creation of a new support issue")
    @ValueSource(strings = {"First attempt", "Second attempt"})
    public void shouldCreateNewIssue(String title) {
        parameter("owner", OWNER);
        parameter("title", title);

        steps.createIssueWithTitle(OWNER, title);
        steps.shouldSeeIssueWithTitle(OWNER, title);
    }

    @Test
    @Story("Managing support requests with type Issue")
    @Microservice("Support")
    @Tags({@Tag("api"), @Tag("regress"), @Tag("nightly")})
    @JiraIssues({@JiraIssue("AD-6")})
    @DisplayName("Closing an existing support issue")
    public void shouldDeleteCreatedIssue() {
        parameter("owner", OWNER);
        parameter("id", ID);

        steps.createIssueWithTitle(OWNER, TITLE);
        steps.closeIssueWithId(OWNER,  ID);
    }


}
