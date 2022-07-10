package dev.unscrud.leilao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.unscrud.leilao.dao.LanceDao;
import dev.unscrud.leilao.dao.LeilaoDao;
import dev.unscrud.leilao.dao.UsuarioDao;
import dev.unscrud.leilao.dto.NovoLanceDto;
import dev.unscrud.leilao.model.Lance;
import dev.unscrud.leilao.model.Leilao;
import dev.unscrud.leilao.model.Usuario;

@Service
public class LanceService {

	@Autowired
	private LanceDao lances;

	@Autowired
	private UsuarioDao usuarios;

	@Autowired
	private LeilaoDao leiloes;

	public boolean propoeLance(NovoLanceDto lanceDto, String nomeUsuario) {

		Usuario usuario = usuarios.buscarPorUsername(nomeUsuario);
		Lance lance = lanceDto.toLance(usuario);

		Leilao leilao = this.getLeilao(lanceDto.getLeilaoId());

		if (leilao.propoe(lance)) {
			lances.salvar(lance);
			return true;
		}

		return false;
	}

	public Leilao getLeilao(Long leilaoId) {
		return leiloes.buscarPorId(leilaoId);
	}

}