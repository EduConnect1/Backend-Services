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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParentController.class)
@AutoConfigureMockMvc(addFilters = false)
class ParentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParentService parentService;

    @Autowired
    private ObjectMapper objectMapper;

    // ============================
    // POST /api/parents
    // ============================
    @Test
    void shouldCreateParent() throws Exception {

        // ✅ record → constructor
        CreateParentRequest request =
                new CreateParentRequest(
                        1L, // userId
                        2L  // studentId
                );

        // Mock the Parent entity returned by the service
        User user = new User(); // fill necessary fields if required
        user.setId(1L);

        Student student = new Student(); // fill necessary fields if required
        student.setId(2L);

        Parent parent = new Parent();
        parent.setId(10L);
        parent.setUser(user);
        parent.setStudent(student);

        // ✅ FIX: match service method signature (Long studentId, Parent parent)
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

    // ============================
    // GET /api/parents/student/{studentId}
    // ============================
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

        // ✅ service returns entities
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
