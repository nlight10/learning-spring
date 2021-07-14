package kz.gm.webfluxwebsocketsmallrest.clients;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import java.net.URI;

/**
 * Daddy: GM
 * BirthDate: 14.07.2021
 */
public class CarWebSocketClient {

    public static void main(String[] args) {
        WebSocketClient client = new ReactorNettyWebSocketClient();

        client.execute(URI.create("ws://localhost:8080/car-feed"), session -> session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(System.out::println)
                .then())
                .block();
    }
}
