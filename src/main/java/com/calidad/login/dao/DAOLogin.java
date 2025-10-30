package com.calidad.login.dao;

import java.util.List;

import com.calidad.login.modelo.Usuario;

public class DAOLogin implements IDAOLogin {

    @Override
    public Usuario findUsuarioByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int save(Usuario u) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public List<Usuario> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public boolean deleteById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Usuario updateUser(Usuario userOld) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public Usuario findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}