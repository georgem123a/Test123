package example.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(schema = "users", name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "acc_number")
    private String accountNumber;

    @Column(name = "date_employment")
    private LocalDate dateEmployment;

    @OneToOne(mappedBy = "account")
    private Employee employee;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public LocalDate getDateEmployment(){
        return dateEmployment;
    }

    public void setDateEmploymentl(LocalDate dateEmployment){
        this.dateEmployment = dateEmployment;
    }

    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public String toString(){
        return "Account [id=" + id + ", accountNumber=" + accountNumber + ", dateEmployment=" + dateEmployment.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "}";
    }
}
