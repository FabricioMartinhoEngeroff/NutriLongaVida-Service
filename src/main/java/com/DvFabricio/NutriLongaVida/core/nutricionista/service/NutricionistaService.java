package com.DvFabricio.NutriLongaVida.core.nutricionista.service;

import com.DvFabricio.NutriLongaVida.core.endereco.DadosEndereco;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Especialidade;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Nutricionista;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.NutricionistaDTO;
import com.DvFabricio.NutriLongaVida.core.nutricionista.repository.NutricionistaRepository;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ResourceNotFoundException;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NutricionistaService {

    @Autowired
    private NutricionistaRepository repository;

    // Listar nutricionistas ativos com paginação
    public Page<NutricionistaDTO> listarAtivos(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(this::converterParaDTO);
    }

    // Buscar nutricionistas por nome com paginação
    public Page<NutricionistaDTO> buscarPorNome(String nome, Pageable paginacao) {
        return repository.findByNomeIgnoreCaseContainingAndAtivoTrue(nome, paginacao)
                .map(this::converterParaDTO);
    }

    // Buscar nutricionista por especialidade
    public List<NutricionistaDTO> buscarPorEspecialidade(Especialidade especialidade) {
        return repository.findByEspecialidade(especialidade).stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // Buscar nutricionista por ID
    public Nutricionista buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista não encontrado com id " + id));
    }

    // Cadastrar nutricionista
    @Transactional
    public NutricionistaDTO cadastrar(NutricionistaDTO dto) {
        validarUnicidade(dto);
        Nutricionista nutricionista = new Nutricionista(dto);
        repository.save(nutricionista);
        return converterParaDTO(nutricionista);
    }

    // Validar unicidade de CPF e CRM
    private void validarUnicidade(NutricionistaDTO dto) {
        repository.findByCpf(dto.cpf()).ifPresent(nutricionista -> {
            throw new ValidacaoException("CPF já cadastrado.");
        });
        repository.findByRegistroProfissionalCrm(dto.registroProfissionalCrm()).ifPresent(nutricionista -> {
            throw new ValidacaoException("CRM já cadastrado.");
        });
    }

    @Transactional
    public void ativarNutricionista(Long id) {
        Nutricionista nutricionista = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista não encontrado com id " + id));

        if (nutricionista.getAtivo()) {
            throw new ValidacaoException("Nutricionista já está ativo");
        }

        nutricionista.setAtivo(true);
        repository.save(nutricionista);
    }

    @Transactional
    public void desativarNutricionista(Long id) {
        Nutricionista nutricionista = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista não encontrado com id " + id));

        if (!nutricionista.getAtivo()) {
            throw new ValidacaoException("Nutricionista já está inativo");
        }

        nutricionista.setAtivo(false);
        repository.save(nutricionista);
    }

    public void alterarStatusNutricionista(Long id, Boolean status) {
        Nutricionista nutricionista = buscarPorId(id);
        if (nutricionista.getAtivo().equals(status)) {
            throw new ValidacaoException(String.format("Nutricionista já está %s", status ? "ativo" : "inativo"));
        }
        nutricionista.setAtivo(status);
        repository.save(nutricionista);
    }


    public void atualizarInformacoesNutricionista(Long id, NutricionistaDTO dto) {
        Nutricionista nutricionista = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista não encontrado com id " + id));

        // Atualiza as informações do nutricionista apenas se os valores do DTO não forem nulos
        if (dto.nome() != null) {
            nutricionista.setNome(dto.nome());
        }
        if (dto.cpf() != null) {
            nutricionista.setCpf(dto.cpf());
        }
        if (dto.registroProfissionalCrm() != null) {
            nutricionista.setRegistroProfissionalCrm(dto.registroProfissionalCrm());
        }
        if (dto.email() != null) {
            nutricionista.setEmail(dto.email());
        }
        if (dto.telefone() != null) {
            nutricionista.setTelefone(dto.telefone());
        }
        if (dto.especialidade() != null) {
            nutricionista.setEspecialidade(dto.especialidade());
        }
        if (dto.endereco() != null) {
            nutricionista.getEndereco().atualizarInformacaoEndereco(dto.endereco());
        }

        repository.save(nutricionista);
    }

    private NutricionistaDTO converterParaDTO(Nutricionista nutricionista) {
        return new NutricionistaDTO(
                nutricionista.getId(),
                nutricionista.getNome(),
                nutricionista.getEmail(),
                nutricionista.getCpf(),
                nutricionista.getTelefone(),
                nutricionista.getRegistroProfissionalCrm(),
                nutricionista.getEspecialidade(),
                new DadosEndereco(
                        nutricionista.getEndereco().getEstado(),
                        nutricionista.getEndereco().getCidade(),
                        nutricionista.getEndereco().getBairro(),
                        nutricionista.getEndereco().getRua(),
                        nutricionista.getEndereco().getCep(),
                        nutricionista.getEndereco().getNumeroCasa(),
                        nutricionista.getEndereco().getComplemento()
                )
        );
    }
}