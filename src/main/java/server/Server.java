package server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import properties.Config;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * Created by Impresyjna on 27.12.2016.
 */
public class Server {

    private static HttpServer serverInstance;

    public static void main( String[] args ){
        try {
            URI baseUri = UriBuilder.fromUri("http://" + Config.SERVER_ADDR).port(Config.SERVER_PORT_REST).build();
            ResourceConfig config = new CustomResourceConfig();
            server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config, false);
            initializeSOAPServer();
            serverInstance.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeSOAPServer() {
        NetworkListener networkListener = new NetworkListener("jaxws-listener", "0.0.0.0", Config.SERVER_PORT_SOAP);
//        serverInstance.getServerConfiguration().addHttpHandler(new JaxwsHandler(new UserService()), "/users");
//        serverInstance.getServerConfiguration().addHttpHandler(new JaxwsHandler(new BankAccountService()), "/bankAccounts");
//        serverInstance.getServerConfiguration().addHttpHandler(new JaxwsHandler(new BankOperationService()), "/bankOperations");
        serverInstance.addListener(networkListener);
    }
}