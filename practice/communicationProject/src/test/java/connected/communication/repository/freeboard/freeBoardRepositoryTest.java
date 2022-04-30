package connected.communication.repository.freeboard;

import connected.communication.repository.Text;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


/**
 * id : 글 번호
 * textName : 글 제목
 * textContent : 글 내용
 * recommendCnt : 글 추천 수
 * commnentCnt : 댓글 수
 * bookMarkCnt : 즐겨찾기 수
 */

class freeBoardRepositoryTest {
    FreeBoardRepository freeBoardRepository = new FreeBoardRepository();

    @AfterEach
    void afterReset(){
        freeBoardRepository.clearStore();
    }

    //자유게시판 글 저장 테스트
    @Test
    public void save() throws Exception{
        //given
        Text text = new Text("테스트 글입니다", "테스트 내용입니다", 1,2,3);
        //when
        Text saveText = freeBoardRepository.save(text);
        //then
        freeBoardRepository.findByTextId(text.getId());
        assertThat(text).isEqualTo(saveText);
    }

    //자유게시판 글 조회테스트
    @Test
    public void findAll() throws Exception{
        //given
        Text text1 = new Text("테스트 글1 입니다", "테스트 내용입니다", 1,2,3);
        Text text2 = new Text("테스트 글2 입니다", "테스트 내용입니다", 1,2,3);
        //when
        Text save1Text = freeBoardRepository.save(text1);
        Text save2Text = freeBoardRepository.save(text2);
        //then
        List<Text> allText = freeBoardRepository.findAll();
        assertThat(allText.size()).isEqualTo(2);
        assertThat(allText).contains(save1Text, save2Text);
    }

    //자유게시판 글 변경 테스트
    @Test
    public void updateText() throws Exception{
        //given
        Text text = new Text("테스트 글1 입니다", "테스트 내용입니다", 1,2,3);
        Text saveText = freeBoardRepository.save(text);
        //when
        Text updateText = new Text("테스트 글1의 업데이트 제목 입니다", "테스트 글1의 업데이트 내용 입니다", 1,2,3);
        freeBoardRepository.update(saveText.getId(), updateText);
        Text findText = freeBoardRepository.findByTextId(saveText.getId());
        //then
        assertThat(updateText.getTextName()).isEqualTo(findText.getTextName());
        assertThat(updateText.getTextContent()).isEqualTo(findText.getTextContent());
    }
}