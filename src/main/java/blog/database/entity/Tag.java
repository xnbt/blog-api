package blog.database.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tag")
@Table(name = "tag")
@Data
@NoArgsConstructor
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Embedded
    private Audit audit = new Audit();

    public Tag(String name) {
        this.name = name;
    }
    @JsonBackReference
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    List<Article> articles = new ArrayList<>();
}
