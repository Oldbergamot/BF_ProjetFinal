package be.bruxellesformation.bf_projet_final.repository;

import be.bruxellesformation.bf_projet_final.model.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
