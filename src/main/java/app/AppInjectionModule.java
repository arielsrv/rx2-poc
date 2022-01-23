package app;

import app.common.RestClient;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class AppInjectionModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RestClient.class).annotatedWith(Names.named("currency"))
			.to(RestClient.class).in(Scopes.SINGLETON);

		bind(RestClient.class).annotatedWith(Names.named("quote"))
			.to(RestClient.class).in(Scopes.SINGLETON);
	}
}
