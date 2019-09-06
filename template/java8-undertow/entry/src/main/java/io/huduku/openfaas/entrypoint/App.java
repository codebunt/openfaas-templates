package io.huduku.openfaas.entrypoint;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.LinkedList;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

public class App {
    public static void main(final String[] args) {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "127.0.0.1")
                .setHandler(new HttpHandler() {
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                    	String fn = exchange.getQueryParameters().getOrDefault("_fn",new LinkedList<String>()).getFirst();
                        if(fn == null || fn.trim().equals("")) {
                        	
                        } else {
                        	
                        }
                        exchange.endExchange();
                    }
                }).build();
        server.start();
    }
    
	private static String getBodyAsString(HttpServerExchange exchange) throws Exception {
        String requestBody = "";
        String method = exchange.getRequestMethod().toString();
        if (method.equalsIgnoreCase("POST")) {
            InputStream inputStream = exchange.getInputStream();
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            requestBody = result.toString("UTF-8");
        }
        return requestBody;
	}
}
