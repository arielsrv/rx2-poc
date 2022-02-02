package app;

import app.common.ObjectMapperFactory;
import app.controllers.QuoteController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jooby.Jooby;
import io.jooby.OpenAPIModule;
import io.jooby.ServerOptions;
import io.jooby.SslOptions;
import io.jooby.di.GuiceModule;
import io.jooby.json.JacksonModule;

public class App extends Jooby {

	{
		ObjectMapper objectMapper = new ObjectMapperFactory().build();
		install(new JacksonModule(objectMapper));
		install(new GuiceModule(new AppInjectionModule()));
		install(new OpenAPIModule());

		mvc(QuoteController.class);
		mvc(Controller.class);
	}

	public static void main(final String[] args) {
		runApp(args, App::new);
	}
}
