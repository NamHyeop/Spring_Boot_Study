package bluish_Community_Project.bluish.member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long MemberId);
}
