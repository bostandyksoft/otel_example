package org.example.otel_example.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private PriceClient priceClient;

  @Autowired
  private ProductRepository productRepository;

  @GetMapping(path = "/product/{id}")
  public Product getProductDetails(@PathVariable("id") long productId) throws InterruptedException {
    if (Math.ceil(Math.random() * 100) % 2 == 0 ) {
      Thread.sleep(2000);
    }
    LOGGER.info("Getting Product and Price Details with Product Id {}", productId);
    Product product = productRepository.getProduct(productId);
    product.setPrice(priceClient.getPrice(productId));
    return product;
  }

  @PutMapping(path = "/product")
  public Product updateProduct(@RequestBody Product product) throws InterruptedException {
    if (Math.ceil(Math.random() * 100) % 2 == 0 ) {
      Thread.sleep(2000);
    }
    LOGGER.info("Updating Product and Price Details with Product Id {}", product.getId());
    return productRepository.putProduct(product);
  }
}
