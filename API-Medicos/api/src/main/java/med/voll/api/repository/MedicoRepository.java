package med.voll.api.repository;

import med.voll.api.models.Medicos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medicos, Long> {


    Page<Medicos> findAllByAtivoTrue(Pageable paginacao);
}
