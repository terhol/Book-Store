package se.terhol.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 200)
    private String title;
    @Column(length = 1000)
    private String description;

    private Float unitCost;

    private String isbn;
    @Column()
    @Temporal(TemporalType.DATE)
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
