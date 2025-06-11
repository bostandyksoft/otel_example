package org.example.otel_example.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepository.class);

  private final ConcurrentHashMap<Long, Product> map = new ConcurrentHashMap<>();

  public Product getProduct(Long productId) {
    LOGGER.info("Getting Product from Product Repo With Product Id {}", productId);
    if (!map.containsKey(productId)) {
      LOGGER.error("Product Not Found for Product Id {}", productId);
      throw new Product.NotFoundException("Product Not Found");
    }
    return map.get(productId);
  }

  public Product putProduct(Product product) {
    return map.put(product.getId(), product);
  }
}
