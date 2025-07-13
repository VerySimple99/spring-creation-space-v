package org.kosta.myproject.model;

import org.springframework.stereotype.Service;

@Service
public class PersonService {
	public void register() {
		System.out.println("register person");
	}
	public String getNick() {
		return "아이유";
	}
}
