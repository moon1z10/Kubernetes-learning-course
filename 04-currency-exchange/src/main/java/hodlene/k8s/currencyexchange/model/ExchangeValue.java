package hodlene.k8s.currencyexchange.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExchangeValue {

    @Id
    private Long id;

    @Column(name = "currency_from")
    private String from;

    @Column(name = "currency_to")
    private String to;

    private BigDecimal conversionMultiple;

    private String exchangeEnvironmentInfo;

    @Override
    public String toString() {
        return "ExchangeValue [id=" + id + ", from=" + from + ", to=" + to + ", conversionMultiple="
                + conversionMultiple + ", exchangeEnvironmentInfo=" + exchangeEnvironmentInfo + "]";
    }
}
