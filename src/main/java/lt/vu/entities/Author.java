package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Author.findAll", query = "select a from Author as a")
})
@Table(name = "AUTHOR")
@Getter @Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinColumn(name="AUTHOR_ID")
    private List<Book> books;

    @OneToMany(mappedBy = "contact")
    private List<Contact> contacts;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Author() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) &&
                Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
