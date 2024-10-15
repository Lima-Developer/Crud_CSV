package Domain.Models;

import Domain.Interfaces.Conversor;

public class DataBaseLine implements Conversor {
    private byte[] removidoBytes;
    private byte[] grupoBytes;
    private byte[] popularidadeBytes;
    private byte[] pesoBytes;
    private byte[] tamanhoTecnologiaOrigemBytes;
    private byte[] nomeTecnologiaOrigemBytes;
    private byte[] tamanhoTecnologiaDestinoBytes;
    private byte[] nomeTecnologiaDestinoBytes;

    public DataBaseLine(String[] csvLine) {
        this.removidoBytes = Conversor.intoBytes(String.valueOf(0), 1);
        this.grupoBytes = Conversor.intoBytes(csvLine[1], 4);
        this.popularidadeBytes = Conversor.intoBytes(csvLine[2], 4);
        this.pesoBytes = Conversor.intoBytes(csvLine[4], 4);


        this.tamanhoTecnologiaOrigemBytes = Conversor.intoBytes(String.valueOf(csvLine[0].length()), 4);
        this.tamanhoTecnologiaDestinoBytes = Conversor.intoBytes(String.valueOf(csvLine[3].length()), 4);


        this.nomeTecnologiaOrigemBytes = Conversor.intoBytes(csvLine[0], csvLine[0].length());
        this.nomeTecnologiaDestinoBytes = Conversor.intoBytes(csvLine[3], csvLine[3].length());
    }

    public DataBaseLine() {

    }

    public byte[] getRemovidoBytes() {
        return removidoBytes;
    }

    public void setRemovidoBytes(byte[] removidoBytes) {
        this.removidoBytes = removidoBytes;
    }

    public byte[] getGrupoBytes() {
        return grupoBytes;
    }

    public void setGrupoBytes(byte[] grupoBytes) {
        this.grupoBytes = grupoBytes;
    }

    public byte[] getPopularidadeBytes() {
        return popularidadeBytes;
    }

    public void setPopularidadeBytes(byte[] popularidadeBytes) {
        this.popularidadeBytes = popularidadeBytes;
    }

    public byte[] getPesoBytes() {
        return pesoBytes;
    }

    public void setPesoBytes(byte[] pesoBytes) {
        this.pesoBytes = pesoBytes;
    }

    public byte[] getTamanhoTecnologiaOrigemBytes() {
        return tamanhoTecnologiaOrigemBytes;
    }

    public void setTamanhoTecnologiaOrigemBytes(byte[] tamanhoTecnologiaOrigemBytes) {
        this.tamanhoTecnologiaOrigemBytes = tamanhoTecnologiaOrigemBytes;
    }

    public byte[] getNomeTecnologiaOrigemBytes() {
        return nomeTecnologiaOrigemBytes;
    }

    public void setNomeTecnologiaOrigemBytes(byte[] nomeTecnologiaOrigemBytes) {
        this.nomeTecnologiaOrigemBytes = nomeTecnologiaOrigemBytes;
    }

    public byte[] getTamanhoTecnologiaDestinoBytes() {
        return tamanhoTecnologiaDestinoBytes;
    }

    public void setTamanhoTecnologiaDestinoBytes(byte[] tamanhoTecnologiaDestinoBytes) {
        this.tamanhoTecnologiaDestinoBytes = tamanhoTecnologiaDestinoBytes;
    }

    public byte[] getNomeTecnologiaDestinoBytes() {
        return nomeTecnologiaDestinoBytes;
    }

    public void setNomeTecnologiaDestinoBytes(byte[] nomeTecnologiaDestinoBytes) {
        this.nomeTecnologiaDestinoBytes = nomeTecnologiaDestinoBytes;
    }
}
