
package com.example.demo.schoolstructure;

import com.example.demo.auth.entity.User;
import com.example.demo.schoolstructure.dto.teacherdto.AssignSubjectRequest;
import com.example.demo.schoolstructure.dto.teacherdto.CreateTeacherRequest;
import com.example.demo.schoolstructure.controller.TeacherController;
import com.example.demo.schoolstructure.model.Teacher;
import com.example.demo.schoolstructure.service.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeacherController.class)
@AutoConfigureMockMvc(addFilters = false)
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private ObjectMapper objectMapper;

    
    @Test
    void shouldCreateTeacher() throws Exception {

        
        CreateTeacherRequest request = new CreateTeacherRequest(
                1L,            
                "EMP001"       
        );

        
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        Teacher teacher = Teacher.builder()
                .id(10L)
                .user(user)
                .employeeNumber("EMP001")
                .build();

        
        when(teacherService.createTeacher(any(Teacher.class)))
                .thenReturn(teacher);

        mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.employeeNumber").value("EMP001"));
    }

    @Test
    void shouldAssignSubjectsToTeacher() throws Exception {

        AssignSubjectRequest request = new AssignSubjectRequest(Set.of(1L, 2L));

        
        doNothing().when(teacherService).assignSubjects(eq(10L), any());

        
        mockMvc.perform(post("/api/teachers/10/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
