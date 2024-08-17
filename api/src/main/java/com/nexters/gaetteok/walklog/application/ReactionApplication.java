package com.nexters.gaetteok.walklog.application;

import com.nexters.gaetteok.domain.Reaction;
import com.nexters.gaetteok.persistence.entity.ReactionEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.repository.ReactionRepository;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import com.nexters.gaetteok.walklog.mapper.ReactionMapper;
import com.nexters.gaetteok.walklog.presentation.request.CreateReactionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReactionApplication {

    private final ReactionRepository reactionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Reaction create(long userId, long walkLogId, CreateReactionRequest request) {
        ReactionEntity reactionEntity = reactionRepository.save(ReactionEntity.builder()
            .userId(userId)
            .walkLogId(walkLogId)
            .reactionType(request.getReactionType())
            .build());
        UserEntity userEntity = userRepository.getById(userId);
        return ReactionMapper.toDomain(reactionEntity, userEntity);
    }

    @Transactional
    public void delete(long userId, long reactionId) {
        ReactionEntity reactionEntity = reactionRepository.getById(reactionId);
        if (reactionEntity.getUserId() != userId) {
            log.warn(
                "자신이 작성한 리액션만 삭제할 수 있습니다. 제거 요청자: {}, 리액션 작성자: {}", userId,
                reactionEntity.getUserId()
            );
            throw new IllegalArgumentException("자신이 작성한 리액션만 삭제할 수 있습니다.");
        }
        reactionRepository.deleteById(reactionId);
    }

}
