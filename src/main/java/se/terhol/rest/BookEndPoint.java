package se.terhol.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import se.terhol.model.Book;
import se.terhol.repository.BookRepository;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/books")
@Api("Book")
public class BookEndPoint {

    @Inject
    private BookRepository bookRepository;

    @GET
    @Path("/{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns a book given id", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book found"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Book not found")
    })
    public Response getBook(@PathParam("id") @Min(1) Long id) {
        Book book = bookRepository.find(id);

        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(book).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a book given a Json book representation")
    @ApiResponses(value = {
            @ApiResponse(code = 210, message = "Book is created"),
            @ApiResponse(code = 415, message = "Not a Json format")
    })
    public Response createBook(Book book, @Context UriInfo uriInfo) {
        book = bookRepository.create(book);
        URI createdUri = uriInfo.getBaseUriBuilder().path(book.getId().toString()).build();
        return Response.created(createdUri).build();
    }

    @DELETE
    @Path("/{id: \\d+}")
    @ApiOperation(value = "Deletes book given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Book has been deleted"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Book not found")
    })
    public Response deleteBook(@PathParam("id") @Min(1) Long id) {
        bookRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Lists all books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Books found"),
            @ApiResponse(code = 204, message = "Books not found")
    })
    public Response getAllBooks() {
        List<Book> allBooks = bookRepository.findAll();

        if (allBooks.size() == 0) {
            return Response.noContent().build();
        }
        return Response.ok(allBooks).build();

    }

    @GET
    @Path("/count")
    @ApiOperation("Returns number of all books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Number of books found"),
            @ApiResponse(code = 204, message = "No books found")

    })
    public Response countAllBooks() {
        Long nbOfBooks = bookRepository.countAll();
        if (nbOfBooks == 0) {
            return Response.noContent().build();
        }

        return Response.ok(nbOfBooks).build();
    }
}
