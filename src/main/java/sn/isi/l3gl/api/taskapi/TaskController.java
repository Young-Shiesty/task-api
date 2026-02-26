package sn.isi.l3gl.api.taskapi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import sn.isi.l3gl.core.entity.Task;
import sn.isi.l3gl.core.entity.TaskStatus;
import sn.isi.l3gl.core.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//c pour  que le taskService puisse etre utiliser car il est dans le taskcore
@ComponentScan(basePackages = {
        "sn.isi.l3gl.api",
        "sn.isi.l3gl.core"
})

@RestController
@RequestMapping("/api/tasks")


public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(
                taskService.createTask(task.getTitle(), task.getDescription())
        );
    }
    @GetMapping
    public ResponseEntity<?> listTasks() {
        return ResponseEntity.ok(taskService.listTasks());
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(
                taskService.updateStatus(id, TaskStatus.valueOf(status))
        );
    }
    @GetMapping("/done/count")
    public ResponseEntity<?> countCompletedTasks() {
        return ResponseEntity.ok(taskService.countCompletedTasks());
    }
}