package app.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Single;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleRequestProducer;
import org.apache.hc.client5.http.async.methods.SimpleResponseConsumer;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.Method;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Future;

public class RestClient {

    final IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
            .setSoTimeout(Timeout.ofSeconds(10))
            .build();

    final CloseableHttpAsyncClient client = HttpAsyncClients.custom()
            .setIOReactorConfig(ioReactorConfig)
            .build();

    ObjectMapper objectMapper;

    public RestClient() {
        this.objectMapper = new ObjectMapperFactory().build();
    }

    public <T> Single<Response<T>> getSingle(String url, Class<T> clazz) {

        try {
            client.start();
            SimpleHttpRequest request = new SimpleHttpRequest(Method.GET, new URI(url));
            System.out.println("Executing request " + request);
            final Future<SimpleHttpResponse> future = client.execute(
                    SimpleRequestProducer.create(request),
                    SimpleResponseConsumer.create(),
                    new FutureCallback<SimpleHttpResponse>() {
                        @Override
                        public void completed(final SimpleHttpResponse response) {
                            System.out.println(request + "->" + new StatusLine(response));
                            System.out.println(response.getBody());
                        }

                        @Override
                        public void failed(final Exception ex) {
                            System.out.println(request + "->" + ex);
                        }

                        @Override
                        public void cancelled() {
                            System.out.println(request + " cancelled");
                        }
                    });

            return Single.fromFuture(future).map(simpleHttpResponse -> {
                T value = objectMapper.readValue(simpleHttpResponse.getBodyBytes(), clazz);
                Response<T> result = new Response<>();
                result.data = value;
                return result;
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Single.just(null);
    }
}
