package app.controllers;

import app.services.QuoteDto;
import app.services.QuoteService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.newrelic.api.agent.Trace;
import io.jooby.annotations.GET;
import io.jooby.annotations.Path;
import io.reactivex.Single;


@Path("/currency")
@Singleton
public class QuoteController {

	QuoteService quoteService;

	@Inject
	public QuoteController(QuoteService quoteService) {
		this.quoteService = quoteService;
	}

	@Trace(dispatcher = true)
	@GET
	public Single<QuoteDto> get() {
		return this.quoteService.get();
	}
}
