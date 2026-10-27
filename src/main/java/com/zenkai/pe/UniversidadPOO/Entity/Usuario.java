package com.zenkai.pe.UniversidadPOO.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zenkai.pe.UniversidadPOO.enums.EstadoUsuario;
import com.zenkai.pe.UniversidadPOO.enums.RolUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id_usuario;
    private String codigo;

    private String nombres, apellidos, dni, numero, correo;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;
    
    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasena;

    private char sexo;

    @Column(name="fechaRegistro")
    private LocalDate fechaRegistro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date cumpleanos;

    public Usuario() {
    }

    public Usuario(int id_usuario, String codigo, String nombres, String apellidos, String dni, String numero, String correo, RolUsuario rol, String contrasena, char sexo, Date cumpleanos) {
        this.id_usuario = id_usuario;
        this.codigo = codigo;
        this.nombres = nombres;
        this.estado = EstadoUsuario.ACTIVO;
        this.apellidos = apellidos;
        this.dni = dni;
        this.numero = numero;
        this.correo = correo;
        this.rol = rol;
        this.contrasena = contrasena;
        this.sexo = sexo;
        this.cumpleanos = cumpleanos;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getNumero() {
        return numero;
    }

    public String getCorreo() {
        return correo;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public char getSexo() {
        return sexo;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getCumpleanos() {
        return cumpleanos;
    }

    public void setCumpleanos(Date cumpleanos) {
        this.cumpleanos = cumpleanos;
    }

    public String getRol() {
        return rol.toString();
    }

    
    
}



