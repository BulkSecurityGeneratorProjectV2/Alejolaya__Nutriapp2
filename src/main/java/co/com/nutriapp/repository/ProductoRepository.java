package co.com.nutriapp.repository;

import co.com.nutriapp.domain.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Producto entity.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(value = "select distinct producto from Producto producto left join fetch producto.tags left join fetch producto.nutrientesAdicionales",
        countQuery = "select count(distinct producto) from Producto producto")
    Page<Producto> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct producto from Producto producto left join fetch producto.tags left join fetch producto.nutrientesAdicionales")
    List<Producto> findAllWithEagerRelationships();

    @Query("select producto from Producto producto left join fetch producto.tags left join fetch producto.nutrientesAdicionales where producto.id =:id")
    Optional<Producto> findOneWithEagerRelationships(@Param("id") Long id);

}
