/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.entity;

import java.util.Date;



/**
 *
 * @author vesprada
 */
public class Incidencia {
   
    private Integer id;
   
    private String resumen;
    
    private String cambios;
   
    private String idEstado;
   
    private int idRepositorio;
  
    private int idUsuario;
   
    private Date fechaAlta;
   
    private Date fechaResolucion;

    public Incidencia() {
    }

    public Incidencia(Integer id) {
        this.id = id;
    }

    public Incidencia(Integer id, String resumen, String cambios, String idEstado, int idRepositorio, int idUsuario, Date fechaAlta, Date fechaResolucion) {
        this.id = id;
        this.resumen = resumen;
        this.cambios = cambios;
        this.idEstado = idEstado;
        this.idRepositorio = idRepositorio;
        this.idUsuario = idUsuario;
        this.fechaAlta = fechaAlta;
        this.fechaResolucion = fechaResolucion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getCambios() {
        return cambios;
    }

    public void setCambios(String cambios) {
        this.cambios = cambios;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdRepositorio() {
        return idRepositorio;
    }

    public void setIdRepositorio(int idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incidencia)) {
            return false;
        }
        Incidencia other = (Incidencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jdbc.entity.Incidencia[ id=" + id + " ]";
    }
    
}
