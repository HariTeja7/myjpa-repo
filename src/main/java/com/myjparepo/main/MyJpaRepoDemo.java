package main.java.com.myjparepo.main;

import java.util.List;

import main.java.com.myjparepo.entity.Product;
import main.java.com.myjparepo.repo.JpaRepository;
import main.java.com.myjparepo.repo.impl.JpaRepositoryImpl;

public class MyJpaRepoDemo {

	public static void main(String[] args) {
		JpaRepository<Product, Integer> productRepository = new JpaRepositoryImpl<>(Product.class, Integer.class);

		System.out.println("Saved first entity : " + productRepository.save(new Product("SAMSUNG S20", 40654.76)));

		productRepository.saveAll(List.of(new Product("NOKIA G42 5G", 12599), new Product("Redmi Note 8 5G", 15999)));

		System.out.println("All saved entities : " + productRepository.findAll());

		productRepository.deleteAll();

		System.out.println("All entities in db after deleting : " + productRepository.findAll());

	}

}
