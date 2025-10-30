package com.calidad.login.dao;

import java.util.List;

import com.calidad.login.modelo.Usuario;

public interface IDAOLogin {
    Usuario findUsuarioByEmail(String email);

    int save(Usuario u);

    List<Usuario> findAll();
    
    boolean deleteById(int id);

    Usuario updateUser(Usuario userOld);

    Usuario findById(int id);    
}
