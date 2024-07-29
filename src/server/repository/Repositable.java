package server.repository;

public interface Repositable {
    String getHistory();
    void saveLogMessage(String message);
}
