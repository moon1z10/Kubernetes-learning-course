package hodlene.k8s.hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity(name = "Members")
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
