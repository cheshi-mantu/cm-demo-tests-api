package io.qameta.allure;


import java.util.Random;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.fail;

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
        step(String.format("POST /api/%s/shipment", owner), ()-> {
            maybeThrowApiTimeoutException();
        });
    }

    @Step("Cancel shipment `{title}`")
    public void cancelExistingShipment(final String owner, final String title) {
        step(String.format("GET /api/shipment?id=%s", title));
        step(String.format("PATCH /api/shipment/%s", title), ()-> {
            System.out.println("do stuff");
        });
    }

    @Step("Check shipment `{title}` has been created")
    public void shouldSeeCreatedShipment(final String owner, final String title) {
        step(String.format("GET /api/%s/shipment?text=%s", owner, title));
        step(String.format("GET /api/%s/shipment/%s", owner, 10), ()-> {
            maybeThrowApiTimeoutException();
        });

    }

    @Step("Create support issue with title `{title}`")
    public void createIssueWithTitle(final String owner, final String title) {
        step(String.format("POST /help/%s/issues", owner));
    }


    @Step("Close support issue with title `{title}`")
    public void closeIssueWithId(final String owner, final int id) {
        step(String.format("GET /help/%s/issues?text=%s", owner,  id));
        step(String.format("PATCH /help/%s/issues/%s", owner,  id), ()-> {
            maybeThrowApiTimeoutException();
        });
    }

    @Step("Check note with content `{title}` exists")
    public void shouldSeeIssueWithTitle(final String owner, final String title) {
        step(String.format("GET /help/%s/issues?text=%s", owner, title));
        step(String.format("GET /help/%s/issues/%s", owner, 10));
    }

    private void maybeThrowApiTimeoutException() {
        if (isTimeToThrowException()) {
            fail(apiTimeoutException());
        }
    }
    private boolean isTimeToThrowException() {
        return new Random().nextBoolean()
                && new Random().nextBoolean()
                && new Random().nextBoolean()
                && new Random().nextBoolean();
    }
    private String apiTimeoutException() {
        return "java.net.SocketTimeoutException: connect timed out\n        at java.base/java.net.PlainSocketImpl.socketConnect(Native Method)\n        at java.base/java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:412)\n        at java.base/java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:255)\n        at java.base/java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:237)\n        at java.base/java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)\n        at java.base/java.net.Socket.connect(Socket.java:609)\n        at org.apache.http.conn.ssl.SSLSocketFactory.connectSocket(SSLSocketFactory.java:542)\n        at org.apache.http.conn.ssl.SSLSocketFactory.connectSocket(SSLSocketFactory.java:414)\n        at org.apache.http.impl.conn.DefaultClientConnectionOperator.openConnection(DefaultClientConnectionOperator.java:180)\n        at org.apache.http.impl.conn.ManagedClientConnectionImpl.open(ManagedClientConnectionImpl.java:326)\n        at org.apache.http.impl.client.DefaultRequestDirector.tryConnect(DefaultRequestDirector.java:610)\n        at org.apache.http.impl.client.DefaultRequestDirector.execute(DefaultRequestDirector.java:445)\n        at org.apache.http.impl.client.AbstractHttpClient.doExecute(AbstractHttpClient.java:835)\n        at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:83)\n        at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:56)\n        at org.apache.http.client.HttpClient$execute$0.call(Unknown Source)\n        at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:47)\n        at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:125)\n        at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:148)\n        at io.restassured.internal.RequestSpecificationImpl$RestAssuredHttpBuilder.doRequest(RequestSpecificationImpl.groovy:2055)\n        at io.restassured.internal.http.HTTPBuilder.doRequest(HTTPBuilder.java:495)\n        at io.restassured.internal.http.HTTPBuilder.request(HTTPBuilder.java:452)\n        at io.restassured.internal.http.HTTPBuilder$request$2.call(Unknown Source)\n        at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:47)\n        at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:125)\n        at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:166)";

    }

}
