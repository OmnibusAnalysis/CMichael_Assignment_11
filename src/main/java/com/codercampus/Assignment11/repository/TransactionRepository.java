package com.codercampus.Assignment11.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.codercampus.Assignment11.domain.Transaction;

@Repository
public class TransactionRepository {
	private List<Transaction> transactions = new ArrayList<>(100);
	
	public TransactionRepository () {
		super();
		populateData();
	}
	
	public List<Transaction> findAll () {
		return transactions;
	}

	public void populateData() {
		try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/doNotTouch/transactions.doNotTouch");
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
			this.transactions = (List<Transaction>) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}

	public Transaction findById(Long transactionId) {
		Optional<Transaction> transaction = transactions.stream()
				.filter(trans -> trans.getId().equals(transactionId))
				.findFirst();

		return transaction.orElse(null);
	}
}
