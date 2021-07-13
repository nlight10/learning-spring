package kz.gm.webfluxwebclientsmallrest;

import kz.gm.webfluxwebclientsmallrest.clients.CarWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebfluxWebClientSmallRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxWebClientSmallRestApplication.class, args);

		CarWebClient carWebClient = new CarWebClient();
		carWebClient.consume();
	}

}
