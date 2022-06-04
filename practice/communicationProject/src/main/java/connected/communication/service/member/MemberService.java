package connected.communication.service.member;

import connected.communication.dto.member.MemberDto;
import connected.communication.entity.member.Member;
import connected.communication.exception.MemberNotFoundException;
import connected.communication.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberDto read(Long id){
        return MemberDto.toDto(memberRepository.findById(id).orElseThrow(MemberNotFoundException::new));
    }

    @Transactional
    public void delete(Long id){
        if(notExistsMember(id)) throw new MemberNotFoundException();
        memberRepository.deleteById(id);
    }
    private boolean notExistsMember(Long id) {
        return !memberRepository.existsById(id);
    }

}
