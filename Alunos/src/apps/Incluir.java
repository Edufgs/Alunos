package apps;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import vo.AlunosVO;

/**
 * Classe que inclui objetos no banco de dados
 * 
 * @author Eduardo Gonçalves da Silva
 */
public class Incluir {
    public static void main(String[] args) {
        EntityManagerFactory emf = null; //representa os objetos de fábrica de gerenciadores – usada para criar os gerenciadores de entidades
        EntityManager em =null; //representa os objetos manipuladores de entidades e transações
        
        AlunosVO aluno = new AlunosVO();
        
        try{
            aluno.setNome(JOptionPane.showInputDialog("Forneca o nome do aluno"));
            aluno.setNota1(Float.parseFloat(JOptionPane.showInputDialog("Forneca a nota 1")));
            aluno.setNota2(Float.parseFloat(JOptionPane.showInputDialog("Forneca a nota 2")));
            aluno.setNota3(Float.parseFloat(JOptionPane.showInputDialog("Forneca a nota 3")));
            aluno.setNota4(Float.parseFloat(JOptionPane.showInputDialog("Forneca a nota 4")));
            
            emf = Persistence.createEntityManagerFactory("UnidadeAlunos"); //criando a fabrica
            em = emf.createEntityManager(); //pede o EntityManager que realmenre faz as ações com o banco de dados
            em.getTransaction().begin(); //Inicia uma transação    
            em.persist(aluno);//faz a persistencia de um objeto no banco
            em.getTransaction().commit();//confirma a inclusão           
            JOptionPane.showMessageDialog(null, "Inclusao realizada com sucesso");
            
        }catch(Exception ex){
            System.out.println("Inclusao nao realizada − " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Inclusao nao realizada − " + ex.getMessage(), "Erro",JOptionPane.ERROR_MESSAGE);
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
