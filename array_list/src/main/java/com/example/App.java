package com.example;

public class App {
    
    public void getMaiorMedia(Turma turma){
        double maiormedia = -1; //descobre a maior média entre alunos
        String aluno_maior_media = "";
        for (Aluno a: turma.getAlunos()) {
            if (a.getMedia() > maiormedia) {
                maiormedia = a.getMedia();
                aluno_maior_media = a.getNome();
            }
        }
        System.out.printf("\nMaior média = %.2f (%s)\n", maiormedia, aluno_maior_media);
    }
    
    public void getMediaTurma(Turma turma){
        double media_geral = 0;
        for (Aluno a: turma.getAlunos()){
            media_geral = media_geral + a.getMedia();
        }
        media_geral = media_geral/turma.getAlunos().size();
        System.out.printf("\nMédia da turma: %.2f", media_geral);
        //divide a média de todos somadas pelo tamanho - quantidade de registros
    }
    public static void main(String[] args) {
        Turma turma = new Turma("POO");
        App i = new App();
        Aluno a1 = new Aluno("Elvira", "1234", 8);
        Aluno a2 = new Aluno("Noah", "2234", 7);
        Aluno a3 = new Aluno("Leucadia", "3234", 9);

        turma.adicionarAluno(a1);
        turma.adicionarAluno(a2);
        turma.adicionarAluno(a3);
        i.getMediaTurma(turma);
        i.getMaiorMedia(turma);

        for (Aluno a: turma.getAlunos()) {
            System.out.println(a.getNome());
        } //for each
    }
}