package io.namjune.community.rest;

import static org.junit.Assert.assertNotNull;

import io.namjune.community.rest.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataRestApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureTestDatabase
public class BoardEventTest {

    private TestRestTemplate testRestTemplate = new TestRestTemplate("nj", "1234");

    @Test
    public void 저장시_이벤트가_적용되어_날짜가_생성되는가() {
        Board createdBoard = createBoard();
        assertNotNull(createdBoard.getCreatedDate());
    }

    @Test
    public void 수정시_이벤트가_적용되어_날짜가_생선되는가() {
        Board createdBoard = createBoard();
        Board updatedBoard = updateBoard();
        assertNotNull(updatedBoard.getUpdatedDate());
    }

    private Board createBoard() {
        Board board = Board.builder().title("저장 이벤트 테스트").build();
        return testRestTemplate
            .postForObject("http://127.0.0.1:8081/api/boards", board, Board.class);
    }

    private Board updateBoard() {
        String updateUri = "http://127.0.0.1:8081/api/boards/1";
        testRestTemplate.put(updateUri, createBoard());
        return testRestTemplate.getForObject(updateUri, Board.class);
    }
}
