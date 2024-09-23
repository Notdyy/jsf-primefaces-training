package com.example.test;

import javax.persistence.EntityManager;

import my.example.jpa.EmfProducer;

public class EmTest {
	public static void main(String[] args) {
		EntityManager em = EmfProducer.createLocalEntityManager();
		em.close();
	}
}