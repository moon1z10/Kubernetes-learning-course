package hodlene.k8s.currencyconversion.Controller;

import hodlene.k8s.currencyconversion.model.CurrencyConversionBean;
import hodlene.k8s.currencyconversion.service.CurrencyExchangeServiceProxy;
import hodlene.k8s.currencyconversion.util.InstanceInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class CurrencyConversionController {

    @Autowired
    private InstanceInformationService instanceInformationService;

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @GetMapping("/")
    public String imHealthy() {
        return "{healthy:true}";
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {

        log.info("Received Request to convert from {} {} to {}. ", quantity, from, to);

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

        BigDecimal convertedValue = quantity.multiply(response.getConversionMultiple());

        String conversionEnvironmentInfo = instanceInformationService.retrieveInstanceInfo();

        return CurrencyConversionBean.builder()
                .id(response.getId())
                .from(from)
                .to(to)
                .conversionMultiple(response.getConversionMultiple())
                .quantity(quantity)
                .totalCalculatedAmount(convertedValue)
                .exchangeEnvironmentInfo(response.getExchangeEnvironmentInfo())
                .conversionEnvironmentInfo(conversionEnvironmentInfo)
                .build();
    }
}
