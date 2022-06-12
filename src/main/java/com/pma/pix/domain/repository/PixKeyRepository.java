package com.pma.pix.domain.repository;

import com.pma.pix.domain.model.PixKey;
import com.pma.pix.domain.model.TipoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKey, String>, JpaSpecificationExecutor<PixKey> {

    long countByTipoPessoaAndAgenciaAndConta(TipoPessoa tipo, Integer agencia, Integer conta);
}
