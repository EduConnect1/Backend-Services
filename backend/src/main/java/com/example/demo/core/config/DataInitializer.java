package com.example.demo.core.config;

import com.example.demo.auth.entity.Role;
import com.example.demo.auth.entity.User;
import com.example.demo.auth.entity.UserStatus;
import com.example.demo.auth.enums.RoleEnum;
import com.example.demo.auth.repository.RoleRepository;
import com.example.demo.auth.repository.UserRepository;
import com.example.demo.schoolstructure.model.Parent;
import com.example.demo.schoolstructure.model.SchoolClass;
import com.example.demo.schoolstructure.model.Student;
import com.example.demo.schoolstructure.model.Teacher;
import com.example.demo.schoolstructure.repository.ParentRepository;
import com.example.demo.schoolstructure.repository.SchoolClassRepository;
import com.example.demo.schoolstructure.repository.StudentRepository;
import com.example.demo.schoolstructure.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ParentRepository parentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting Database User Seeding...");

        // 1. Initialize Roles
        Role adminRole = getOrCreateRole(RoleEnum.ROLE_ADMIN);
        Role teacherRole = getOrCreateRole(RoleEnum.ROLE_TEACHER);
        Role studentRole = getOrCreateRole(RoleEnum.ROLE_STUDENT);
        Role parentRole = getOrCreateRole(RoleEnum.ROLE_PARENT);

        // 2. Initialize Class
        SchoolClass defaultClass = schoolClassRepository.findAll().stream().findFirst()
                .orElseGet(() -> schoolClassRepository.save(SchoolClass.builder()
                        .name("Class 10-A")
                        .academicYear("2026")
                        .build()));

        // 3. Seed Administrator
        User admin = createUserIfNotFound("admin@educonnect.com", "AdminPass123!", "System", "Admin", "+1000000001", adminRole);

        // 4. Seed Teacher & Profile
        User teacherUser = createUserIfNotFound("teacher@educonnect.com", "TeacherPass123!", "Sarah", "Jenkins", "+1000000002", teacherRole);
        if (teacherRepository.findByUserId(teacherUser.getId()).isEmpty()) {
            teacherRepository.save(Teacher.builder().user(teacherUser).employeeNumber("EMP-1001").build());
        }

        // 5. Seed Student & Profile
        User studentUser = createUserIfNotFound("student@educonnect.com", "StudentPass123!", "Alice", "Smith", "+1000000003", studentRole);
        Student student = studentRepository.findByUserId(studentUser.getId()).orElse(null);
        if (student == null) {
            student = studentRepository.save(Student.builder()
                    .user(studentUser)
                    .schoolClass(defaultClass)
                    .admissionNumber("ST-1001")
                    .build());
        }

        // 6. Seed Parent & Profile
        User parentUser = createUserIfNotFound("parent@educonnect.com", "ParentPass123!", "Robert", "Smith", "+1000000004", parentRole);
        if (parentRepository.findByUserId(parentUser.getId()).isEmpty() && student != null) {
            parentRepository.save(Parent.builder().user(parentUser).student(student).build());
        }

        log.info("Database User Seeding Completed Successfully.");
    }

    private Role getOrCreateRole(RoleEnum roleEnum) {
        return roleRepository.findByRole(roleEnum)
                .orElseGet(() -> roleRepository.save(Role.builder().role(roleEnum).build()));
    }

    private User createUserIfNotFound(String email, String rawPassword, String firstName, String lastName, String phone, Role role) {
        return userRepository.findByEmail(email)
                .orElseGet(() -> {
                    Set<Role> roles = new HashSet<>();
                    roles.add(role);
                    return userRepository.save(User.builder()
                            .email(email)
                            .password(passwordEncoder.encode(rawPassword))
                            .firstName(firstName)
                            .lastName(lastName)
                            .phoneNumber(phone)
                            .verified(true)
                            .status(UserStatus.ACTIVE)
                            .roles(roles)
                            .build());
                });
    }
}
