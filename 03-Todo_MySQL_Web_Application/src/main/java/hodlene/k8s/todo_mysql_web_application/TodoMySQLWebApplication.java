package hodlene.k8s.todo_mysql_web_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TodoMySQLWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TodoMySQLWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TodoMySQLWebApplication.class);
    }
}
