package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreatedTrelloCardTest {
    @Test
    void testCreateCartDtoNoArgs() {
        //Given
        Trello trello = new Trello();

        AttachmentByType attachmentByType = new AttachmentByType();
        attachmentByType.setTrello(trello);

        TrelloBadgesDto trelloBadgesDto = new TrelloBadgesDto();
        trelloBadgesDto.setAttachmentsByType(attachmentByType);

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();
        createdTrelloCardDto.setBadges(trelloBadgesDto);

        //Then
        assertEquals(trelloBadgesDto, createdTrelloCardDto.getBadges());
        assertEquals(attachmentByType, createdTrelloCardDto.getBadges().getAttachmentsByType());
        assertEquals(trello, createdTrelloCardDto.getBadges().getAttachmentsByType().getTrello());
    }

    @Test
    void testCreateCartDtoAllArgs() {
        //Given
        Trello trello = new Trello(1, 1);

        AttachmentByType attachmentByType = new AttachmentByType(trello);

        TrelloBadgesDto trelloBadgesDto = new TrelloBadgesDto(1, attachmentByType);

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "Test", "testUrl", trelloBadgesDto);

        //Then
        assertEquals(1, createdTrelloCardDto.getBadges().getAttachmentsByType().getTrello().getCard());
        assertEquals(1, createdTrelloCardDto.getBadges().getAttachmentsByType().getTrello().getBoard());
        assertEquals(trello, createdTrelloCardDto.getBadges().getAttachmentsByType().getTrello());
        assertEquals(1, createdTrelloCardDto.getBadges().getVotes());
        assertEquals(attachmentByType, createdTrelloCardDto.getBadges().getAttachmentsByType());
        assertEquals("1", createdTrelloCardDto.getId());
        assertEquals("Test", createdTrelloCardDto.getName());
        assertEquals("testUrl", createdTrelloCardDto.getShortUrl());
        assertEquals(trelloBadgesDto, createdTrelloCardDto.getBadges());
    }
}
