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
@Table(name="persona")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @OneToOne
    @JoinColumn(name = "id_tipo_doc_identidad", updatable = false, nullable = false)
    private TipoDocIdentidadEntity tipoDocIdentidad;

    @OneToOne
    @JoinColumn(name = "id_tipo_persona", updatable = false, nullable = false)
    private TipoPersonaEntity tipoPersona;

    @OneToOne
    @JoinColumn(name = "cod_ubigeo", nullable = false)
    private UbigeoEntity ubigeo;

    @Column(name="nro_documento")
    private String nroDocumento;
    @Column(name="nombres")
    private String nombres;
    @Column(name="apellido_paterno")
    private String apellidoPaterno;
    @Column(name="apellido_materno")
    private String apellidoMaterno;
    @Column(name = "direccion")
    private String direccion;
    @Column(name="correo")
    private String correo;
    @Column(name="celular")
    private String celular;
    @Column(name="ruc")
    private String ruc;
    @Column(name="razon_social")
    private String razonSocial;
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
