package io.qameta.allure;

import io.qameta.allure.Step;

import static io.qameta.allure.Allure.step;

public class RestSteps {

    @Step("Create new payment `{title}`")
    public void createNewPayment(final String owner, final String title) {
        step(String.format("POST /api/%s/payment", owner));
    }

    @Step("Cancel payment `{title}`")
    public void cancelExistingPayment(final String owner, final String title) {
        step(String.format("GET /api/payment?id=%s", owner));
        step(String.format("PATCH /api/payment/%s", owner));
    }

    @Step("Check payment `{title}` has been created")
    public void shouldSeeCreatedPayment(final String owner, final String title) {
        step(String.format("GET /api/%s/payment?text=%s", owner, title));
        step(String.format("GET /api/%s/payment/%s", owner, 10));
    }
    @Step("Create new shipment `{title}`")
    public void createNewShipment(final String owner, final String title) {
        step(String.format("POST /api/%s/shipment", owner));
    }

    @Step("Cancel shipment `{title}`")
    public void cancelExistingShipment(final String owner, final String title) {
        step(String.format("GET /api/shipment?id=%s", title));
        step(String.format("PATCH /api/shipment/%s", title));
    }

    @Step("Check shipment `{title}` has been created")
    public void shouldSeeCreatedShipment(final String owner, final String title) {
        step(String.format("GET /api/%s/shipment?text=%s", owner, title));
        step(String.format("GET /api/%s/shipment/%s", owner, 10));
    }

    @Step("Create support issue with title `{title}`")
    public void createIssueWithTitle(final String owner, final String title) {
        step(String.format("POST /help/%s/issues", owner));
    }


    @Step("Close support issue with title `{title}`")
    public void closeIssueWithId(final String owner, final int id) {
        step(String.format("GET /help/%s/issues?text=%s", owner,  id));
        step(String.format("PATCH /help/%s/issues/%s", owner,  id));
    }

    @Step("Check note with content `{title}` exists")
    public void shouldSeeIssueWithTitle(final String owner, final String title) {
        step(String.format("GET /help/%s/issues?text=%s", owner, title));
        step(String.format("GET /help/%s/issues/%s", owner, 10));
    }

}
