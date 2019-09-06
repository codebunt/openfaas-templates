package io.huduku.openfaas.function;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

/**
 * The Class OpenFaasHandler.
 *
 * @author shettypk
 */
public class OpenFaasHandler implements HttpHandler {

	public void handleRequest(HttpServerExchange res) throws Exception {
    	res.getResponseSender().send("Hello World");
	}
	
}
