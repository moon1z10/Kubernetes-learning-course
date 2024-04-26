package hodlene.k8s.hello_world_rest_api;

import hodlene.k8s.hello_world_rest_api.env.InstanceInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private InstanceInformationService service;

    @GetMapping(path = "/")
    public String imUpAndRunning() {
        return "{healthy: true}";
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World " + " V1 " + service.retrieveInstanceInfo();
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("This is HelloWorldBean Message!");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
