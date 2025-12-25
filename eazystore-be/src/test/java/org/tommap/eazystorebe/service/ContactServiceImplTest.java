package org.tommap.eazystorebe.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tommap.eazystorebe.mapper.ContactMapper;
import org.tommap.eazystorebe.model.entity.Contact;
import org.tommap.eazystorebe.model.request.ContactRequest;
import org.tommap.eazystorebe.repository.ContactRepository;
import org.tommap.eazystorebe.service.impl.ContactServiceImpl;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {
    @Mock
    ContactRepository contactRepository;

    @Mock
    ContactMapper contactMapper;

    @InjectMocks
    ContactServiceImpl contactService; //require implementation class instead of interface

    @Test
    void testSave_WhenThrowsException_ShouldReturnFalse() {
        //arrange
        var request = ContactRequest.builder()
                .name("Khanh Le")
                .email("khanh.le@example.com")
                .mobileNumber("+1234567890")
                .message("This is a test message for the contact form!")
                .build();

        var contact = new Contact();
        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setMobileNumber(request.getMobileNumber());
        contact.setMessage(request.getMessage());
        contact.setCreatedAt(LocalDateTime.now());
        contact.setCreatedBy(request.getName());

        when(contactMapper.toContact(request)).thenReturn(contact);
        when(contactRepository.save(contact)).thenThrow(new RuntimeException("Database Connection Error!"));

        //act
        boolean result = contactService.save(request);

        //assert
        assertThat(result).isFalse();
    }
}
