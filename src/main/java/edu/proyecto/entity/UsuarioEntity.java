package edu.proyecto.entity;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuario")
public class UsuarioEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @OneToOne
    @JoinColumn(name = "id_persona", updatable = false, nullable = false)
    private PersonaEntity persona;

    @Column(name="password")
    private String password;

    @Column(name="codigo_verificacion")
    private String codigoVerificacion;

    @Column(name="verificado")
    private Boolean verificado;
    
    @Column(name = "fechaVerificacion")
    private Date fechaVerificacion;

    @Column(name="activo")
    private Boolean activo;

    @Column(name="reg_fecha_creacion")
    private Date regFechaCreacion;
    @Column(name="reg_usuario_creacion")
    private String regUsuarioCreacion;
    @Column(name="reg_ip_creacion")
    private String regIpCreacion;
    @Column(name="reg_fecha_modificacion")
    private Date regFechaModificacion;
    @Column(name="reg_usuario_modificacion")
    private String regUsuarioModificacion;
    @Column(name="reg_ip_modificacion")
    private String regIpModificacion;
}
