package hodlene.k8s.todo_mysql_web_application.controller;

import hodlene.k8s.todo_mysql_web_application.model.Todo;
import hodlene.k8s.todo_mysql_web_application.service.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository repository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping(value = "/list-todos")
    public String showTodos(ModelMap model) {
        String name = getLoggedInUserName(model);
        model.put("todos", repository.findByUsername(name));

        return "list-todos";
    }

    @GetMapping(value = "/add-todo")
    public String showAddTodoPage(ModelMap model) {
        Todo newTodo = Todo.builder()
                .id(0)
                .description("Default Desc")
                .isDone(false)
                .targetDate(new Date())
                .username(getLoggedInUserName(model))
                .build();
        model.addAttribute("todo", newTodo);
//        model.addAttribute("todo", new Todo(0, "Default Desc", false, new Date(), getLoggedInUserName(model)));
        return "todo";
    }

    @GetMapping(value = "/update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = repository.findById(id).get();
        model.put("todo", todo);
        return "todo";
    }

    @GetMapping(value = "/delete-todo")
    public String deleteTodo(@RequestParam int id) {
        repository.deleteById(id);
        return "redirect:/list-todos";
    }

    @PostMapping(value = "/add-todo")
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUsername(getLoggedInUserName(model));
        repository.save(todo);

        return "redirect:/list-todos";
    }

    @PostMapping(value = "/update-todo")
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUsername(getLoggedInUserName(model));
        repository.save(todo);

        return "redirect:/list-todos";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }
}
