package com.nexters.gaetteok.domain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public enum ReactionType {

    LIKE,
    CONGRATULATION,
    IMPRESSIVE,
    SAD,
    ANGRY,
    ;

    @Component
    public static class ReactionTypeConverter implements Converter<String, ReactionType> {
        @Override
        public ReactionType convert(String source) {
            return ReactionType.valueOf(source.toUpperCase());
        }
    }

}
