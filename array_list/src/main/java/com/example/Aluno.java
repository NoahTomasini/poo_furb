package com.example;

public class Aluno {
    private String nome;
    private String matricula;
    private double media;

    public Aluno(String nome, String matricula, double media){
        setNome(nome);
        setMatricula(matricula);
        setMedia(media);
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public String getMatricula() {
        return matricula;
    }

    public double getMedia() {
        return media;
    }

    public String getNome() {
        return nome;
    }
}
