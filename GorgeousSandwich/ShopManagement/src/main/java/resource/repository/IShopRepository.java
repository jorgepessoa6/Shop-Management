package resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resource.model.Shop;

import java.util.UUID;

@Repository
public interface IShopRepository extends JpaRepository<Shop, UUID> {

    @Query("SELECT DISTINCT s FROM Shop s WHERE s.address = :address ")
    Shop getShopByAddress(@Param("address")String address);

    @Query("SELECT sto.id FROM Storage sto, Shop s WHERE s.id = :storeId AND sto MEMBER OF s.storages AND sto.sandwichId = :sandwichId")
    UUID getStorageByShopAndSandwich(@Param("sandwichId")UUID sandwichId, @Param("storeId") UUID storeId);
}
