package com.shivam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shivam.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	public Book findById(int id);
}
