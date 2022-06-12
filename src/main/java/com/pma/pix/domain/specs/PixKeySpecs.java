package com.pma.pix.domain.specs;

import com.pma.pix.domain.model.PixKey;
import com.pma.pix.domain.model.PixKeyFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class PixKeySpecs {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("dd/MM/yyyy HH:mm");
  public static Specification<PixKey> querYFilter(PixKeyFilter pixKeyFilter) {
    return ((root, query, criteriaBuilder) -> {
      var predicates = new ArrayList<Predicate>();

      if (pixKeyFilter.getId() != null) {
        predicates.add(criteriaBuilder.equal(root.get("id"), UUID.fromString(pixKeyFilter.getId())));
      }

      if (pixKeyFilter.getTipoChave() != null) {
        predicates.add(criteriaBuilder.equal(root.get("tipoChave"), pixKeyFilter.getTipoChave()));
      }

      if (pixKeyFilter.getAgencia() != null) {
        predicates.add(criteriaBuilder.equal(root.get("agencia"), pixKeyFilter.getAgencia()));
      }

      if (pixKeyFilter.getConta() != null) {
        predicates.add(criteriaBuilder.equal(root.get("conta"), pixKeyFilter.getConta()));
      }

      if (pixKeyFilter.getNomeCorrentista() != null) {
        predicates.add(
            criteriaBuilder.equal(root.get("nomeCorrentista"), pixKeyFilter.getNomeCorrentista()));
      }

      if (pixKeyFilter.getDataAtivacao() != null) {
        OffsetDateTime dateInit = LocalDateTime.parse(pixKeyFilter.getDataAtivacao() + " 00:00", formatter)
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toOffsetDateTime();

        OffsetDateTime dateEnd = LocalDateTime.parse(pixKeyFilter.getDataAtivacao() + " 23:59", formatter)
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toOffsetDateTime();

        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataAtivacao"), dateInit));
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataAtivacao"), dateEnd));
      }

      if (pixKeyFilter.getDataInativacao() != null) {
        OffsetDateTime dateInit = LocalDateTime.parse(pixKeyFilter.getDataInativacao() + " 00:00", formatter)
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toOffsetDateTime();

        OffsetDateTime dateEnd = LocalDateTime.parse(pixKeyFilter.getDataInativacao() + " 23:59", formatter)
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toOffsetDateTime();

        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataInativacao"), dateInit));
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataInativacao"), dateEnd));
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    });
  }
}
