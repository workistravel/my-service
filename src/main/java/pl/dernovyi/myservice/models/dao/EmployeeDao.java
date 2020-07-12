package pl.dernovyi.myservice.models.dao;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    @Column(name="data_employ")
    private Date dateOfEmployment;

    private boolean isActive;

    //    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "employee_association",
//            joinColumns = @JoinColumn(name = "employee_id"),
//            inverseJoinColumns = @JoinColumn(name = "association_id"))
//    private Set<Union> associations = new HashSet<>();
}
