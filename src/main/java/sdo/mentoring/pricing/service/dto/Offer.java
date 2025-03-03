package sdo.mentoring.pricing.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class Offer implements Serializable {

    private Long offerId;

    private Integer brandId;

    private Instant startDate;

    private Instant endDate;

    private Long priceListId;

    private String productPartnumber;

    private Integer priority;
    
    private BigDecimal price;

    private String currencyIso;

}