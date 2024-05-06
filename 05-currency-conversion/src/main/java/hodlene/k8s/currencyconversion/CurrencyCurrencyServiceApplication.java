package hodlene.k8s.currencyconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyCurrencyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyCurrencyServiceApplication.class, args);
    }

}
