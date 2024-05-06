package hodlene.k8s.currencyexchange.controller;

import hodlene.k8s.currencyexchange.model.ExchangeValue;
import hodlene.k8s.currencyexchange.service.ExchangeValueRepository;
import hodlene.k8s.currencyexchange.util.InstanceInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CurrencyExchangeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private ExchangeValueRepository repository;

    @Autowired
    private InstanceInformationService instanceInformationService;

    @GetMapping("/")
    public String imHealthy() {
        return "{healthy:true}";
    }

    //http://localhost:8000/currency-exchange/from/USD/to/KRW
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to,
                                               @RequestHeader Map<String, String> headers) {
        printAllHeaders(headers);
        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

        LOGGER.info("{} {} {}", from, to, exchangeValue);

        if (exchangeValue == null) {
            throw new RuntimeException("Unable to find data to convert " + from + " to " + to);
        }

        exchangeValue.setExchangeEnvironmentInfo(instanceInformationService.retrieveInstanceInfo());

        return exchangeValue;
    }

    private void printAllHeaders(Map<String, String> headers) {
        headers.forEach((key, value) -> {
            LOGGER.info(String.format("Header '%s' = %s", key, value));
        });
    }
}
