package com.mydata.userdata.utils;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

import java.util.List;
import org.springframework.test.web.reactive.server.WebTestClient;

/** Utility methods for WebTestClient */
public final class WebTestClientUtil {

  /**
   * Do a get request for the given url
   *
   * @param webTestClient the {@link WebTestClient}
   * @param url the url
   * @return the {@link WebTestClient.RequestHeadersSpec}
   */
  public static WebTestClient.ResponseSpec get(
      final WebTestClient webTestClient, final String url) {
    return get(webTestClient, "", url);
  }

  /**
   * Do a get request by concatenating base url and url
   *
   * @param webTestClient the {@link WebTestClient}
   * @param baseUrl the base url for the request
   * @param url the url for the request
   * @return the {@link WebTestClient.RequestHeadersSpec}
   */
  public static WebTestClient.ResponseSpec get(
      final WebTestClient webTestClient, final String baseUrl, final String url) {
    return webTestClient.get().uri(concatUrl(baseUrl, url)).exchange();
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

  /**
   * Check if response status is Ok
   *
   * @param responseSpec the {@link WebTestClient.ResponseSpec}
   * @return the {{@link WebTestClient.ResponseSpec}}
   */
  public static WebTestClient.ResponseSpec isOk(final WebTestClient.ResponseSpec responseSpec) {
    return responseSpec.expectStatus().isOk();
  }

  /**
   * Check if response content type is JSON
   *
   * @param responseSpec the {@link WebTestClient.ResponseSpec}
   * @return the {@link WebTestClient.ResponseSpec}
   */
  public static WebTestClient.ResponseSpec isContentTypeJson(
      final WebTestClient.ResponseSpec responseSpec) {
    return responseSpec.expectHeader().contentType(APPLICATION_JSON);
  }

  /**
   * Assert the body of response is equal to the given list and generated the API documentation.
   *
   * @param responseSpec the {@link WebTestClient.ResponseSpec}
   * @param objClass the object Class
   * @param objList the object List
   * @param apiName the API name
   * @param <E> the Object type
   * @return the {@link WebTestClient.ListBodySpec}
   */
  public static <E> WebTestClient.ListBodySpec<E> assertBodyEquals(
      final WebTestClient.ResponseSpec responseSpec,
      final Class<E> objClass,
      final List<E> objList,
      final String apiName) {
    return generateApiDoc(assertBodyEquals(responseSpec, objClass, objList), apiName);
  }

  /**
   * Generate the API Documentation
   *
   * @param listBodySpec the {@link WebTestClient.ListBodySpec} //TODO this may need to be changed
   * @param apiName the API name
   * @param <E> the Object Type
   * @return the {@link WebTestClient.ListBodySpec} //TODO this may need to be changed
   */
  public static <E> WebTestClient.ListBodySpec<E> generateApiDoc(
      final WebTestClient.ListBodySpec<E> listBodySpec, final String apiName) {
    return listBodySpec.consumeWith(document(apiName));
  }

  /**
   * Assert the body of response is equal to the given list
   *
   * @param responseSpec the {@link WebTestClient.ResponseSpec}
   * @param objClass the object Class
   * @param objList the object List
   * @param <E> the Object Type
   * @return the {@link WebTestClient.ListBodySpec}
   */
  public static <E> WebTestClient.ListBodySpec<E> assertBodyEquals(
      final WebTestClient.ResponseSpec responseSpec,
      final Class<E> objClass,
      final List<E> objList) {
    return responseSpec.expectBodyList(objClass).isEqualTo(objList);
  }
}
