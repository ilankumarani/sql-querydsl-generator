package xio.ilan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yov.ilan.entity.Channel;

@Repository
public interface ForumRepository extends JpaRepository<Channel, Long> {
}
