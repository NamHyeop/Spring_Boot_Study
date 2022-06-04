package connected.communication.service.member;

import connected.communication.dto.member.MemberDto;
import connected.communication.entity.member.Member;
import connected.communication.exception.MemberNotFoundException;
import connected.communication.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @InjectMocks MemberService memberService;
    @Mock MemberRepository memberRepository;

    @Test
    public void readTest() throws Exception{
        //given
        Member member = createMember();
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));
        //when
        MemberDto ret = memberService.read(1L);
        //then
        assertThat(ret.getEmail()).isEqualTo(member.getEmail());
    }
    @Test
    public void readExceptionByMemberNotFoundTest() throws Exception{
        //given
        given(memberRepository.findById(any())).willReturn(Optional.ofNullable(null));
        //when
        //then
        assertThatThrownBy(() -> memberService.read(1L)).isInstanceOf(MemberNotFoundException.class);
    }
    @Test
    public void deleteTest() throws Exception{
        //given
        given(memberRepository.existsById(anyLong())).willReturn(true);
        //when
        memberService.delete(1L);
        //then
        verify(memberRepository).deleteById(anyLong());
    }

    @Test
    public void deleteExceptionByMemberNotFoundTest() throws Exception{
        //given
        given(memberRepository.existsById(anyLong())).willReturn(false);
        //when
        //then
        assertThatThrownBy(() -> memberService.delete(1L)).isInstanceOf(MemberNotFoundException.class);
    }
    private Member createMember(){
        return new Member("email@email.com", "123456a!", "username", "nickname", List.of());
    }
}