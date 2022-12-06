package com.griddynamics.gridu.qa.user.all_tests.test_data;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.qameta.allure.Step;
import org.apache.http.HttpStatus;

public class ServicesTestData extends TestData {

    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String JSON_CONTENT_TYPE = "application/json";

    @Step("Stub for service with urlRegex (regex = {urlRegex})")
    public static void stubForService(WireMockServer wireMockServer, String urlRegex, String jsonBody) {
        wireMockServer.stubFor(WireMock.post(WireMock.urlMatching(urlRegex))
                .willReturn(WireMock.created()));
        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching(urlRegex))
                .willReturn(WireMock.aResponse()
                        .withHeader(CONTENT_TYPE, JSON_CONTENT_TYPE)
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(jsonBody)));
    }

    @Step("Stub for service with urlRegex (regex = {urlRegex}) which returns error")
    public static void stubForServiceWithError(WireMockServer wireMockServer, String urlRegex) {
        wireMockServer.stubFor(WireMock.post(WireMock.urlMatching(urlRegex))
                .willReturn(WireMock.serverError()));
    }

}
