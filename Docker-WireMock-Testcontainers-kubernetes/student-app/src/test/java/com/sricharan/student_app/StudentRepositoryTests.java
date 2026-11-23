package com.sricharan.student_app;


import com.sricharan.student_app.dto.LeetCodeProgressDataDTO;
import com.sricharan.student_app.dto.LeetCodeProgressResponseDTO;
import com.sricharan.student_app.dto.UserQuestionProgressDTO;
import com.sricharan.student_app.repository.StudentRepository;
import com.sricharan.student_app.service.LeetCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class StudentRepositoryTests {

    @Container
    @ServiceConnection
    static MySQLContainer mysql = new MySQLContainer(DockerImageName.parse("mysql:8.0"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @MockitoBean
    private LeetCodeService leetCodeService;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    void shouldCreateFetchAndSoftDeleteStudent() throws Exception {
        String studentJson = """
                {
                             "name": "Sricharan",
                             "age": 21,
                             "leetCodeUserName": "sricharan_dev",
                             "active": true
                }
                """;

        // 1. POST /students -> 201 Created
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Sricharan"))
                .andExpect(jsonPath("$.age").value(21));

        int savedId = studentRepository.findByActiveTrue().get(0).getId();

        // 2. GET /students -> 200 OK with 1 active student
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        // 3. DELETE /students/{id} -> 204 No Content (soft delete)
        mockMvc.perform(delete("/students/{id}", savedId))
                .andExpect(status().isNoContent());

        // 4. GET /students -> 200 OK with 0 active students
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void shouldReturnLeetCodeProfileForActiveStudent() throws Exception {
        String studentJson = """
                {
                    "name": "Sricharan",
                    "leetCodeUserName": "sricharan_dev",
                    "age": 21
                }
                """;

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isCreated());

        int savedId = studentRepository.findByActiveTrue().get(0).getId();

        LeetCodeProgressResponseDTO mockResponse = new LeetCodeProgressResponseDTO(new LeetCodeProgressDataDTO(new UserQuestionProgressDTO(new ArrayList<>(),new ArrayList<>(), new ArrayList<>(),new ArrayList<>(), 0.0)));
        when(leetCodeService.getProgress("sricharan_dev")).thenReturn(mockResponse);

        // GET /students/{id}/lcprofiles -> 200 OK
        mockMvc.perform(get("/students/{id}/lcprofiles", savedId))
                .andExpect(status().isOk());
    }
}