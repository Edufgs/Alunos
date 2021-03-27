package vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe Objeto Alunos
 * 
 * @author Eduardo Gonçalvesa da Silva
 */
@Entity //Diz que essa classe vai gerar objetos a serem persistidos
//Define o nome da tabela, se não a tabela vai ser do mesmo nome da classe (cria se não existir)
//Tambem pode colocar o mesmo nome de uma tabela que ja existe no bando de dados
@Table(name = "alunosnotas")

public class AlunosVO implements Serializable {
    @Id//o valor do campo codigo é valor da chave primaria la no banco (É codigo pq vem depois dessa definição)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//Diz que o valor da chave primaria vai ser gerado automaticamente pelo banco(Tipo SEQUENCE)
    private int matricula;
    private String nome;
    private float nota1;
    private float nota2;
    private float nota3;
    private float nota4;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }

    public float getNota3() {
        return nota3;
    }

    public void setNota3(float nota3) {
        this.nota3 = nota3;
    }

    public float getNota4() {
        return nota4;
    }

    public void setNota4(float nota4) {
        this.nota4 = nota4;
    }
    
    public float getMedia(){
        float soma = nota1+nota2+nota3+nota4;
        if(soma == 0)
            return 0;
        
        return soma/4;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlunosVO other = (AlunosVO) obj;
        if (this.matricula != other.matricula) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\nAlunosVO{" + " Matricula= " + matricula + ", Nome= " + nome + ", Media= " +getMedia() +'}' + "\n";
    }
}
