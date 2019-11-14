package se.terhol.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    @NotNull
    @Size(min = 1, max = 200)
    private String title;

    @Column(length = 1000)
    @Size(min = 1, max = 10000)
    private String description;

    @Min(1)
    private Float unitCost;

    @NotNull
    @Size(min = 1, max = 50)
    private String isbn;

    @Column()
    @Temporal(TemporalType.DATE)
    @Past
    private Date publicationDate;

    private int numberOfPages;

    private String imageURL;

    private Language language;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", unitCost=" + unitCost +
                ", isbn='" + isbn + '\'' +
                ", publicationDate=" + publicationDate +
                ", numberOfPages=" + numberOfPages +
                ", imageURL='" + imageURL + '\'' +
                ", language=" + language +
                '}';
    }
}
