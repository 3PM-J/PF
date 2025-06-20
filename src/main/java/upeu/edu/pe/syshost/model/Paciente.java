package upeu.edu.pe.syshost.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, unique = true, length = 8)
    private String dni;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    
    @Column(length = 9)
    private String telefono;
    
    @Column(length = 200)
    private String direccion;
    
    @Column(length = 20)
    private String sexo;
    
    @Column(length = 100)
    private String correo;
    
    @Column(length = 500)
    private String caso;
    
    private Double estatura;
    
    private Double peso;
    
    @Column(name = "tipo_sangre", length = 5)
    private String tipoSangre;
    
    @Column(name = "enfermedad_genetica", length = 300)
    private String enfermedadGenetica;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    // Método para calcular la edad
    @Transient
    public Integer getEdad() {
        if (fechaNacimiento != null) {
            return Period.between(fechaNacimiento, LocalDate.now()).getYears();
        }
        return null;
    }
}