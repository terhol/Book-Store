package se.terhol.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Book resource representation")
public class Book {
    @Id
    @ApiModelProperty("Identifier")
    private Long id;

    @Column(length = 200)
    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty("Title of the book")
    private String title;

    @Column(length = 1000)
    @Size(min = 1, max = 10000)
    @ApiModelProperty("Description of the book")
    private String description;

    @Min(1)
    @Column(name = "unit_cost")
    @ApiModelProperty("Cost of one unit")
    private Float unitCost;

    @NotNull
    @Size(min = 1, max = 50)
    @ApiModelProperty("ISBN code")
    private String isbn;


    @Temporal(TemporalType.DATE)
    @Past
    @Column(name = "publication_date")
    @ApiModelProperty("Date of publication")
    private Date publicationDate;

    @Column(name = "nb_of_pages")
    @ApiModelProperty("Number of pages")
    private int numberOfPages;

    @Column(name = "image_url")
    @ApiModelProperty("URL of image of the book")
    private String imageURL;

    @ApiModelProperty("Language of the book")
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
