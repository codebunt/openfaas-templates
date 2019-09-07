package io.huduku.openfaas.entrypoint;

import io.huduku.openfaas.function.OpenFaasHandler;
import io.undertow.Undertow;

public class App {
    public static void main(final String[] args) {
        Undertow server = Undertow.builder()
                .addHttpListener(8081, "127.0.0.1")
                .setHandler(new OpenFaasHandler()).build();
        server.start();
    }
}
