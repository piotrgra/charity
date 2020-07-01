package pl.coderslab.charity.entity;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pl.coderslab.charity.enmu.InstitutionState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "institutions")
@SQLDelete(sql = "UPDATE institutions SET state = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "state <> 'DELETED'")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private InstitutionState state;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "institution")
    private List<Donation> donations = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public InstitutionState getState() {
        return state;
    }

    public void setState(InstitutionState state) {
        this.state = state;
    }

    public Institution() {
    }

    @PreRemove
    public void deleteInstitution() {
        this.state = InstitutionState.DELETED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institution that = (Institution) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
