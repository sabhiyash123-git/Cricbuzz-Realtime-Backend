package processor_service.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BallRepository extends JpaRepository<Entity , Long> {
}
