package io.huduku.openfaas.function;
import io.undertow.server.HttpServerExchange;

public class Handler {
    public static void handleDefault(HttpServerExchange res) {
    	res.getResponseSender().send("Hello World");
    }
}
