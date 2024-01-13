package main.java.com.myjparepo.repo;

import java.util.Optional;

public interface JpaRepository<T, I> {

	public T save(T t);

	public Iterable<T> saveAll(Iterable<T> t);

	public Optional<T> findById(I id);

	public Iterable<T> findBy(String columnName, Object columnData);

	public Iterable<T> findAll();

	public void delete(T t);

	public void deleteAll();

}
