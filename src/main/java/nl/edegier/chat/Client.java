package nl.edegier.chat;

import io.vertx.core.Vertx;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;

import java.util.UUID;

/**
 * Created by Erwin on 15/11/2017.
 */
public class Client extends AbstractVerticle {
    private static  final String NAME = UUID.randomUUID().toString().substring(0,5);
    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new Client());
    }

    @Override
    public void start() throws Exception {
        HttpClient client = vertx.createHttpClient();

        client.websocket(8080, "localhost", "", websocket -> {
            websocket.handler(data -> System.out.println(data.toString("ISO-8859-1")));
            websocket.writeTextMessage(NAME+ ":hello from client");
        });

    }

}
