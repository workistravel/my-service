package pl.dernovyi.myservice.models.dao;

import lombok.*;

import javax.persistence.*;
import java.util.*;
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employees")
@EqualsAndHashCode(exclude = "unions")
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


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE})
    @JoinTable(
            name = "employees_unions",
            joinColumns = @JoinColumn(name = "employees_id"),
            inverseJoinColumns = @JoinColumn(name = "unions_id"))
    Set<UnionDao> unions;

    public void addUnion(UnionDao unionDao) {
        unions.add( unionDao );
        unionDao.getEmployees().add( this );
    }

    public void removeUnion(UnionDao unionDao) {
        unions.remove( unionDao );
        unionDao.getEmployees().remove( this );
    }




}
