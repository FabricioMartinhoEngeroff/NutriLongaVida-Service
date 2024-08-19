package com.DvFabricio.NutriLongaVida.core.nutricionista.service;

import com.DvFabricio.NutriLongaVida.core.endereco.DadosEndereco;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Especialidade;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Nutricionista;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.NutricionistaDTO;
import com.DvFabricio.NutriLongaVida.core.nutricionista.repository.NutricionistaRepository;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NutricionistaService {

    @Autowired
    private NutricionistaRepository repository;

    // Listar nutricionistas ativos
    public List<NutricionistaDTO> listarAtivos() {
        return repository.findByAtivoTrue().stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // Buscar nutricionista por nome
    public Optional<NutricionistaDTO> buscarPorNome(String nome) {
        return repository.findByNome(nome)
                .map(this::converterParaDTO);
    }

    // Buscar nutricionista por especialidade
    public List<NutricionistaDTO> buscarPorEspecialidade(Especialidade especialidade) {
        return repository.findByEspecialidade(especialidade).stream()
                .map(this::converterParaDTO)
                .toList();
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
            throw new IllegalArgumentException("CPF já cadastrado.");
        });
        repository.findByRegistroProfissionalCrm(dto.registroProfissionalCrm()).ifPresent(nutricionista -> {
            throw new IllegalArgumentException("CRM já cadastrado.");
        });
    }

    @Transactional
    public void ativarNutricionista(Long id) {
        alterarStatusNutricionista(id, true);
    }

    @Transactional
    public void desativarNutricionista(Long id) {
        alterarStatusNutricionista(id, false);
    }

    private void alterarStatusNutricionista(Long id, Boolean status) {
        Nutricionista nutricionista = buscarPorId(id);
        if (nutricionista.getAtivo().equals(status)) {
            throw new IllegalStateException(String.format("Nutricionista já está %s", status ? "ativo" : "inativo"));
        }
        nutricionista.setAtivo(status);
        repository.save(nutricionista);
    }

    private Nutricionista buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista não encontrado com id " + id));
    }

    private NutricionistaDTO converterParaDTO(Nutricionista nutricionista) {
        return new NutricionistaDTO(
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
