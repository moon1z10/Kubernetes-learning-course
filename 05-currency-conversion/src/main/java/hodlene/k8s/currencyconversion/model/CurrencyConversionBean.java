package hodlene.k8s.currencyconversion.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyConversionBean {

    @Id
    private Long id;

    private String from;

    private String to;

    private BigDecimal conversionMultiple;

    private BigDecimal quantity;

    private BigDecimal totalCalculatedAmount;

    private String exchangeEnvironmentInfo;

    private String conversionEnvironmentInfo;
}
