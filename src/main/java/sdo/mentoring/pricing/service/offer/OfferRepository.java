package sdo.mentoring.pricing.service.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    List<OfferEntity> findAll();
    List<OfferEntity> findByBrandIdAndPartNumber(Long brandId, String partNumber);
}
