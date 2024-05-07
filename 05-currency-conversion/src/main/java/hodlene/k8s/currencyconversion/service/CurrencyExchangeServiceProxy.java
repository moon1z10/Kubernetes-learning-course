package hodlene.k8s.currencyconversion.service;

import hodlene.k8s.currencyconversion.model.CurrencyConversionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000") // 서비스가 쿠버네티스에 등록되면 쿠버네티스 DNS에 등록됨. CURRENCY_EXCHANGE_URI는 deployment.yaml파일에 env(환경변수)로 정의되어 있음.
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000") // --> Pod가 구동할 때 서비스에 등록된 환경 변수를 가져와서 설정. 문제는 currency-exchange 서비스가 구동되어 있지 않으면, CURRENCY_EXCHANGE_SERVICE_HOST를 알수 없음.
//@FeignClient(name = "currency-exchange-service") //Kubernetes Service Name
public interface CurrencyExchangeServiceProxy {
    @GetMapping("F{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
                                                        @PathVariable("to") String to);
}
