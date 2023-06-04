package pl.krabelard.lines.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.krabelard.lines.entities.Route;
import pl.krabelard.lines.entities.line.LineDTO;

@Repository
public interface LineRepository extends JpaRepository<Route, String>{}
