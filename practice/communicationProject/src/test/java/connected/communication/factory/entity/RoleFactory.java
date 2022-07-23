package connected.communication.factory.entity;

import connected.communication.entity.member.Role;
import connected.communication.entity.member.RoleType;

public class RoleFactory {
    public static Role createRole(){
        return new Role(RoleType.ROLE_NORMAL);
    }
}
