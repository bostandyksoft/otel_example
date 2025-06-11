package org.example.otel_example;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RestController
@RequestMapping("test")
public class TestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

  private final Tracer tracer;

  @Autowired
  public TestController(Tracer tracer) {
    this.tracer = tracer;
  }

  @GetMapping("/fast")
  public String fastEndpoint() {
    Span span = tracer.spanBuilder("fast").startSpan();
    try {
      LOGGER.info("fast");
      return "Fast response!";
    } finally {
      span.end();
    }
  }

  @GetMapping("/slow")
  public String slowEndpoint() throws InterruptedException {
    Span span = tracer.spanBuilder("slow").startSpan();
    try {
      LOGGER.info("before sleep");
      Thread.sleep(1000); // Имитация медленного запроса (1 сек)
      LOGGER.info("after sleep");
      return "Slow response!";
    } finally {
      span.end();
    }
  }

  @GetMapping("/error")
  public String errorEndpoint() {
    Span span = tracer.spanBuilder("error").startSpan();
    try {
      LOGGER.error("error");
      throw new RuntimeException("Simulated error!");
    } finally {
      span.end();
    }
  }
}
