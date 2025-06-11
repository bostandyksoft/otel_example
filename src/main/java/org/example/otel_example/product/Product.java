package org.example.otel_example.product;

import org.example.otel_example.price.Price;

public class Product {
  private long id;
  private String name;
  private Price price;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Price getPrice() {
    return price;
  }

  public void setPrice(Price price) {
    this.price = price;
  }

  public static class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
      super(message);
    }
  }
}