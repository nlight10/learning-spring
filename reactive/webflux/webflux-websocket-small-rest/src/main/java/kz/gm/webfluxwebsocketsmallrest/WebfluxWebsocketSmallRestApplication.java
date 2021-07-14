package kz.gm.webfluxwebsocketsmallrest;

import kz.gm.webfluxwebsocketsmallrest.clients.CarWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebfluxWebsocketSmallRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxWebsocketSmallRestApplication.class, args);

		CarWebClient carWebClient = new CarWebClient();
		carWebClient.consume();
	}

}
