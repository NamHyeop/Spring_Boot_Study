package connected.communication.repository.member;

import connected.communication.entity.member.Member;
import connected.communication.entity.member.MemberRole;
import connected.communication.entity.member.Role;
import connected.communication.entity.member.RoleType;
import connected.communication.exception.MemberNotFoundException;
import connected.communication.repository.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired RoleRepository roleRepository;
    @PersistenceContext EntityManager em;

    @Test
    public void createAndReadTest() throws Exception{
        //given
        Member member = createMember();
        //when
        memberRepository.save(member);
        clear();
        //then
        Member foundMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFoundException::new);
        assertThat(foundMember.getId()).isEqualTo(member.getId());
    }

    @Test
    public void memberDateTest() throws Exception{
        //given
        Member member = createMember();
        //when
        memberRepository.save(member);
        clear();
        //then
        Member foundMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFoundException::new);
        assertThat(foundMember.getCreatedDate()).isNotNull();
        assertThat(foundMember.getLastModifiedDate()).isNotNull();
        assertThat(foundMember.getCreatedDate()).isEqualTo(foundMember.getLastModifiedDate());
    }

   @Test
   public void updateTest() throws Exception{
       //given
       String updatedNickname = "updated";
       Member member = memberRepository.save(createMember());
       clear();
       //when
       Member foundMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFoundException::new);
       foundMember.updateNickname(updatedNickname);
       clear();
       //then
       Member updateMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFoundException::new);
       assertThat(updateMember.getNickname()).isEqualTo(updatedNickname);
   }

   @Test
   public void deleteTest() throws Exception{
       //given
       Member member = memberRepository.save(createMember());
       clear();
       //when
       memberRepository.delete(member);
       //then
       assertThatThrownBy(() -> memberRepository.findById(member.getId()).orElseThrow(MemberNotFoundException::new))
               .isInstanceOf(MemberNotFoundException.class);
   }

   @Test
   public void findByEmailTest() throws Exception{
       //given
       Member member = memberRepository.save(createMember());
       clear();
       //when
       Member foundMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(MemberNotFoundException::new);
       //then
       assertThat(foundMember.getNickname()).isEqualTo(member.getNickname());
   }

   @Test
   public void uniqueEmailTest() throws Exception{
       //given
       Member member = memberRepository.save(createMember("email1", "password1", "username1", "nickname1"));
       clear();
       //when

       //then
       assertThatThrownBy(()->memberRepository.save(createMember(member.getEmail(), "password2", "username2", "nickname2")))
               .isInstanceOf(DataIntegrityViolationException.class);
   }

    @Test
    public void uniqueNicknameTest() throws Exception{
        //given
        Member member = memberRepository.save(createMember("email1", "password1", "username1", "nickname1"));
        clear();
        //when
        //then
        assertThatThrownBy(()-> memberRepository.save(createMember("email1", "password1", "username1", member.getNickname())))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void existsByEmailTest() throws Exception{
        //given
        Member member = memberRepository.save(createMember());
        clear();
        //when
        //then
        assertThat(memberRepository.existsByEmail(member.getEmail())).isTrue();
        assertThat(memberRepository.existsByEmail(member.getEmail() + "it is impossible")).isFalse();
    }

    @Test
    public void existsByNicknameTest() throws Exception{
        //given
        Member member = memberRepository.save(createMember());
        clear();
        //when
        //then
        assertThat(memberRepository.existsByNickname(member.getNickname())).isTrue();
        assertThat(memberRepository.existsByNickname(member.getNickname() + "it is impossible")).isFalse();
    }

    @Test
    public void memberRoleCascadePersistTest() throws Exception{
        //given
        List<RoleType> roleTypes = List.of(RoleType.ROLE_NORMAL, RoleType.ROLE_ADMIN, RoleType.ROLE_RESIDENT);
        List<Role> roles = roleTypes.stream().map(roleType -> new Role(roleType)).collect(Collectors.toList());
        roleRepository.saveAll(roles);
        clear();

        Member member = memberRepository.save(createMemberWithRoles(roleRepository.findAll()));
        clear();

        //when
        Member foundMember = memberRepository.findById(member.getId()).orElseThrow(MemberNotFoundException::new);
        Set<MemberRole> memberRoles = foundMember.getRoles();
        //then
        assertThat(memberRoles.size()).isEqualTo(roles.size());
    }

    @Test
    public void memberRoleCascadeDeleteTest() throws Exception{
        //given
        List<RoleType> roleTypes = List.of(RoleType.ROLE_NORMAL, RoleType.ROLE_ADMIN, RoleType.ROLE_RESIDENT);
        List<Role> roles = roleTypes.stream().map(roleType -> new Role(roleType)).collect(Collectors.toList());
        roleRepository.saveAll(roles);
        clear();

        Member member = memberRepository.save(createMemberWithRoles(roleRepository.findAll()));
        clear();
        //when
        memberRepository.deleteById(member.getId());
        clear();
        //then
        List<MemberRole> result = em.createQuery("select mr from MemberRole mr", MemberRole.class).getResultList();
        assertThat(result.size()).isZero();
    }

    private void clear(){
        em.flush();
        em.clear();
    }

    private Member createMemberWithRoles(List<Role> roles) {
        return new Member("email", "password", "username", "nickname", roles);
    }
    private Member createMember(String email, String password, String username, String nickname){
        return new Member(email, password, username, nickname, emptyList());
    }
    private Member createMember(){
        return new Member("email", "password", "username", "nickname", emptyList());
    }
}