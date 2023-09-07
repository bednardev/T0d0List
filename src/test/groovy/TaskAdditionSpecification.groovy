import com.todolist.models.Color
import com.todolist.models.TaskStatus

class TaskAdditionSpecification extends Specification {

     TaskService taskService = new TaskService()

    def "should save new task"()

    {
        given:
        TaskDto taskDto = new TaskDto(
        title : "Elo",
        description : "Melo",
        color : Color.BLUE
        )

        when:
        TaskDto savedTask = taskService.saveTask(taskDto)

        then:
        savedTask != null
        savedTask.title == "Elo"
        savedTask.description == "Melo"
        savedTask.color = Color.BLUE
        savedTask.taskStatus = TaskStatus.BACKLOG;
    }
}
