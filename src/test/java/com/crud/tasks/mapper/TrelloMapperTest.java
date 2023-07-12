package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloMapperTest {

    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void shouldMapToBoards() {
        //Given
        TrelloBoardDto boardDto1 = new TrelloBoardDto("boardId1", "Board 1", List.of(
                new TrelloListDto("listId1", "List 1", false)
        ));
        TrelloBoardDto boardDto2 = new TrelloBoardDto("boardId2", "Board 2", List.of(
                new TrelloListDto("listId2", "List 2", true)
        ));
        List<TrelloBoardDto> boardDtos = Arrays.asList(boardDto1, boardDto2);

        //When
        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardDtos);

        //Then
        assertEquals(1, boards.get(0).getLists().size());
        assertEquals("boardId1", boards.get(0).getId());
        assertEquals("listId1", boards.get(0).getLists().get(0).getId());
        assertEquals(1, boards.get(1).getLists().size());
        assertEquals("boardId2", boards.get(1).getId());
        assertEquals("listId2", boards.get(1).getLists().get(0).getId());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        TrelloBoard board = new TrelloBoard("boardId", "Board 1", List.of(
                new TrelloList("listId", "List 1", false)
        ));
        List<TrelloBoard> boards = List.of(board);

        //When
        List<TrelloBoardDto> boardDtos = trelloMapper.mapToBoardsDto(boards);

        //Then
        assertEquals(1, boardDtos.size());
        assertEquals("boardId", boardDtos.get(0).getId());
        assertEquals("listId", boardDtos.get(0).getLists().get(0).getId());
    }

    @Test
    public void shouldMapToList() {
        //Given
        TrelloListDto listDto1 = new TrelloListDto("listId1", "List 1", false);
        TrelloListDto listDto2 = new TrelloListDto("listId2", "List 2", true);
        List<TrelloListDto> listDtos = Arrays.asList(listDto1, listDto2);

        //When
        List<TrelloList> lists = trelloMapper.mapToList(listDtos);

        //Then
        assertEquals(2, lists.size());
        assertEquals("listId1", lists.get(0).getId());
        assertEquals("List 1", lists.get(0).getName());
        assertEquals("listId2", lists.get(1).getId());
        assertEquals("List 2", lists.get(1).getName());

    }
    @Test
    public void shouldMapToListDto() {
        //Given
        TrelloList list = new TrelloList("listId", "List 1", false);
        List<TrelloList> lists = List.of(list);

        //When
        List<TrelloListDto> listDtos = trelloMapper.mapToListDto(lists);

        //Then
        assertEquals(1, listDtos.size());
        assertEquals("listId", listDtos.get(0).getId());
        assertEquals("List 1", listDtos.get(0).getName());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("Card", "desc", "1", "1");

        //When
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);

        //Then
        assertEquals("Card", cardDto.getName());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("Card", "desc", "1", "1");

        //When
        TrelloCard card = trelloMapper.mapToCard(cardDto);

        //Then
        assertEquals("Card", card.getName());
        assertEquals("desc", card.getDescription());
        assertEquals("1", card.getPos());
        assertEquals("1", card.getListId());
    }

}
