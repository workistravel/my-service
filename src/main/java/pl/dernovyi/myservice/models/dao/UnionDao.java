package pl.dernovyi.myservice.models.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;
import pl.dernovyi.myservice.models.dto.EmployeeDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="union")
public class UnionDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50)
    private String name;
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToMany(mappedBy = "unions")
    private Set<EmployeeDao> employees;

//    @ManyToMany()
//    @JoinTable(name = "employee_union",
//            joinColumns = {@JoinColumn(name = "union_id", insertable = false, updatable = false, referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "employee_id", insertable = false, updatable = false, referencedColumnName = "id")}
//    )
//    @JsonBackReference
//    private List<EmployeeDao> employees;




}
