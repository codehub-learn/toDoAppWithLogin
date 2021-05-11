package gr.codehub.toDoAppWithLogin.service;

import gr.codehub.toDoAppWithLogin.ToDoAppWithLoginApplication;
import gr.codehub.toDoAppWithLogin.model.Item;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Add item")
    void insertItem() {
        Item itemToAdd = Item.builder().description("buy milk and eggs").date_created(new Date()).build();
        Item item = itemService.addItem("buy milk and eggs");
        assertEquals(itemToAdd.getDescription(), item.getDescription());
    }

    @Test
    @DisplayName("Http Authentication")
    public void givenStatus_whenAccessAppHealth_thenStatus200()
            throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("admin", "admin");
        mvc.perform(get("/actuator/health")
                .contentType(MediaType.APPLICATION_JSON).headers(httpHeaders))
                .andExpect(status().isOk());
    }
}
