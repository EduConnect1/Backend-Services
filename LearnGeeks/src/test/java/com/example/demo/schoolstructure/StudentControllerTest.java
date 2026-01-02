package com.example.demo.schoolstructure;

import com.example.demo.schoolstructure.controller.StudentController;
import com.example.demo.schoolstructure.dto.studentdto.CreateStudentRequest;
import com.example.demo.schoolstructure.model.Student;
import com.example.demo.schoolstructure.model.SchoolClass;
import com.example.demo.schoolstructure.service.StudentService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    
    @Test
    void shouldCreateStudent() throws Exception {

        CreateStudentRequest request =
                new CreateStudentRequest(
                        1L,
                        "ADM001",
                        2L
                );

        Student student = Student.builder()
                .id(10L)
                .admissionNumber("ADM001")
                .build();

        when(studentService.createStudent(any(Long.class), any(Student.class)))
            .thenReturn(student);

    mockMvc.perform(post("/api/students")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(10))
            .andExpect(jsonPath("$.admissionNumber").value("ADM001"));
}

    
    @Test
    void shouldGetStudentsByClass() throws Exception {

        SchoolClass schoolClass = SchoolClass.builder()
                .id(2L)
                .name("Senior 1")
                .build();

        Student student = Student.builder()
                .id(10L)
                .admissionNumber("ADM001")
                .schoolClass(schoolClass)
                .build();

        when(studentService.getStudentsByClass(2L))
                .thenReturn(List.of(student));

        mockMvc.perform(get("/api/students/class/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(10))
                .andExpect(jsonPath("$[0].className").value("Senior 1"));
    }
}
