package connected.communication.repository.role;

import connected.communication.entity.member.Role;
import connected.communication.entity.member.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
