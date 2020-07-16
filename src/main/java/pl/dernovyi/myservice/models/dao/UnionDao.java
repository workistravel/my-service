package pl.dernovyi.myservice.models.dao;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;


@Entity
@SuperBuilder
@Table(name="unions")
@EqualsAndHashCode(exclude = "employees")
@AllArgsConstructor
@NoArgsConstructor
public class UnionDao {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "unions")
    private Set<EmployeeDao> employees;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(id);
        sb.append("-").append(name);
        return sb.toString();
    }
}
