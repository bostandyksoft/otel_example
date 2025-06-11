package org.example.otel_example.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class PriceRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(PriceRepository.class);

  private final ConcurrentHashMap<Long, Price> map = new ConcurrentHashMap<>();

  public Price getPrice(Long productId){
    LOGGER.info("Getting Price from Price Repo With Product Id {}", productId);
    if (!map.containsKey(productId)){
      LOGGER.error("Price Not Found for Product Id {}", productId);
      throw new Price.NotFoundException("Price Not Found");
    }
    return map.get(productId);
  }

  public void putPrice(Price price) {
    map.put(price.getProductId(), price);
  }
}
