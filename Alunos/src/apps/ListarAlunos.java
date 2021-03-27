package apps;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import vo.AlunosVO;

/**
 * Lista todos os alunos no banco de dados
 * 
 * @author Eduardo Gonçalves da Silva
 */
public class ListarAlunos {
    public static void main(String[] args) {
        EntityManagerFactory emf = null; //representa os objetos de fábrica de gerenciadores – usada para criar os gerenciadores de entidades
        EntityManager em =null; //representa os objetos manipuladores de entidades e transações
        
        try{                    
            emf = Persistence.createEntityManagerFactory("UnidadeAlunos"); //criando a fabrica
            em = emf.createEntityManager(); //pede o EntityManager que realmenre faz as ações com o banco de dados
            em.getTransaction().begin(); //Inicia uma transação
            
            Query consulta = em.createQuery("SELECT alunos FROM AlunosVO alunos ORDER BY alunos.nome"); //imprime tudo em ordem de nome
            
            List<AlunosVO> listaAlunos = consulta.getResultList(); //busca e recebe em formato de lista
            
            System.out.println(listaAlunos.toString());
            
            JOptionPane.showMessageDialog(null, listaAlunos.toString(), "Alunos", JOptionPane.WIDTH);
            
        }catch(Exception ex){
            System.out.println("Erro ao listar alunos: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar alunos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
