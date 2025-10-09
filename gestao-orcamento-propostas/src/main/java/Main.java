import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            // Tentar criar o EntityManagerFactory
            System.out.println("Tentando conectar ao banco de dados...");
            emf = Persistence.createEntityManagerFactory("persistenciaPU");

            // Tentar criar o EntityManager
            em = emf.createEntityManager();

            // Se chegou aqui, a conexão foi bem-sucedida
            System.out.println("✓ Conexão com o banco de dados estabelecida com sucesso!");

            // Opcional: verificar se o banco está respondendo
            em.getTransaction().begin();
            em.getTransaction().commit();
            System.out.println("✓ Transação de teste executada com sucesso!");

        } catch (Exception e) {
            System.err.println("✗ Erro ao conectar com o banco de dados:");
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}