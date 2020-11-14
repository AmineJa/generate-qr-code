package com.generate.qr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generate.qr.interfaces.CodeService;
import com.generate.qr.model.User;

@RestController
@RequestMapping("/api")
public class CodeController {

	@Autowired
	CodeService codeService;

	@GetMapping("/")
	public ResponseEntity<String> generateCode() throws Exception {

		User user = new User("user", "user", "user@user.fr");

		String path = codeService.generateCode(user.toString());
		return ResponseEntity.ok(path);

	}

	@GetMapping(value = "/get-code-data")
	public ResponseEntity<String> getDataCode(@RequestParam String path) throws Exception {

		String result = codeService.getDataCode(path);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);

	}

}
