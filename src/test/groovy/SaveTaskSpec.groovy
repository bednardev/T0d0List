import com.todolist.models.Color
import com.todolist.models.TaskStatus
import org.springframework.web.bind.MethodArgumentNotValidException

class TaskAdditionSpecification extends Specification {

    TaskService taskService = new TaskService()

    def "should save new task"()

    {
        given:
        TaskDto taskDto = new TaskDto(
                title: "Task",
                description: "Task test",
                color: Color.BLUE
        )

        when:
        TaskDto taskDtoToSave = taskService.saveTask(taskDto)

        then:
        TaskDto savedTaskDto = taskService.findById(taskDtoToSave.getId())
        savedTaskDto.getTitle() == "Task"
        savedTaskDto.getDescription() == "Task test"
        savedTaskDto.getColor() == Color.BLUE
        savedTaskDto.getTaskStatus() == TaskStatus.BACKLOG
    }

    def "should throw MethodArgumentNotValidException empty title"() {
        given:
        TaskDto taskDto = new TaskDto(
                title: "",
                description: "Task test",
                color: Color.BLUE
        )

        when:
        TaskDto taskDtoToSave = taskService.saveTask(taskDto)

        then:
        thrown(MethodArgumentNotValidException)
    }

    def "should throw MethodArgumentNotValidException wrong color value"() {
        given:
        TaskDto taskDto = new TaskDto(
                title: "Task",
                description: "Task test",
                color: "yellow"
        )

        when:
        TaskDto taskDtoToSave = taskService.saveTask(taskDto)

        then:
        thrown(MethodArgumentNotValidException)
    }
}
