package sdo.mentoring.pricing.service.offer.mapper;

import org.mapstruct.Mapper;
import sdo.mentoring.pricing.service.brand.BrandEntity;
import sdo.mentoring.pricing.service.dto.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toDto(BrandEntity brandEntity);
    BrandEntity toEntity(Brand brand);
}