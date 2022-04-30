package connected.communication.repository;

import lombok.Data;

/**
 * id : 글 번호
 * textName : 글 제목
 * textContent : 글 내용
 * recommendCnt : 글 추천 수
 * commnentCnt : 댓글 수
 * bookMarkCnt : 즐겨찾기 수
 */

@Data
public class Text {
    private Long id;
    private String textName;
    private String textContent;
    private Integer recommendCnt;
    private Integer commentCnt;
    private Integer bookMarkCnt;

    public Text() {
    }

    public Text(String textName, String textContent, Integer recommendCnt, Integer commentCnt, Integer bookMarkCnt) {
        this.textName = textName;
        this.textContent = textContent;
        this.recommendCnt = recommendCnt;
        this.commentCnt = commentCnt;
        this.bookMarkCnt = bookMarkCnt;
    }
}

