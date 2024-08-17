package com.nexters.gaetteok.user.constant;

import com.nexters.gaetteok.domain.FriendWalkStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

public enum SortCondition {

    FRIEND_DESC,
    WALK_DONE_DESC,
    ;

    public Function<FriendWalkStatus, LocalDateTime> getComparator() {
        if (this == FRIEND_DESC) {
            return FriendWalkStatus::getFriendCreatedAt;
        }
        if (this == WALK_DONE_DESC) {
            return FriendWalkStatus::getLastWalkDate;
        }
        throw new IllegalArgumentException("지원하지 않는 정렬 조건입니다. condition: " + this);
    }

    @Component
    public static class SortConditionConverter implements Converter<String, SortCondition> {
        @Override
        public SortCondition convert(String source) {
            return SortCondition.valueOf(source.toUpperCase());
        }
    }

}
