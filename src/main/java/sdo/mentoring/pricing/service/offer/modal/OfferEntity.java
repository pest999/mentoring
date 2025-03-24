package sdo.mentoring.pricing.service.offer.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "offers")
public class OfferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long offerId;

    private Integer brandId;

    private Instant startDate;

    private Instant endDate;

    private Long priceListId;

    private String partNumber;

    private Integer priority;

    private BigDecimal price;

    private String currencyIso;
}