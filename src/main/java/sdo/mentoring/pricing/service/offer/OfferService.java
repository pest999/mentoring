package sdo.mentoring.pricing.service.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    public OfferEntity saveOffer(OfferEntity offer) {
        return offerRepository.save(offer);
    }

    public void deleteAllOffer() {
        offerRepository.deleteAll();
    }

    public void deleteOfferById(Long offerId) {
        offerRepository.deleteById(offerId);
    }

    public List<OfferEntity> getAllOffers() {

        return offerRepository.findAll();
    }

    public OfferEntity getOfferById(Long offerId) {
        return offerRepository.findById(offerId).orElse(null);
    }
}
