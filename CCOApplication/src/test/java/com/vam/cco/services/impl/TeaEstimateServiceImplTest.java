package com.vam.cco.services.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.vam.cco.dao.entity.TeaEstimate;
import com.vam.cco.dao.repository.TeaEstimateRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class TeaEstimateServiceImplTest {

    @Mock
    private TeaEstimateRepository teaEstimateRepository;

    @InjectMocks
    private TeaEstimateServiceImpl teaEstimateService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAndFind() {
        TeaEstimate t = new TeaEstimate();
        t.setId(1L);
        t.setEngagementName("Test Engagement");

        when(teaEstimateRepository.save(any(TeaEstimate.class))).thenReturn(t);
        when(teaEstimateRepository.findById(1L)).thenReturn(Optional.of(t));

        TeaEstimate saved = teaEstimateService.save(t);
        assertNotNull(saved);
        assertEquals(1L, saved.getId().longValue());

        Optional<TeaEstimate> fetched = teaEstimateService.findById(1L);
        assertTrue(fetched.isPresent());
        assertEquals("Test Engagement", fetched.get().getEngagementName());

        verify(teaEstimateRepository, times(1)).save(any(TeaEstimate.class));
        verify(teaEstimateRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAllPageable() {
        TeaEstimate t1 = new TeaEstimate();
        t1.setId(1L);
        TeaEstimate t2 = new TeaEstimate();
        t2.setId(2L);
        List<TeaEstimate> list = Arrays.asList(t1, t2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<TeaEstimate> page = new PageImpl<>(list, pageable, list.size());

        when(teaEstimateRepository.findAll(pageable)).thenReturn(page);

        Page<TeaEstimate> result = teaEstimateService.findAll(pageable);
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());

        verify(teaEstimateRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testUpdateAndDelete() {
        TeaEstimate t = new TeaEstimate();
        t.setId(3L);
        t.setEngagementName("To Update");

        when(teaEstimateRepository.save(any(TeaEstimate.class))).thenReturn(t);

        TeaEstimate updated = teaEstimateService.update(t);
        assertNotNull(updated);
        assertEquals(3L, updated.getId().longValue());

        // delete
        doNothing().when(teaEstimateRepository).deleteById(3L);
        teaEstimateService.deleteById(3L);
        verify(teaEstimateRepository, times(1)).deleteById(3L);
    }
}