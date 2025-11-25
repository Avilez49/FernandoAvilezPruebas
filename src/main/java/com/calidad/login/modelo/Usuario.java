package com.calidad.login.modelo;

public class Usuario {
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean isLogged;

    public Usuario(String name, String email, String password, boolean isLogged) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isLogged = isLogged;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    
    /* 
    docker run -p 3307:3306 --name mysql-dbunit -e MYSQL_ROOT_PASSWORD=123456 -d mysql:latest

    docker exec -it mysql-dbunit mysql -uroot -p

    Les debe pedir el password. Escribir : 123456
    */
}
