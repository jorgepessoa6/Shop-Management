package resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resource.dto.responseDtos.PromotionResponseDto;
import resource.model.Promotion;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Repository
public interface IPromotionRepository extends JpaRepository<Promotion, UUID> {
    @Query("SELECT p FROM Promotion p WHERE (p.shopId = :shopId OR p.shopId IS NULL) AND " +
            " :date BETWEEN p.startDate AND p.endDate")
    List<Promotion> getAvailablePromotions(@Param("date") Date date, @Param("shopId") UUID shopId);
}
