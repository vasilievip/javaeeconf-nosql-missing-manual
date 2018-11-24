package com.github.nosql;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserScoreDocument {
    private final int balance;
    private final List<String> achievements;
}
