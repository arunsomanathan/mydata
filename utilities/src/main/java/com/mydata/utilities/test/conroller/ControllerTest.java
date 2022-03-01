package com.mydata.utilities.test.conroller;

import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

import java.util.List;

public interface ControllerTest {

	/** Get the WebTestClient */
	WebTestClient getWebTestClient();

	/** Get the Base URL */
	String getBaseUrl();

	/**
	 * Send Get request and verify response
	 *
	 * @param apiUrl the API URL
	 * @param dtoList list of dto objects
	 * @param dtoClass the dto object class
	 */
	default <D> void verifyGet(final String apiUrl, final List<D> dtoList, final Class<D> dtoClass) {
		get(apiUrl)
						.expectAll(
										ControllerTest::isOk,
										ControllerTest::isContentTypeJson,
										responseSpec -> assertBodyEquals(responseSpec, dtoClass, dtoList, apiUrl));
	}

	/**
	 * Send Post request and verify response
	 *
	 * @param requestObj the request object
	 * @param apiUrl the API URL
	 * @param dto the dto objects
	 * @param dtoClass the dto object class
	 */
	default <T, D> void verifyPost(
					final String apiUrl, final T requestObj, final D dto, final Class<D> dtoClass) {
		post(apiUrl, requestObj)
						.expectAll(
										ControllerTest::isOk,
										ControllerTest::isContentTypeJson,
										responseSpec -> assertBodyEquals(responseSpec, dtoClass, dto, apiUrl));
	}

	/**
	 * Do a get request by concatenating base url and url
	 *
	 * @param url the url for the request
	 * @return the {@link WebTestClient.RequestHeadersSpec}
	 */
	private WebTestClient.ResponseSpec get(final String url) {
		return getWebTestClient().get().uri(concatUrl(getBaseUrl(), url)).exchange();
	}
	/**
	 * Do a post request by concatenating base url and url
	 *
	 * @param url the url for the request
	 * @param reqObject the request Object
	 * @return the {@link WebTestClient.RequestHeadersSpec}
	 */
	private <R> WebTestClient.ResponseSpec post(
					final String url,
					final R reqObject) {
		return getWebTestClient()
						.post()
						.uri(concatUrl(getBaseUrl(), url))
						.accept(APPLICATION_JSON)
						.contentType(APPLICATION_JSON)
						.body(Mono.just(reqObject), reqObject.getClass())
						.exchange();
	}

	/**
	 * Check if response status is Ok
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 */
	private static void isOk(final WebTestClient.ResponseSpec responseSpec) {
		responseSpec.expectStatus().isOk();
	}

	/**
	 * Check if response content type is JSON
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 */
	private static void isContentTypeJson(
					final WebTestClient.ResponseSpec responseSpec) {
		responseSpec.expectHeader().contentType(MediaType.APPLICATION_JSON);
	}

	/**
	 * Generate the API Documentation
	 *
	 * @param listBodySpec the {@link WebTestClient.ListBodySpec}
	 * @param apiName the API name
	 */
	private static <E> void generateApiDoc(
					final WebTestClient.ListBodySpec<E> listBodySpec, final String apiName) {
		listBodySpec.consumeWith(document(apiName));
	}

	/**
	 * Generate the API Documentation
	 *
	 * @param bodySpec the {@link WebTestClient.BodySpec}
	 * @param apiName the API name
	 */
	private static void generateApiDoc(
					final WebTestClient.BodySpec<?, ?> bodySpec, final String apiName) {
		bodySpec.consumeWith(document(apiName));
	}

	/**
	 * Assert the body of response is equal to the given list and generated the API documentation.
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 * @param objClass the object Class
	 * @param objList the object List
	 * @param apiName the API name
	 */
	private static <E> void assertBodyEquals(
					final WebTestClient.ResponseSpec responseSpec,
					final Class<E> objClass,
					final List<E> objList,
					final String apiName) {
		generateApiDoc(assertBodyEquals(responseSpec, objClass, objList), apiName);
	}

	/**
	 * Assert the body of response is equal to the given list
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 * @param objClass the object Class
	 * @param objList the object List
	 * @return the {@link WebTestClient.ListBodySpec}
	 */
	private static <E> WebTestClient.ListBodySpec<E> assertBodyEquals(
					final WebTestClient.ResponseSpec responseSpec,
					final Class<E> objClass,
					final List<E> objList) {
		return responseSpec.expectBodyList(objClass).isEqualTo(objList);
	}

	/**
	 * Assert the body of response is equal to the given object and generated the API documentation.
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 * @param objClass the object Class
	 * @param respObj the object List
	 * @param apiName the API name
	 */
	private static <E> void assertBodyEquals(
					final WebTestClient.ResponseSpec responseSpec,
					final Class<E> objClass,
					final E respObj,
					final String apiName) {
		generateApiDoc(assertBodyEquals(responseSpec, objClass, respObj), apiName);
	}

	/**
	 * Assert the body of response is equal to the given list
	 *
	 * @param responseSpec the {@link WebTestClient.ResponseSpec}
	 * @param objClass the object Class
	 * @param respObj the object List
	 * @param <E> the Object Type
	 * @return the {@link WebTestClient.BodySpec}
	 */
	private static <E> WebTestClient.BodySpec<E, ?> assertBodyEquals(
					final WebTestClient.ResponseSpec responseSpec, final Class<E> objClass, final E respObj) {
		return responseSpec.expectBody(objClass).isEqualTo(respObj);
	}

	/**
	 * Concatenate an url to the base url. If base URL is null or blank return url
	 *
	 * @param baseUrl the base url
	 * @param url the url
	 * @return the concatenated url
	 */
	private static String concatUrl(final String baseUrl, final String url) {
		return (null != baseUrl && !baseUrl.isBlank() ? baseUrl.concat("/").concat(url) : url);
	}
}
