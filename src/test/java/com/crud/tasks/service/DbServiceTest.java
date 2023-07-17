package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository taskRepository;

    @Test
    void shouldGetAllTasks() {
        //Given
        List<Task> tasks = List.of(
                new Task(1L, "title1", "content1"),
                new Task(2L, "title2", "content2"));

        //When
        taskRepository.saveAll(tasks);
        when(dbService.getAllTasks()).thenReturn(tasks);

        //Then
        assertEquals(2, dbService.getAllTasks().size());
    }

    @Test
    void shouldGetTaskById() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "title1", "content1");

        //When
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Task resultTask = dbService.getTask(1L);

        //Then
        assertEquals(task.getId(), resultTask.getId());
    }

    @Test
    void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "title1", "content1");

        //When
        when(taskRepository.save(task)).thenReturn(task);
        Task resultTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), resultTask.getId());
    }

    @Test
    void shouldDeleteTask() {
        //Given
        Task task = new Task(1L, "title1", "content1");

        //When
        dbService.deleteTask(task.getId());

        //Then
        assertTrue(dbService.getAllTasks().isEmpty());
    }

}
