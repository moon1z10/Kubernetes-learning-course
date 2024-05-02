package hodlene.k8s.todo_mysql_web_application.service;

import hodlene.k8s.todo_mysql_web_application.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUsername(String user);
}