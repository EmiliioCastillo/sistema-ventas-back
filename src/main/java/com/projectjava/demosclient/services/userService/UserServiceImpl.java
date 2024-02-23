package com.projectjava.demosclient.services.userService;
/*
import com.projectjava.demosclient.dao.UserDao;
import com.projectjava.demosclient.dto.UsuarioDTO;
import com.projectjava.demosclient.entity.Rol;
import com.projectjava.demosclient.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDao userDao;

//Aqui se carga el usuario por nombre de usuario que es el email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userDao.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("User unvalido");
        }
        return new User(
                usuario.getEmail(),
                usuario.getPassword(), authority(usuario.getListRoles()));
    }

        // Este método se encarga de mapear los
        // roles a objetos SimpleGrantedAuthority y
        // luego los recopila en una lista utilizando Stream y Collectors
    private Collection<? extends GrantedAuthority> authority(Collection<Rol> roles){
       return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombrerol())).collect(Collectors.toList());
    }


    @Override
    public List<Usuario> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(UsuarioDTO usuario) {
        Usuario usuarioSave = new Usuario(usuario.getId(),
                usuario.getNombre(), usuario.getApellido(),
                usuario.getEmail(), bCryptPasswordEncoder.encode(usuario.getPassword()),
                usuario.getRoles());


        userDao.save(usuarioSave);
    }



    @Override
    public void editUser(Long id) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}


*/