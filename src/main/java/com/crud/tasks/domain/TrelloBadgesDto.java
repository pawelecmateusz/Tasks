package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class TrelloBadgesDto {
    @JsonProperty("votes")
    private int votes;
    @JsonProperty("attachments")
    private AttachmentByType attachments;
}
