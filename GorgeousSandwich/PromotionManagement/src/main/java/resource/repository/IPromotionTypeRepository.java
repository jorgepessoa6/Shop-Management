package resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import resource.model.PROMOTIONKIND;
import resource.model.PromotionType;

import java.util.List;
import java.util.UUID;

public interface IPromotionTypeRepository  extends JpaRepository<PromotionType, UUID> {
    @Query("SELECT pt FROM PromotionType pt")
    List<PromotionType> findPromotionTypes();
}
