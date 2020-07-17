package pl.dernovyi.myservice.models.dao;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@Table(name="unions")
@EqualsAndHashCode(exclude = "employees")
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Union")
@ToString
public class UnionDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "unions")

    private Set<EmployeeDao> employees;

//    @Override
//    public String toString() {
//        final StringBuffer sb = new StringBuffer();
//        sb.append(id);
//        sb.append("-").append(name);
//        return sb.toString();
//    }

}
