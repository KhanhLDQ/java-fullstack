package org.tommap.eazystorebe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommap.eazystorebe.model.request.ContactRequest;
import org.tommap.eazystorebe.service.IContactService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final IContactService contactService;

    @PostMapping
    public ResponseEntity<String> saveContact(@Valid @RequestBody ContactRequest request) { //allow to customize http status|headers|body
        contactService.save(request);

        return ResponseEntity.status(CREATED).body("Request processed successfully");
    }
}
