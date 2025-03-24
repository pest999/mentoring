package sdo.mentoring.pricing.service.offer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sdo.mentoring.pricing.service.dto.Offer;
import sdo.mentoring.pricing.service.offer.exception.EntityNotFoundException;
import sdo.mentoring.pricing.service.offer.mapper.OfferMapper;
import sdo.mentoring.pricing.service.offer.modal.OfferEntity;
import sdo.mentoring.pricing.service.offer.repository.OfferRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    public Offer saveOffer(Offer offer) {
        log.info("Saving offer: {}", offer);

        return offerMapper.toDto(offerRepository.save(offerMapper.toEntity(offer)));
    }

    public void deleteAllOffer() {
        log.warn("Deleting all offers");
        offerRepository.deleteAll();
    }

    public void deleteOfferById(Long offerId) {
        log.info("Deleting offer with ID: {}", offerId);
        offerRepository.deleteById(offerId);
    }

    public List<Offer> getAllOffers() {
        log.info("Fetching all offers");
//        List<OfferEntity> entites = offerRepository.findAll();
//        List<Offer> offers = new ArrayList<>();
//        for(var item: entites){
//            offers.add(offerMapper.toDto(item));
//        }
//
//        return offers;
          return offerRepository.findAll()
                  .stream()
                  .map(offerMapper::toDto)
                  .collect(Collectors.toList());
    }

    public Offer getOfferById(Long offerId) {
        log.info("Fetching offer with ID: {}", offerId);
        OfferEntity offerEntity  = offerRepository.findById(offerId)
                .orElseThrow(()-> {
                    log.error("Offer with ID {} not found", offerId);
                    return new EntityNotFoundException("Offer with ID " + offerId + " not found");
                });

        return offerMapper.toDto(offerEntity );
    };

    public List<Offer> getOffersByBrandIdAndPartNumber(Long brandId, String partNumber){
      log.info("Fetching offers with brand ID {} and part number {}", brandId, partNumber);
      List<OfferEntity> entities = offerRepository.findByBrandIdAndPartNumber(brandId, partNumber);

      return entities.stream()
              .map(offerMapper::toDto)
              .collect(Collectors.toList());
    };
}
