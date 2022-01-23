package app.services;

import app.clients.QuoteClient;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.reactivex.Single;

@Singleton
public class QuoteService {

	QuoteClient quoteClient;

	@Inject
	public QuoteService(QuoteClient quoteClient) {
		this.quoteClient = quoteClient;
	}

	public Single<QuoteDto> get() {
		return this.quoteClient.getLatest().map(quoteResponse -> {

			QuoteDto quoteDto = new QuoteDto();
			quoteDto.official = new QuoteDto.Official();
			quoteDto.official.Buy = quoteResponse.oficial.ValueBuy;
			quoteDto.official.Sell = quoteResponse.oficial.ValueSell;

			return quoteDto;
		});
	}
}

