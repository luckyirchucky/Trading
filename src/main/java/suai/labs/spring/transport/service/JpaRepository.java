package suai.labs.spring.transport.service;

public interface JpaRepository<T extends JpaEntity>
        extends org.springframework.data.jpa.repository.JpaRepository<T, Long> {
}
