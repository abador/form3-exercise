package com.olsys.form3.exercise.cucumber.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olsys.form3.exercise.cucumber.CucumberRoot;
import com.olsys.form3.exercise.cucumber.helper.HALResponseBody;
import com.olsys.form3.exercise.cucumber.helper.Payment;
import com.olsys.form3.exercise.cucumber.helper.PaymentsList;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.*;
@Ignore
public class Payments extends CucumberRoot {
    private static final String BASE_URI = "/api/v1/payments";
    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private List<Payment> initialPayments;
    private String sentRequestBody;
    private ResponseEntity<Payment> currentResponse;
    private ResponseEntity<HALResponseBody<PaymentsList>> currentResponseList;
    private UUID referencedPaymentId;

    public Payments() {
        super();
    }

    @Before
    public void before() {
        initialPayments = new ArrayList<>();
    }

    @Given("^payments list$")
    public void PaymentsList(DataTable dataTable) throws Throwable {
        for (int i = 1; i < dataTable.getGherkinRows().size(); i++) {
            final JSONObject payment = createPayment(dataTable.getGherkinRows().get(i));
            final HttpEntity<String> entity = createHttpEntity(payment);

            final ResponseEntity<Payment> response
                    = restTemplate.exchange(BASE_URI, HttpMethod.POST, entity, Payment.class);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            initialPayments.add(response.getBody());
        }
    }

    private void deleteAllPayments() {
        final ResponseEntity<HALResponseBody<PaymentsList>> response = restTemplate.exchange(BASE_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<HALResponseBody<PaymentsList>>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());

        for (Payment p : response.getBody().getEmbedded().getPayments()){
            Logger.getLogger(Payments.class).info(p.getId());
            restTemplate.delete(BASE_URI + "/" + p.getId());
        }
    }

    private JSONObject createPayment(DataTableRow row) throws JSONException {
        final JSONObject requestBody = new JSONObject();
        requestBody.put("type", row.getCells().get(1));
        requestBody.put("version", row.getCells().get(2));
        requestBody.put("organisation_id", row.getCells().get(3));
        requestBody.put("attributes", new JSONObject(row.getCells().get(4)));
        return requestBody;
    }

    private HttpEntity<String> createHttpEntity(JSONObject input) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(input.toString(), headers);
    }

    private void assertSamePayments(Payment expected, Payment actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getVersion(), actual.getVersion());
        assertThat(expected.getAttributes(), samePropertyValuesAs(currentResponse.getBody().getAttributes()));
        assertThat(expected.getOrganisationId(), samePropertyValuesAs(currentResponse.getBody().getOrganisationId()));
    }



    @When("^user sends GET request without references$")
    public void userSendsGETRequestWithoutReferences()
            throws JSONException {
        currentResponseList = restTemplate.exchange(BASE_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<HALResponseBody<PaymentsList>>() {});
    }

    @When("^user sends a (GET|POST|PUT|DELETE) request with reference to (\\d+.|non-existing) payment$")
    public void userSendsRequestWithReferenceToThePayment(String method, String index)
            throws JSONException {
        userSendsRequestWithReferenceToThePaymentAndBody(method, index, "");
    }

    @When("^user sends a (PUT|POST|PATCH|DELETE) request without payment id and body '(.+)'$")
    public void userSendsRequestWithoutPaymentIdAndBody(String method, String requestBody)
            throws JSONException {
        userSendsRequestWithReferenceToThePaymentAndBody(method, "no", requestBody);
    }

    @When("^user sends a (PUT|POST|PATCH|DELETE) request with reference to (\\d+.|non-existing) payment and body '(.+)'$")
    public void userSendsRequestWithReferenceToThePaymentAndBody(String method, String index, String requestBody)
            throws JSONException {
        final HttpMethod httpMethod = HttpMethod.resolve(method);

        String uri = BASE_URI;
        if (index.equals("non-existing")) {
            referencedPaymentId = UUID.randomUUID();
            uri += "/" + referencedPaymentId;
        } else if (!index.equals("no")) {
            final Payment p = initialPayments.get(Integer.parseInt(index.substring(0, index.length() - 1)) - 1);
            referencedPaymentId = p.getId();
            uri += "/" + referencedPaymentId;
        }

        HttpEntity<String> requestEntity = null;
        if (!requestBody.equals("")) {
            sentRequestBody = requestBody;
            requestEntity = createHttpEntity(new JSONObject(requestBody));
        }
        currentResponse = restTemplate.exchange(uri, httpMethod, requestEntity,
                Payment.class);
    }

    @Then("^the response status code should be (\\d+)$")
    public void theResponseStatusCodeShouldBe(int statusCode) throws Throwable {
        assertEquals(statusCode, currentResponse.getStatusCode().value());
    }

    @Then("^the response list status code should be (\\d+)$")
    public void theResponseListStatusCodeShouldBe(int statusCode) throws Throwable {
        assertEquals(statusCode, currentResponseList.getStatusCode().value());
    }

    @And("^the response body should be the same as (.+)$")
    public void theResponseBodyShouldBeLike(String body) throws Throwable {
        final Payment expectedPayment;
        if (body.equals("the one sent")) {
            expectedPayment = OBJECT_MAPPER.readValue(sentRequestBody, Payment.class);
        } else {
            expectedPayment = OBJECT_MAPPER.readValue(body, Payment.class);
        }
        expectedPayment.setId(currentResponse.getBody().getId());
        assertSamePayments(expectedPayment, currentResponse.getBody());
    }

    @And("^the response body should contain Id the same as the referenced one$")
    public void theResponseBodyShouldContainIdLikeReferencedOne() {
        assertEquals(referencedPaymentId, currentResponse.getBody().getId());
    }

    @And("^the response body should contain all payments$")
    public void theResponseBodyShouldContainAllPayments() throws Throwable {
        final List<Payment> currentPayments = currentResponseList.getBody().getEmbedded().getPayments();

        assertEquals(initialPayments.size(), currentPayments.size());
        assertTrue(initialPayments.stream()
                .map(p -> p.getId())
                .collect(Collectors.toList())
                .containsAll(
                        currentPayments
                                .stream()
                                .map(p -> p.getId())
                                .collect(Collectors.toList())));
    }

    @After
    public void after() {
        deleteAllPayments();
    }
}
