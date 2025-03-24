package sdo.mentoring.pricing.service.offer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sdo.mentoring.pricing.service.dto.Offer;
import sdo.mentoring.pricing.service.offer.modal.OfferEntity;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    @Mapping(source = "partNumber", target = "productPartnumber")
    Offer toDto(OfferEntity entity);

    @Mapping(source = "productPartnumber", target = "partNumber")
    OfferEntity toEntity(Offer offer);
}
