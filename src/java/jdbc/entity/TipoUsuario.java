/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.entity;



/**
 *
 * @author vesprada
 */
public class TipoUsuario  {
    
    private Integer id;
    private String descripcion;

    public TipoUsuario() {
    }

    public TipoUsuario(Integer id) {
        this.id = id;
    }

    public TipoUsuario(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jdbc.entity.TipoUsuario[ id=" + id + " ]";
    }
    
}
