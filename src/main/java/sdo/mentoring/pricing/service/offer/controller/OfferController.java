package sdo.mentoring.pricing.service.offer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdo.mentoring.pricing.service.brand.BrandService;
import sdo.mentoring.pricing.service.dto.Offer;
import sdo.mentoring.pricing.service.offer.modal.OfferEntity;
import sdo.mentoring.pricing.service.offer.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;
    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer savedOffer = offerService.saveOffer(offer);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOffer);
    }

    @DeleteMapping
    public ResponseEntity<OfferEntity> deleteAllOffers() {
        offerService.deleteAllOffer();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<OfferEntity> deleteOffer(@PathVariable Long offerId) {
        offerService.deleteOfferById(offerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<Offer>> getAllOffers() {

        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long offerId) {

        return ResponseEntity.ok(offerService.getOfferById(offerId));
    }

    @GetMapping("/{brandId}/partNumber/{partNumber}")
    public ResponseEntity<List<Offer>> getBrand(@PathVariable long brandId, @PathVariable String partNumber) {

        return ResponseEntity.ok(offerService.getOffersByBrandIdAndPartNumber(brandId, partNumber));
    }
}