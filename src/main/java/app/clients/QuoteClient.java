package app.clients;

import app.common.Client;
import app.common.Response;
import app.common.RestClient;
import com.google.inject.name.Named;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class QuoteClient extends Client {

    @Inject
    public QuoteClient(@Named("currency") RestClient restClient) {
        super(restClient, "currency");
    }

    public Single<QuoteResponse> getLatest() {

        String url = "https://api.bluelytics.com.ar/v2/latest";

        return this.restClient.getSingle(url, QuoteResponse.class)
                .map(Response::getData);
    }

}

