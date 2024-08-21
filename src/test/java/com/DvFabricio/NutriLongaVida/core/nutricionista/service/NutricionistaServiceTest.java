package com.DvFabricio.NutriLongaVida.core.nutricionista.service;

import com.DvFabricio.NutriLongaVida.core.endereco.DadosEndereco;
import com.DvFabricio.NutriLongaVida.core.endereco.Endereco;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Especialidade;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Nutricionista;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.NutricionistaDTO;
import com.DvFabricio.NutriLongaVida.core.nutricionista.repository.NutricionistaRepository;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NutricionistaServiceTest {

    @Mock
    private NutricionistaRepository repository;

    @InjectMocks
    private NutricionistaService service;

    private Nutricionista criarNutricionista(boolean ativo) {
        Endereco endereco = new Endereco("RS", "Porto Alegre", "Centro", "Rua A", "90000-000", 123, "Apto 101");
        return new Nutricionista(1L, "João", "99999999901", "joao@gmail.com", "12345", "CRM123", Especialidade.NUTRICAO_CLINICA_GERAL, endereco, ativo);
    }

    @Test
    @DisplayName("Deve listar nutricionistas ativos com paginação")
    void listarAtivos() {
        Nutricionista nutricionista = criarNutricionista(true);
        Page<Nutricionista> page = new PageImpl<>(List.of(nutricionista));
        when(repository.findAllByAtivoTrue(any(Pageable.class))).thenReturn(page);

        Page<NutricionistaDTO> ativos = service.listarAtivos(Pageable.unpaged()); // Utilize Pageable.unpaged() ou um Pageable específico

        assertEquals(1, ativos.getTotalElements());
        assertEquals("João", ativos.getContent().get(0).nome());
        verify(repository, times(1)).findAllByAtivoTrue(any(Pageable.class));
    }

    @Test
    @DisplayName("Deve buscar nutricionista por nome com paginação")
    void buscarPorNome() {
        Nutricionista nutricionista = criarNutricionista(true);
        Page<Nutricionista> page = new PageImpl<>(List.of(nutricionista));
        when(repository.findByNomeIgnoreCaseContainingAndAtivoTrue(any(String.class), any(Pageable.class))).thenReturn(page);

        Page<NutricionistaDTO> result = service.buscarPorNome("João", Pageable.unpaged()); // Utilize Pageable.unpaged() ou um Pageable específico

        assertEquals(1, result.getTotalElements());
        assertEquals("João", result.getContent().get(0).nome());
        verify(repository, times(1)).findByNomeIgnoreCaseContainingAndAtivoTrue(any(String.class), any(Pageable.class));
    }


    @Test
    @DisplayName("Deve buscar nutricionistas por especialidade")
    void buscarPorEspecialidade() {
        Nutricionista nutricionista = criarNutricionista(true);
        when(repository.findByEspecialidade(Especialidade.NUTRICAO_CLINICA_GERAL)).thenReturn(List.of(nutricionista));

        List<NutricionistaDTO> result = service.buscarPorEspecialidade(Especialidade.NUTRICAO_CLINICA_GERAL);

        assertEquals(1, result.size());
        verify(repository, times(1)).findByEspecialidade(Especialidade.NUTRICAO_CLINICA_GERAL);
    }

    @Test
    @DisplayName("Deve cadastrar novo nutricionista")
    void cadastrar() {
        NutricionistaDTO dto = new NutricionistaDTO(null, "João", "joao@gmail.com", "99999999901", "12345", "CRM123", Especialidade.NUTRICAO_CLINICA_GERAL, new DadosEndereco("Rio Grande do sul", "Bom Principio", "Nova Columbia", "Rua Das Bergamoteiras", "95765000", 876, "Perto do Bar"));
        Nutricionista nutricionista = criarNutricionista(true);

        when(repository.save(any())).thenReturn(nutricionista);
        NutricionistaDTO result = service.cadastrar(dto);

        assertNotNull(result);
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar nutricionista com CPF já existente")
    void cadastrarComCpfExistente() {
        NutricionistaDTO dto = new NutricionistaDTO(null, "João", "joao@gmail.com", "99999999901", "12345", "CRM123", Especialidade.NUTRICAO_CLINICA_GERAL, new DadosEndereco("Rio Grande do Sul", "Bom Principio", "Nova Columbia", "Rua Das Bergamoteiras", "95765000", 876, "Perto do Bar"));
        when(repository.findByCpf(dto.cpf())).thenReturn(Optional.of(criarNutricionista(true)));

        assertThrows(ValidacaoException.class, () -> service.cadastrar(dto));
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve ativar nutricionista")
    void ativarNutricionista() {
        Nutricionista nutricionista = criarNutricionista(false);
        when(repository.findById(1L)).thenReturn(Optional.of(nutricionista));

        service.ativarNutricionista(1L);

        assertTrue(nutricionista.getAtivo());
        verify(repository, times(1)).save(nutricionista);
    }

    @Test
    @DisplayName("Deve desativar nutricionista")
    void desativarNutricionista() {
        Nutricionista nutricionista = criarNutricionista(true);
        when(repository.findById(1L)).thenReturn(Optional.of(nutricionista));

        service.desativarNutricionista(1L);

        assertFalse(nutricionista.getAtivo());
        verify(repository, times(1)).save(nutricionista);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar ativar nutricionista já ativo")
    void ativarNutricionistaJaAtivo() {
        Nutricionista nutricionistaAtivo = criarNutricionista(true);

        when(repository.findById(1L)).thenReturn(Optional.of(nutricionistaAtivo));

        ValidacaoException thrown = assertThrows(ValidacaoException.class, () -> {
            service.ativarNutricionista(1L);
        });

        assertEquals("Nutricionista já está ativo", thrown.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar desativar nutricionista já inativo")
    void desativarNutricionistaJaInativo() {
        Nutricionista nutricionistaInativo = criarNutricionista(false);

        when(repository.findById(1L)).thenReturn(Optional.of(nutricionistaInativo));

        ValidacaoException thrown = assertThrows(ValidacaoException.class, () -> {
            service.desativarNutricionista(1L);
        });

        assertEquals("Nutricionista já está inativo", thrown.getMessage());
        verify(repository, never()).save(any());
    }
}