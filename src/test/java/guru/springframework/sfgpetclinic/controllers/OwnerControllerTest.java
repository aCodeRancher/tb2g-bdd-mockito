package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {


    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    @Mock
    BindingResult bindingResult;

    @Mock
    Owner owner ;


    @BeforeEach
   void setUp(){
       owner = new Owner(5L, "John", "Doe");
   }

    @Test
    void processCreationFormWithError() {

        given(bindingResult.hasErrors()).willReturn(true);

        String errorMessage = ownerController.processCreationForm(owner, bindingResult);
        assertThat(errorMessage.equals("owners/createOrUpdateOwnerForm"));

    }

    @Test
    void processCreationFormWithoutError(){

        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(owner)).willReturn(owner);
        String output = ownerController.processCreationForm(owner, bindingResult);
        assertTrue(output.equalsIgnoreCase("redirect:/owners/5"));
        assertThat(output).isEqualTo("redirect:/owners/5");
    }
}