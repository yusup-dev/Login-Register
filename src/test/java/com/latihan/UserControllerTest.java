package com.latihan;

import com.latihan.controller.UserController;
import com.latihan.entity.User;
import com.latihan.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        session = new MockHttpSession();
    }

    @Test
    public void testUpdateSuccess() {
        User user = new User(); // Create a user object

        // Mocking userService.save() method to return a user object
        when(userService.save(user)).thenReturn(user);

        // Call the update() method
        String result = userController.update(user, session);

        // Verify that userService.save() was called once
        verify(userService, times(1)).save(user);

        // Verify that the success message was set in the session
        assertEquals("Update Successfully", session.getAttribute("msg"));

        // Verify that the method returns the correct redirect URL
        assertEquals("redirect:/", result);
    }

    @Test
    public void testUpdateFailure() {
        User user = new User(); // Create a user object

        // Mocking userService.save() method to return null
        when(userService.save(user)).thenReturn(null);

        // Call the update() method
        String result = userController.update(user, session);

        // Verify that userService.save() was called once
        verify(userService, times(1)).save(user);

        // Verify that the error message was set in the session
        assertEquals("something wrong on server", session.getAttribute("msg"));

        // Verify that the method returns the correct redirect URL
        assertEquals("redirect:/", result);
    }
}
