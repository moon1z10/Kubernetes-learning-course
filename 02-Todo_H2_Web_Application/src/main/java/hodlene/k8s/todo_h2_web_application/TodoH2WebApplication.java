package hodlene.k8s.todo_h2_web_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TodoH2WebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TodoH2WebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TodoH2WebApplication.class);
    }
}
