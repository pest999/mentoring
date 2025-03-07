package sdo.mentoring.pricing.service.offer;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class OfferByPartNumberEntity {
    private Instant startDate;

    private Instant endDate;

    private BigDecimal price;

    private String currencyIso;

    private Integer priority;
}
