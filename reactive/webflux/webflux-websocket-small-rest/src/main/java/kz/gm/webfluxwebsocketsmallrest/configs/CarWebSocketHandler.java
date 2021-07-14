package kz.gm.webfluxwebsocketsmallrest.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.gm.webfluxwebsocketsmallrest.events.CarCreationEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

import static java.time.LocalDateTime.now;


/**
 * Daddy: GM
 * BirthDate: 14.07.2021
 */
@Component("CarWebSocketHandler")
public class CarWebSocketHandler implements WebSocketHandler {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {

        Flux<String> carCreationEvent = Flux.generate(sink -> {
            CarCreationEvent event = new CarCreationEvent(new Random().nextLong(), now().toString());
            try {
                sink.next(mapper.writeValueAsString(event));
            } catch (JsonProcessingException e) {
                sink.error(e);
            }
        });

        return webSocketSession.send(carCreationEvent
                .map(webSocketSession::textMessage)
                .delayElements(Duration.ofSeconds(1)));
    }
}
