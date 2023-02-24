package com.dalbot.ai.cmcopilot.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdentifiableEntity<T> {

    private T id;

    private Date createdAt;
    private Date upadtedAt;

}
