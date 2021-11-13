package springboot.service;

import org.springframework.stereotype.Component;
import springboot.model.Role;
import springboot.model.User;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DBInitializer {
    private final UserService userService;
    private final RoleService roleService;

    public DBInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
        roleService.addRole(new Role("ADMIN"));
        roleService.addRole(new Role("USER"));

        User commonUser = new User();
        commonUser.setName("CommonUserName");
        commonUser.setLastName("CommonUserLastName");
        commonUser.setCity("CommonUserCity");
        commonUser.setPassword("CommonUserPassword");
        commonUser.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.createUser(commonUser);

        User uncommonUser = new User();
        uncommonUser.setName("UncommonUserName");
        uncommonUser.setLastName("UncommonUserLastName");
        uncommonUser.setCity("UncommonUserCity");
        uncommonUser.setPassword("UncommonUserPassword");
        uncommonUser.setRoles(Set.of(roleService.getRoleByName("ADMIN"), roleService.getRoleByName("USER")));
        userService.createUser(uncommonUser);

        User admin = new User();
        admin.setName("AdminName");
        admin.setLastName("AdminLastName");
        admin.setCity("AdminCity");
        admin.setPassword("AdminPassword");
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.createUser(admin);
    }
}
