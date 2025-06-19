package com.estoqueApi.estoque_api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DatabaseCreator {

    private static final String JDBC_URL = "jdbc:h2:./testdb"; // Caminho relativo para o arquivo do banco de dados
    private static final String USER = "sa"; // Usuário padrão
    private static final String PASSWORD = ""; // Senha padrão (deixe em branco para começar)

    public static void main(String[] args) {
        try {
            // 1. Carregar o driver JDBC (opcional para JDBC 4.0+, mas boa prática)
            Class.forName("org.h2.Driver");

            // 2. Estabelecer a conexão com o banco de dados
            // Se 'testdb.mv.db' não existir, ele será criado
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
                System.out.println("Conexão com o banco de dados H2 estabelecida com sucesso!");

                // 3. Criar uma tabela (exemplo)
                createTable(connection);

                // 4. Inserir alguns dados (exemplo)
                insertData(connection);

                System.out.println("Banco de dados e tabela criados e populados!");

            } catch (SQLException e) {
                System.err.println("Erro ao conectar ou operar no banco de dados: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Driver H2 JDBC não encontrado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS usuarios (" +
                                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                                    "nome VARCHAR(255) NOT NULL," +
                                    "email VARCHAR(255) UNIQUE NOT NULL" +
                                    ");";
            statement.execute(createTableSQL);
            System.out.println("Tabela 'usuarios' criada ou já existente.");
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String insertSQL1 = "INSERT INTO usuarios (nome, email) VALUES ('João Silva', 'joao.silva@example.com');";
            String insertSQL2 = "INSERT INTO usuarios (nome, email) VALUES ('Maria Souza', 'maria.souza@example.com');";

            statement.execute(insertSQL1);
            statement.execute(insertSQL2);
            System.out.println("Dados inseridos na tabela 'usuarios'.");
        }
    }
}