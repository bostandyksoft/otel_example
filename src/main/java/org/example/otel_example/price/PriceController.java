package org.example.otel_example.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PriceController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PriceController.class);

  @Autowired
  private PriceRepository priceRepository;

  @GetMapping(path = "/price/{id}")
  public Price getPrice(@PathVariable("id") long productId) throws InterruptedException {
    if (Math.ceil(Math.random() * 100) % 2 == 0 ) {
      Thread.sleep(2000);
    }
    LOGGER.info("Getting Price details for Product Id {}", productId);
    return priceRepository.getPrice(productId);
  }

  @PutMapping("/price")
  public void updatePrice(@RequestBody Price price) throws InterruptedException {
    if (Math.ceil(Math.random() * 100) % 2 == 0 ) {
      Thread.sleep(2000);
    }
    LOGGER.info("Updating Price details for Product Id {}", price.getProductId());
    priceRepository.putPrice(price);
  }
}
