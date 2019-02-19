package io.namjune.community.rest.event;

import io.namjune.community.rest.domain.Board;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler(Board.class)
public class BoardEventHandler {

    @HandleBeforeCreate
    public void beforeCreateBoard(Board board) {
        board.setCreateDateNow();
    }

    @HandleBeforeSave
    public void beforeSaveBoard(Board board) {
        board.setUpdateDateNow();
    }
}
