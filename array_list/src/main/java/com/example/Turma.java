package com.example;
import java.util.ArrayList;;
public class Turma {
    private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
    private String materia;

    public Turma(String material){
        setMateria(materia);
    }

    public void adicionarAluno(Aluno aluno){
        if (aluno == null){
            throw new IllegalArgumentException("Aluno inválido");
        }
        alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno){
        if (aluno == null){
            throw new IllegalArgumentException("Aluno inválido");
        }
        alunos.remove(aluno);
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public String getMateria() {
        return materia;
    }
}
