package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(1L, task.getId());
        assertEquals("title1", task.getTitle());
        assertEquals("content1", task.getContent());
    }

    @Test
    void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "title1", "content1");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(1L, taskDto.getId());
        assertEquals("title1", taskDto.getTitle());
        assertEquals("content1", taskDto.getContent());
    }

    @Test
    void testMapToTaskDtoList() {
        //Given
        List<Task> tasks = List.of(new Task(1L, "title1", "content1"), new Task(2L, "title2", "content2"));

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(2, taskDtoList.size());
        assertEquals(1L, taskDtoList.get(0).getId());
        assertEquals("title1", taskDtoList.get(0).getTitle());
        assertEquals("content1", taskDtoList.get(0).getContent());
        assertEquals(2L, taskDtoList.get(1).getId());
        assertEquals("title2", taskDtoList.get(1).getTitle());
        assertEquals("content2", taskDtoList.get(1).getContent());
    }
}