package com.DvFabricio.NutriLongaVida.core.paciente.service;

import com.DvFabricio.NutriLongaVida.core.endereco.DadosEndereco;
import com.DvFabricio.NutriLongaVida.core.paciente.domain.Paciente;
import com.DvFabricio.NutriLongaVida.core.paciente.domain.PacienteDTO;
import com.DvFabricio.NutriLongaVida.core.paciente.repository.PacienteRepository;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ResourceNotFoundException;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    // Listar pacientes ativos com paginação
    public Page<PacienteDTO> listarAtivos(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(this::converterParaDTO);
    }

    // Buscar pacientes por nome com paginação
    public Page<PacienteDTO> buscarPorNome(String nome, Pageable paginacao) {
        return repository.findByNomeIgnoreCaseContainingAndAtivoTrue(nome, paginacao)
                .map(this::converterParaDTO);
    }

    // Buscar paciente por ID e retornar DTO
    public PacienteDTO buscarPorId(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id " + id));
        return converterParaDTO(paciente);
    }

    // Cadastrar paciente
    @Transactional
    public PacienteDTO cadastrar(PacienteDTO dto) {
        validarUnicidade(dto);
        Paciente paciente = new Paciente(dto);
        repository.save(paciente);
        return converterParaDTO(paciente);
    }

    // Validar unicidade de CPF
    private void validarUnicidade(PacienteDTO dto) {
        repository.findByCpf(dto.cpf()).ifPresent(paciente -> {
            throw new ValidacaoException("CPF já cadastrado.");
        });
    }

    // Reativar paciente
    @Transactional
    public void reativarPaciente(Long pacienteId) {
        var paciente = repository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id " + pacienteId));
        paciente.reativar();
        repository.save(paciente);
    }

    // Desativar paciente
    @Transactional
    public void desativarPaciente(Long pacienteId) {
        var paciente = repository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id " + pacienteId));
        paciente.excluir();
        repository.save(paciente);
    }

    // Alterar status do paciente (ativar/desativar)
    @Transactional
    public void alterarStatusPaciente(Long id, Boolean status) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id " + id));

        if (paciente.getAtivo().equals(status)) {
            throw new ValidacaoException(String.format("Paciente já está %s", status ? "ativo" : "inativo"));
        }

        paciente.setAtivo(status);
        repository.save(paciente);
    }

    // Atualizar informações do paciente
    @Transactional
    public void atualizarInformacoesPaciente(Long id, PacienteDTO dto) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id " + id));

        // Atualiza as informações do paciente apenas se os valores do DTO não forem nulos
        if (dto.nome() != null) {
            paciente.setNome(dto.nome());
        }
        if (dto.email() != null) {
            paciente.setEmail(dto.email());
        }
        if (dto.telefone() != null) {
            paciente.setTelefone(dto.telefone());
        }
        if (dto.endereco() != null) {
            paciente.getEndereco().atualizarInformacaoEndereco(dto.endereco());
        }

        repository.save(paciente);
    }

    // Converter Paciente para PacienteDTO
    private PacienteDTO converterParaDTO(Paciente paciente) {
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getTelefone(),
                paciente.getGenero(),
                new DadosEndereco(
                        paciente.getEndereco().getEstado(),
                        paciente.getEndereco().getCidade(),
                        paciente.getEndereco().getBairro(),
                        paciente.getEndereco().getRua(),
                        paciente.getEndereco().getCep(),
                        paciente.getEndereco().getNumeroCasa(),
                        paciente.getEndereco().getComplemento()
                )
        );
    }
}