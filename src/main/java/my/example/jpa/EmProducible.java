package my.example.jpa;

import javax.persistence.EntityManager;

public interface EmProducible  {

    /**
     * Produce the entity manager.
     *
     * @return The produced entity manager
     * @since 0.0.1
     */
    EntityManager produce();

    /**
     * Dispose the entity manager.
     *
     * @param disposingEm
     *            The disposing entity manager.
     * @since 0.0.1
     */
    void dispose(final EntityManager disposingEm);
}
