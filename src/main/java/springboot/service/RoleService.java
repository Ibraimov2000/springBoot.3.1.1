package springboot.service;



import springboot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getByIdRole(int id);
}
