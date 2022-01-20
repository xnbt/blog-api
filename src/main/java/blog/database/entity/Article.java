package blog.database.entity;


import blog.dto.ArticleDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "article")
@Table(name = "article")
@Data
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String authName;

    public Article(String authName) {
        this.authName = authName;
    }

    @Embedded
    private Audit audit = new Audit();

    @ManyToMany
    @JoinTable(
            name = "article_tag",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonManagedReference
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<UserReact> userReacts = new ArrayList<>();

    public Article(Long id){
        this.id = id;
    }
    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getArticles().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getArticles().remove(this);
    }

    public ArticleDTO toDTO() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(id);
        articleDTO.setAuthName(authName);
        articleDTO.setTags(getTagsName());

        return articleDTO;
    }

    public List<String> getTagsName() {
        return tags.stream().map(Tag::getName).collect(Collectors.toList());
    }
}
