package pl.dernovyi.myservice.models.dao;

import com.sun.xml.bind.v2.schemagen.xmlschema.Union;
import lombok.*;

import javax.persistence.*;
import java.util.*;
@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employee")
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


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "employee_union",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "union_id"))
    Set<UnionDao> unions;

    public void addAddress(UnionDao unionDao) {
        unions.add( unionDao );
        unionDao.getEmployees().add( this );
    }

    public void removeAddress(UnionDao unionDao) {
        unions.remove( unionDao );
        unionDao.getEmployees().remove( this );
    }

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(name = "employee_union",
//            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "union_id", referencedColumnName = "id")})
//      private List<UnionDao> unions;


}
