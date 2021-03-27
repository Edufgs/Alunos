package apps;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import vo.AlunosVO;

/**
 * Classe que altera objetos persistidos
 * 
 * @author Eduardo Gonçalves da Silva
 */
public class Alterar {
    public static void main(String[] args) {
        EntityManagerFactory emf = null; //representa os objetos de fábrica de gerenciadores – usada para criar os gerenciadores de entidades
        EntityManager em =null; //representa os objetos manipuladores de entidades e transações
        
        AlunosVO aluno = null;
        
        try{
            emf = Persistence.createEntityManagerFactory("UnidadeAlunos"); //criando a fabrica
            em = emf.createEntityManager(); //pede o EntityManager que realmenre faz as ações com o banco de dados
            em.getTransaction().begin(); //Inicia uma transação
            
            String nome = JOptionPane.showInputDialog("Forneca o nome para localizar");
            /*
            objeto Query com o script.
            Seleciona o alunos e recupera a partir da entidade AlunosVO.
            alunos é um apelido dado a AlunosVO.
            UPPER: Transforma tudo em maiusculo
            alunos.nome : é o objeto com a area nome.   
            Compara com o nome digitado
            */
            Query consulta = em.createQuery("SELECT alunos FROM AlunosVO alunos WHERE UPPER(alunos.nome) = :nome"); 
            consulta.setParameter("nome", nome.toUpperCase());//Esta passando o parametro para o script em miusculo.
            List<AlunosVO> listaAlunos = consulta.getResultList(); //busca e recebe em formato de lista
            if(!listaAlunos.isEmpty()){
                aluno = listaAlunos.get(0);
                aluno.setNome(JOptionPane.showInputDialog("Forneca o nome do aluno", aluno.getNome()));
                aluno.setNota1(Float.parseFloat(JOptionPane.showInputDialog("Forneca a nota 1", aluno.getNota1())));
                aluno.setNota2(Float.parseFloat(JOptionPane.showInputDialog("Forneca a nota 2", aluno.getNota2())));
                aluno.setNota3(Float.parseFloat(JOptionPane.showInputDialog("Forneca a nota 3", aluno.getNota3())));
                aluno.setNota4(Float.parseFloat(JOptionPane.showInputDialog("Forneca a nota 4", aluno.getNota4())));
                
                em.merge(aluno);//Faz a alteração usando o metodo merge
                em.getTransaction().commit(); //Confirma a transação
                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso");                                
            }else{
                JOptionPane.showMessageDialog(null, "Produto não localizado");
            }
        }catch(Exception ex){
            System.out.println("Alteração nao realizada − " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Alteração nao realizada − " + ex.getMessage(), "Erro",JOptionPane.ERROR_MESSAGE);
        }finally{
            if(em!=null){
                em.close();
            }
            if(emf!=null){ 
                emf.close();
            }
        }    
    }
}
