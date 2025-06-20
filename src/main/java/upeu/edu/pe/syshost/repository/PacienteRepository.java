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
    
    // Buscar por DNI
    Optional<Paciente> findByDni(String dni);
    
    // Buscar por nombre (contiene)
    @Query("SELECT p FROM Paciente p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Paciente> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);
    
    // Buscar solo pacientes activos
    List<Paciente> findByActivoTrue();
    
    // Verificar si existe DNI (para validación)
    boolean existsByDni(String dni);
}