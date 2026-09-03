package com.example.demo.schoolstructure;

import com.example.demo.schoolstructure.controller.ParentController;
import com.example.demo.schoolstructure.dto.parentdto.CreateParentRequest;

import com.example.demo.schoolstructure.model.Parent;

import com.example.demo.schoolstructure.model.Student;
import com.example.demo.auth.entity.User;
import com.example.demo.schoolstructure.service.ParentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.demo.auth.repository.UserRepository;
import com.example.demo.core.security.JwtAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

@WebMvcTest(ParentController.class)
@AutoConfigureMockMvc(addFilters = false)
class ParentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParentService parentService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    
    @Test
    void shouldCreateParent() throws Exception {

        
        CreateParentRequest request =
                new CreateParentRequest(
                        1L, 
                        2L  
                );

        User user = new User(); 
        user.setId(1L);

        Student student = new Student(); 
        student.setId(2L);

        Parent parent = new Parent();
        parent.setId(10L);
        parent.setUser(user);
        parent.setStudent(student);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        when(parentService.createParent(eq(2L), any(Parent.class)))
                .thenReturn(parent);

        mockMvc.perform(post("/api/parents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.studentId").value(2));
    }

   
    @Test
    void shouldGetParentByStudent() throws Exception {

        User user = new User();
        user.setId(1L);

        Student student = new Student();
        student.setId(2L);

        Parent parent = new Parent();
        parent.setId(10L);
        parent.setUser(user);
        parent.setStudent(student);

        
        when(parentService.getParentsByStudent(2L))
                .thenReturn(List.of(parent));

        mockMvc.perform(get("/api/parents/student/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(10))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].studentId").value(2));
    }
}
