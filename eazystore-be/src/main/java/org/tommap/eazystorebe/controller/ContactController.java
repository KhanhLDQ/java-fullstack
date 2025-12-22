package org.tommap.eazystorebe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommap.eazystorebe.model.request.ContactRequest;
import org.tommap.eazystorebe.service.IContactService;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final IContactService contactService;

    @PostMapping
    public String saveContact(@RequestBody ContactRequest request) {
        boolean isSaved = contactService.save(request);

        return isSaved
                ? "Request processed successfully"
                : "An error occurred! Please try again or contact engineer team!!";
    }
}
