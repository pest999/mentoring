package sdo.mentoring.pricing.service.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdo.mentoring.pricing.service.offer.modal.OfferEntity;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    List<OfferEntity> findAll();
    List<OfferEntity> findByBrandIdAndPartNumber(Long brandId, String partNumber);
}
