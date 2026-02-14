package processor_service.demo;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Table(name = "match_balls")
@Data
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer matchId;
    private Integer runs;
    private LocalDateTime timestamp;
}
