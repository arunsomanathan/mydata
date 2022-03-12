package com.mydata.utilities.test.conroller;

import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

public interface ControllerTest {

	/**
	 * Get the WebTestClient
	 */
	WebTestClient getWebTestClient();

	/**
	 * Get the Base URL
	 */
	String getBaseUrl();

	/**
	 * Get API URL from API name
	 */
	String getApiUrlFromApiName(String apiName);


	/**
	 * Send Get request and verify response
	 *
	 * @param apiUrl   the API URL
	 * @param dtoList  list of dto objects
	 * @param dtoClass the dto object class
	 */
	default <D> void verifyGet(final String apiUrl, final List<D> dtoList, final Class<D> dtoClass) {
		verifyGet(apiUrl, dtoList, dtoClass, Boolean.FALSE);
	}

	/**
	 * Send Get request and verify response
	 *
	 * @param apiName  the API Name
	 * @param dtoList  list of dto objects
	 * @param dtoClass the dto object class
	 * @param genDoc   if true generate documentation
	 */
	private <D> void verifyGet(
					final String apiName, final List<D> dtoList, final Class<D> dtoClass, final Boolean genDoc) {
		get(getApiUrl(apiName))
						.expectAll(
										ControllerTest::isOk,
										ControllerTest::isContentTypeJson,
										responseSpec -> assertBodyEquals(responseSpec, dtoClass, dtoList, apiName, genDoc));
	}

	/**
	 * Do a get request by concatenating base url and url
	 *
	 * @param url the url for the request
	 * @return the {@link WebTestClient.RequestHeadersSpec}
	 */
	default WebTestClient.ResponseSpec get(final String url) {
		return getWebTestClient().get().uri(concatUrl(getBaseUrl(), url)).exchange();
	}

	default String getApiUrl(String apiName) {
		var apiUrl = getApiUrlFromApiName(apiName);
		if (null == apiUrl) {
			fail("API URL is empty, getApiUrlFromApiName method should return API url for the given API Name");
		}
		return apiUrl;
	}

	/**
	 * Check if response status is Ok
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 */
	static void isOk(final WebTestClient.ResponseSpec responseSpec) {
		responseSpec.expectStatus().isOk();
	}

	/**
	 * Check if response content type is JSON
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 */
	static void isContentTypeJson(final WebTestClient.ResponseSpec responseSpec) {
		responseSpec.expectHeader().contentType(MediaType.APPLICATION_JSON);
	}

	/**
	 * Assert the body of response is equal to the given list and generated the API documentation.
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 * @param objClass     the object Class
	 * @param objList      the object List
	 * @param apiName      the API name
	 * @param genDoc       if true generated api documentation
	 */
	@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
	private static <E> void assertBodyEquals(
					final WebTestClient.ResponseSpec responseSpec,
					final Class<E> objClass,
					final List<E> objList,
					final String apiName,
					final Boolean genDoc) {
		var listBodySpec = assertBodyEquals(responseSpec, objClass, objList);
		if (Boolean.TRUE.equals(genDoc)) {
			generateApiDoc(listBodySpec, apiName);
		}
	}

	/**
	 * Concatenate an url to the base url. If base URL is null or blank return url
	 *
	 * @param baseUrl the base url
	 * @param url     the url
	 * @return the concatenated url
	 */
	private static String concatUrl(final String baseUrl, final String url) {
		return (null != baseUrl && !baseUrl.isBlank() ? baseUrl.concat(url) : url);
	}

	/**
	 * Assert the body of response is equal to the given list
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 * @param objClass     the object Class
	 * @param objList      the object List
	 * @return the {@link WebTestClient.ListBodySpec}
	 */
	private static <E> WebTestClient.ListBodySpec<E> assertBodyEquals(
					final WebTestClient.ResponseSpec responseSpec,
					final Class<E> objClass,
					final List<E> objList) {
		return responseSpec.expectBodyList(objClass).isEqualTo(objList);
	}

	/**
	 * Generate the API Documentation
	 *
	 * @param listBodySpec the {@link WebTestClient.ListBodySpec}
	 * @param apiName      the API name
	 */
	private static <E> void generateApiDoc(
					final WebTestClient.ListBodySpec<E> listBodySpec, final String apiName) {
		listBodySpec.consumeWith(document(apiName));
	}

	/**
	 * Send Get request and verify response
	 *
	 * @param apiName  the API Name
	 * @param dtoList  list of dto objects
	 * @param dtoClass the dto object class
	 */
	default <D> void verifyGetAndDocument(
					final String apiName, final List<D> dtoList, final Class<D> dtoClass) {
		verifyGet(apiName, dtoList, dtoClass, Boolean.TRUE);
	}

	/**
	 * Send Post request and verify response
	 *
	 * @param requestObj the request object
	 * @param apiName    the API Name
	 * @param dto        the dto objects
	 * @param dtoClass   the dto object class
	 */
	default <V, T> void verifyPost(
					final String apiName, final V requestObj, final T dto, final Class<T> dtoClass) {
		verifyPost(apiName, requestObj, dto, dtoClass, Boolean.FALSE);
	}

	/**
	 * Send Post request and verify response
	 *
	 * @param requestObj the request object
	 * @param apiName    the API Name
	 * @param dto        the dto objects
	 * @param dtoClass   the dto object class
	 * @param genDoc     if true generate documentation
	 */
	default <V, T> void verifyPost(
					final String apiName,
					final V requestObj,
					final T dto,
					final Class<T> dtoClass,
					final Boolean genDoc) {
		post(getApiUrl(apiName), requestObj)
						.expectAll(
										ControllerTest::isOk,
										ControllerTest::isContentTypeJson,
										responseSpec -> assertBodyEquals(responseSpec, dtoClass, dto, apiName, genDoc));
	}

	/**
	 * Do a post request by concatenating base url and url
	 *
	 * @param url       the url for the request
	 * @param reqObject the request Object
	 * @return the {@link WebTestClient.RequestHeadersSpec}
	 */
	default <R> WebTestClient.ResponseSpec post(final String url, final R reqObject) {
		return getWebTestClient()
						.post()
						.uri(concatUrl(getBaseUrl(), url))
						.accept(APPLICATION_JSON)
						.contentType(APPLICATION_JSON)
						.body(Mono.just(reqObject), reqObject.getClass())
						.exchange();
	}

	/**
	 * Assert the body of response is equal to the given object and generated the API documentation.
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 * @param objClass     the object Class
	 * @param respObj      the object List
	 * @param apiName      the API name
	 * @param genDoc       if true generated api documentation
	 */
	@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
	private static <E> void assertBodyEquals(
					final WebTestClient.ResponseSpec responseSpec,
					final Class<E> objClass,
					final E respObj,
					final String apiName,
					final boolean genDoc) {
		final var bodySpec = assertBodyEquals(responseSpec, objClass, respObj);
		if (Boolean.TRUE.equals(genDoc)) {
			generateApiDoc(bodySpec, apiName);
		}
	}

	/**
	 * Assert the body of response is equal to the given list
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 * @param objClass     the object Class
	 * @param respObj      the object List
	 * @param <E>          the Object Type
	 * @return the {@link WebTestClient.BodySpec}
	 */
	private static <E> WebTestClient.BodySpec<E, ?> assertBodyEquals(
					final WebTestClient.ResponseSpec responseSpec, final Class<E> objClass, final E respObj) {
		return responseSpec.expectBody(objClass).isEqualTo(respObj);
	}

	/**
	 * Generate the API Documentation
	 *
	 * @param bodySpec the {@link WebTestClient.BodySpec}
	 * @param apiName  the API name
	 */
	private static void generateApiDoc(
					final WebTestClient.BodySpec<?, ?> bodySpec, final String apiName) {
		bodySpec.consumeWith(document(apiName));
	}

	/**
	 * Send Post request and verify response
	 *
	 * @param requestObj the request object
	 * @param apiUrl     the API URL
	 * @param dto        the dto objects
	 * @param dtoClass   the dto object class
	 */
	default <T, D> void verifyPostAndDocument(
					final String apiUrl, final T requestObj, final D dto, final Class<D> dtoClass) {
		verifyPost(apiUrl, requestObj, dto, dtoClass, Boolean.TRUE);
	}
}
