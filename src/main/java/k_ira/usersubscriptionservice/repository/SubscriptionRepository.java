package k_ira.usersubscriptionservice.repository;

import k_ira.usersubscriptionservice.entity.Subscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findAllByUserId(Long userId);

    @Query("SELECT s.title, COUNT(s) as cnt FROM Subscription s GROUP BY s.title ORDER BY cnt DESC")
    List<Object[]> findTopSubscriptions(Pageable pageable);
}
