package org.example.otel_example.product;

import org.example.otel_example.price.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class PriceClient {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

  @Value("${price_app_url:http://localhost:8080}")
  private String baseUrl;

  private final RestTemplate restTemplate;

  @Autowired
  public PriceClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Price getPrice(@PathVariable("id") long productId){
    LOGGER.info("Fetching Price Details With Product Id {}", productId);
    String url = String.format("%s/price/%d", baseUrl, productId);
    ResponseEntity<Price> price = restTemplate.getForEntity(url, Price.class);
    return price.getBody();
  }

}
