package app;

import io.jooby.annotations.GET;
import io.jooby.annotations.Path;

@Path("/")
public class Controller {

    @GET
    public String sayHi() {
        return "Welcome to Jooby!";
    }
}
