/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Francisco
 */
public class ReporteJefe {
    private String rutEvaluado;
    private String perfil;
    private String Competencia;
    private int notaEsperada;
    private int nota;
    private int brecha;

    public String getRutEvaluado() {
        return rutEvaluado;
    }

    public void setRutEvaluado(String rutEvaluado) {
        this.rutEvaluado = rutEvaluado;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getCompetencia() {
        return Competencia;
    }

    public void setCompetencia(String Competencia) {
        this.Competencia = Competencia;
    }

    public int getNotaEsperada() {
        return notaEsperada;
    }

    public void setNotaEsperada(int notaEsperada) {
        this.notaEsperada = notaEsperada;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getBrecha() {
        return brecha;
    }

    public void setBrecha(int brecha) {
        this.brecha = brecha;
    }

    public ReporteJefe() {
    }

    public ReporteJefe(String rutEvaluado, String perfil, String Competencia, int notaEsperada, int nota) {
        this.rutEvaluado = rutEvaluado;
        this.perfil = perfil;
        this.Competencia = Competencia;
        this.notaEsperada = notaEsperada;
        this.nota = nota;
        this.brecha = this.nota - this.notaEsperada;
    }
    
    
    
}
