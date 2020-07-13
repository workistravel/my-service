package pl.dernovyi.myservice.models.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;
import pl.dernovyi.myservice.models.dto.EmployeeDto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="union")
@EqualsAndHashCode(exclude = "name")
public class UnionDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @ManyToMany()
    @JoinTable(name = "employee_union",
            joinColumns = {@JoinColumn(name = "union_id", insertable = false, updatable = false, referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id", insertable = false, updatable = false, referencedColumnName = "id")}
    )
    @JsonBackReference
    private Set<EmployeeDao> employees;
}
