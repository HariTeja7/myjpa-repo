package main.java.com.myjparepo.repo;

import java.util.Optional;

/**
 * The Interface JpaRepository.
 *
 * @param <T> the generic type
 * @param <I> the generic type
 */
public interface JpaRepository<T, I> {

	/**
	 * Save.
	 *
	 * @param t the t
	 * @return the t
	 */
	public T save(T t);

	/**
	 * Save all.
	 *
	 * @param t the t
	 * @return the iterable
	 */
	public Iterable<T> saveAll(Iterable<T> t);

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<T> findById(I id);

	/**
	 * Find by.
	 *
	 * @param columnName the column name
	 * @param columnData the column data
	 * @return the iterable
	 */
	public Iterable<T> findBy(String columnName, Object columnData);

	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public Iterable<T> findAll();

	/**
	 * Delete.
	 *
	 * @param t the t
	 */
	public void delete(T t);

	/**
	 * Delete all.
	 */
	public void deleteAll();

}
