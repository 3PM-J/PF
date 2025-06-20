package upeu.edu.pe.syshost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import upeu.edu.pe.syshost.model.Paciente;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    // Buscar pacientes por nombre (ignorando mayúsculas/minúsculas)
    @Query("SELECT p FROM Paciente p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Paciente> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);
    
    // Buscar por DNI
    Optional<Paciente> findByDni(String dni);
    
    // Buscar pacientes activos
    List<Paciente> findByActivoTrue();
    
    // Verificar si existe un DNI (útil para validaciones)
    boolean existsByDni(String dni);
}