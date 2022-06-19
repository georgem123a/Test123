package example.entity;

import javax.persistence.*;

@Entity
@Table(schema = "users", name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Person person;

    @Column(name = "phone_number")
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type")
    private PhoneType type;

    public Phone(String number, PhoneType type, Person person){
        this.number = number;
        this.type = type;
        this.person = person;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public PhoneType getType(){
        return type;
    }

    public void setType(PhoneType type){
        this.type = type;
    }
}
