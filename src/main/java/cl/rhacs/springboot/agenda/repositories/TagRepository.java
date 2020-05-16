package cl.rhacs.springboot.agenda.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.rhacs.springboot.agenda.models.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    public Optional<Tag> findByName(final String name);

}
