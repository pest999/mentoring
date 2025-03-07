package sdo.mentoring.pricing.service.brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import sdo.mentoring.pricing.service.offer.OfferEntity;
import sdo.mentoring.pricing.service.offer.OfferRepository;

import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class BrandService {
    private final OfferRepository offerRepository;

    public List<OfferEntity> getBrandByIdAndPartNumber(Long id, String partNumber) {

        return offerRepository.findByBrandIdAndPartNumber(id, partNumber);
    }
}