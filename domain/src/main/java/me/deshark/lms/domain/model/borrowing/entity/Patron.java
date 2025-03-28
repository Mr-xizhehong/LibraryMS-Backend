package me.deshark.lms.domain.model.borrowing.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author DE_SHARK
 * @date 2025/2/15 22:51
 */
@Data
@Builder
public class Patron {
    // 账号 id
    private final UUID id;
    // 目前已借阅数量
    private int currentBorrows;
    
    private static final int MAX_BORROWS = 5;

    public void canBorrow() {
        if (currentBorrows >= MAX_BORROWS) {
            throw new IllegalArgumentException("Max borrows reached");
        }
    }
}
