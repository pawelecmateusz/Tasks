package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private AdminConfig adminConfig;

    @Test
    void shouldGetTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("List1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = List.of(
                new TrelloBoardDto("Board1", "test_board", trelloLists),
                new TrelloBoardDto());

        //When
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);

        //Then
        assertEquals(trelloBoards, trelloService.fetchTrelloBoards());
    }

    @Test
    void shouldCreateTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "test", "1");
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        // When
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(newCard);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");

        // Then
        assertEquals(newCard, trelloClient.createNewCard(trelloCardDto));
        assertEquals(newCard, trelloService.createTrelloCard(trelloCardDto));
        assertEquals("name", trelloCardDto.getName());
        assertEquals("test@test.com", adminConfig.getAdminMail());
    }
}
