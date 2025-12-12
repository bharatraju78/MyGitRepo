package com.vam.cco.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import com.vam.cco.model.TeaEstimateModel;
import com.vam.cco.services.impl.TeaEstimateServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

@WebMvcTest(TeaEstimateController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TeaEstimateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeaEstimateServiceImpl teaEstimateService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testList() throws Exception {
        TeaEstimateModel model = new TeaEstimateModel();
        model.setId(1L);
        model.setEngagementName("Test");
        Pageable pageable = PageRequest.of(0, 10);
        when(teaEstimateService.findAllTeaEstimates(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(model), pageable, 1));

        mockMvc.perform(get("/admin/tea-estimate/list").param("page", "1").param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/teaEstimateList"))
                .andExpect(model().attributeExists("teaEstimateModels"));

        verify(teaEstimateService, times(1)).findAllTeaEstimates(pageable);
    }
}