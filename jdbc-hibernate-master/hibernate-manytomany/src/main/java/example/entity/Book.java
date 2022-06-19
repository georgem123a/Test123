package example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "users", name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_to_authors")
    private Set<Author> authors;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Set<Author> getAuthors(){
        return authors;
    }

    public void setAuthors(Set<Author> authors){
        this.authors = authors;
    }
}
