package pl.dernovyi.myservice.models.dao;

import com.sun.xml.bind.v2.schemagen.xmlschema.Union;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employee")
@EqualsAndHashCode(exclude = "name")
public class EmployeeDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name="salary")
    private Double salary;
    @Column(name="date_employ")
    private Date dateOfEmployment;

    private boolean isActive;




    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "employee_union",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "union_id", referencedColumnName = "id")})
      private Set<UnionDao> unions = new HashSet<>();


}
