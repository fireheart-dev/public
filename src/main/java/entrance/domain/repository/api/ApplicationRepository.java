package entrance.domain.repository.api;

import entrance.domain.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "select * from application a where a.contact_id = :contact_id order by a.dt_created desc fetch first 1 row only", nativeQuery = true)
    Application findRecentByContactId(@Param("contact_id") Long contactId);

    @Query(value = "select a from Application a where a.productName = :name")
    List<Application> findByProductName(@Param("name") String name);
}
