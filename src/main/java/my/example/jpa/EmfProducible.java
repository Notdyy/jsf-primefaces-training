package my.example.jpa;

import javax.persistence.EntityManagerFactory;

public interface EmfProducible {

    /**
     * Produce the entity manager factory.
     *
     * @return The produced entity manager factory
     * @since 0.0.1
     */
    EntityManagerFactory produce();

    /**
     * Dispose the entity manager factory.
     *
     * @param disposingEmf
     *            The disposing entity manager factory.
     * @since 0.0.1
     */
    void dispose(final EntityManagerFactory disposingEmf);
}
