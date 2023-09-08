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
        TaskDto taskDtoToSave = taskService.saveTask(taskDto)

        then:
        TaskDto savedTaskDto = taskService.findById(taskDtoToSave.getId())
        savedTaskDto.getTitle() == "Elo"
        savedTaskDto.getDescription() == "Melo"
        savedTaskDto.getColor() == Color.BLUE
        savedTaskDto.getTaskStatus() == TaskStatus.BACKLOG
    }
}
