package com.DvFabricio.NutriLongaVida.core.nutricionista.service;

import com.DvFabricio.NutriLongaVida.core.endereco.DadosEndereco;
import com.DvFabricio.NutriLongaVida.core.endereco.Endereco;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Especialidade;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Nutricionista;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.NutricionistaDTO;
import com.DvFabricio.NutriLongaVida.core.nutricionista.repository.NutricionistaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @DisplayName("Deve listar nutricionistas ativos")
    void listarAtivos() {
        Nutricionista nutricionista = criarNutricionista(true);
        when(repository.findByAtivoTrue()).thenReturn(List.of(nutricionista));

        List<NutricionistaDTO> ativos = service.listarAtivos();

        assertEquals(1, ativos.size());
        verify(repository, times(1)).findByAtivoTrue();
    }

    @Test
    @DisplayName("Deve buscar nutricionista por nome")
    void buscarPorNome() {
        Nutricionista nutricionista = criarNutricionista(true);
        when(repository.findByNome("João")).thenReturn(Optional.of(nutricionista));

        Optional<NutricionistaDTO> result = service.buscarPorNome("João");

        assertTrue(result.isPresent());
        assertEquals("João", result.get().nome());
        verify(repository, times(1)).findByNome("João");
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
        NutricionistaDTO dto = new NutricionistaDTO(null, "João", "joao@gmail.com", "99999999901", "12345", "CRM123", Especialidade.NUTRICAO_CLINICA_GERAL, new DadosEndereco("Rio Grande do sul", "Bom Principio", "Nova Columbia", "Rua Das Bergamoteiras", "95765000", 876, "Perto do Bar"));
        when(repository.findByCpf(dto.cpf())).thenReturn(Optional.of(criarNutricionista(true)));

        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(dto));
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
    @DisplayName("Deve lançar exceção ao tentar ativar ou desativar nutricionista com status já existente")
    void ativarOuDesativarComStatusExistente() {
        Nutricionista nutricionistaAtivo = criarNutricionista(true);
        Nutricionista nutricionistaInativo = criarNutricionista(false);

        when(repository.findById(1L)).thenReturn(Optional.of(nutricionistaAtivo));

        assertThrows(IllegalStateException.class, () -> service.ativarNutricionista(1L));
        verify(repository, never()).save(any());

        when(repository.findById(1L)).thenReturn(Optional.of(nutricionistaInativo));

        assertThrows(IllegalStateException.class, () -> service.desativarNutricionista(1L));
        verify(repository, never()).save(any());
    }
}