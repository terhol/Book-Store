package se.terhol.repository;

import se.terhol.model.Book;
import se.terhol.util.NumberGenerator;
import se.terhol.util.TextUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class BookRepository {

    @PersistenceContext(unitName = "bookStorePU")
    private EntityManager manager;

    @Inject
    private TextUtil textUtil;

    @Inject
    private NumberGenerator generator;


    public Book find(@NotNull Long id) {
        return manager.find(Book.class, id);

    }

    @Transactional(REQUIRED)
    public Book create(@NotNull Book book) {
        book.setTitle(textUtil.sanitize(book.getTitle()));
        book.setIsbn(generator.generate());
        manager.persist(book);
        return book;
    }

    @Transactional(REQUIRED)
    public void delete(@NotNull Long id) {
        manager.remove(manager.getReference(Book.class, id));
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = manager.createQuery("SELECT b from Book b order by b.title", Book.class);
        return query.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> query = manager.createQuery("SELECT count(b) from Book b", Long.class);
        return query.getSingleResult();
    }
}
