package example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "get_person_by_name",
                query = "from Person where lastName like :name"
        ),
        @NamedQuery(
                name = "get_person_by_mobile",
                query = "select pr from Person pr join pr.phones ph where ph.type = :phoneType"
        ),
        @NamedQuery(
                name = "get_person_by_land_line",
                query = "select pr from Person pr left join pr.phones ph where ph is null or ph.type = :phoneType"
        )
})
@Entity
@Table(schema = "users", name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "person")
    private List<Phone> phones = new ArrayList<>();

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
    }

    public Person(String firstName, String lastName, String email, String country, List<Phone> phones) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public List<Phone> getPhones(){
        return phones;
    }

    public void setPhones(List<Phone> phones){
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", country=" + country + "]";
    }

}
