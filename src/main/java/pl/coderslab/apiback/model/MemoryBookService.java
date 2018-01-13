package pl.coderslab.apiback.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class MemoryBookService implements BookService {
	private static long nextId = 1L;
	private List<Book> list;

	public MemoryBookService() {
		this.list = new ArrayList<>();

		add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming"));
		add(new Book(2L, "9788324627738", "Rusz glowa, Java.", "Sierra Kathy, Bates Bert", "Helion",
				"programming"));
		add(new Book(3L, "9780130819338", "Java 2. Podstawy", "Cay Horstmann, Gary Cornell", "Helion",
				"programming"));
	}

	/* (non-Javadoc)
	 * @see pl.coderslab.apiback.model.BookService#getList()
	 */
	@Override
	public List<Book> getList() {
		return list;
	}

	/* (non-Javadoc)
	 * @see pl.coderslab.apiback.model.BookService#setList(java.util.List)
	 */
	@Override
	public void setList(List<Book> list) {
		this.list = list;
	}

	/* (non-Javadoc)
	 * @see pl.coderslab.apiback.model.BookService#getById(long)
	 */
	@Override
	public Book getById(long id) throws NoSuchElementException {
		Predicate<Book> bookById = c -> c.getId() == id;
		Book book = this.list.stream().filter(bookById).findFirst().get();
		
		return book;
	}
	
	/* (non-Javadoc)
	 * @see pl.coderslab.apiback.model.BookService#edit(pl.coderslab.apiback.model.Book)
	 */
	@Override
	public void edit(Book book) throws NoSuchElementException {
		Book b = this.getById(book.getId());
		b = book;
	}
	
	/* (non-Javadoc)
	 * @see pl.coderslab.apiback.model.BookService#delete(pl.coderslab.apiback.model.Book)
	 */
	@Override
	public void delete(Book book) throws NoSuchElementException {
		deleteById(book.getId());
	}
	
	/* (non-Javadoc)
	 * @see pl.coderslab.apiback.model.BookService#deleteById(long)
	 */
	@Override
	public void deleteById(long id) throws NoSuchElementException {
		Book b = this.getById(id);
		this.list.remove(b);
	}

	/* (non-Javadoc)
	 * @see pl.coderslab.apiback.model.BookService#add(pl.coderslab.apiback.model.Book)
	 */
	@Override
	public Book add(Book book) {
		book.setId(nextId++);
		this.list.add(book);
		
		return book;
	}
}
