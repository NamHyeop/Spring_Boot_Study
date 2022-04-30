package bluish_Community_Project.bluish.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long MemberId);
}
