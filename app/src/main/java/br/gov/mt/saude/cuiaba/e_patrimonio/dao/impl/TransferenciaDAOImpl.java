package br.gov.mt.saude.cuiaba.e_patrimonio.dao.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import br.gov.mt.saude.cuiaba.e_patrimonio.dao.TransferenciaDAO;
import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Transferencia;
import br.gov.mt.saude.cuiaba.e_patrimonio.persisten.AppDatabase;

public class TransferenciaDAOImpl implements TransferenciaDAO {
    private AppDatabase database;

    @Inject
    public TransferenciaDAOImpl(Context context) {
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    public void importarTransferencia(List<Transferencia> transferencias) {
        this.database.transferenciaDAO().importarTransferencia(transferencias);
    }

    @Override
    public void removerTodasTRansferencia() {
        this.database.transferenciaDAO().removerTodasTRansferencia();
    }

    @Override
    public Transferencia pesquisaTransferenciaPorPatrimonio(String pNUMERO) {
        return this.database.transferenciaDAO().pesquisaTransferenciaPorPatrimonio(pNUMERO);
    }

    @Override
    public List<Transferencia> pesquisaTransferenciaPorSerialPatrimonio(String pNUMERO, String pSERIAL) {
        return this.database.transferenciaDAO().pesquisaTransferenciaPorSerialPatrimonio(pNUMERO,pSERIAL);
    }

    @Override
    public Transferencia pesquisaTransferenciaPorSerial(String pSERIAL) {
        return this.database.transferenciaDAO().pesquisaTransferenciaPorSerial(pSERIAL);
    }

    @Override
    public List<Transferencia> pesquisaTodasTransferencia() {
        return this.database.transferenciaDAO().pesquisaTodasTransferencia();
    }

    @Override
    public void removerTransferenciaPorCodigo(Transferencia transferencia) {
        this.database.transferenciaDAO().removerTransferenciaPorCodigo(transferencia);
    }

    @Override
    public void inserirTransferencia(Transferencia transferencia) {
        this.database.transferenciaDAO().inserirTransferencia(transferencia);
    }

    @Override
    public List<Transferencia> pesquisaTransferenciaFlag() {
        return this.database.transferenciaDAO().pesquisaTransferenciaFlag();
    }

    @Override
    public void deletarTransferenciaPorFlag() {
        this.database.transferenciaDAO().deletarTransferenciaPorFlag();
    }

    public void close(){
        database.destroyInstance();
    }
}
