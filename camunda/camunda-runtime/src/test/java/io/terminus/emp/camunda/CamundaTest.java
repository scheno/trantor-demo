package io.terminus.emp.camunda;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenchen
 * @version 1.0
 * @date 2021/8/15 7:22 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CamundaTest {

    /**
     * 整个项目流程id
     */
    public static final String PROCESS_ID  = "project";

    /**
     * 学生id变量
     */
    public static final String VAR_NAME_STUDENT = "student";

    /**
     * 学院名称变量
     */
    public static final String VAR_NAME_SCHOOL = "school";

    /**
     * 表单字段名
     * 项目申请记录id
     */
    public static final String FORM_RECORD_ID = "recordId";

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Test
    public void startProcessTest() {
        String projectId = "project";
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(VAR_NAME_SCHOOL, "上海交通大学");
        variables.put(VAR_NAME_STUDENT, "宇智波佐助");
        variables.put(FORM_RECORD_ID, "5a330d4c-6704-4d14-ba9a-3ab9797e334a");

        ProcessInstance instance = runtimeService.
                startProcessInstanceByKey(projectId, variables);
        System.out.println(instance.getId());
        System.out.println(instance.getBusinessKey());
        System.out.println(instance.getCaseInstanceId());
        System.out.println(instance.getProcessInstanceId());
    }

    @Test
    public void taskQueryTest() {
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("project")
                .list();
        for (Task task : tasks) {
            System.out.println(task.getAssignee());
            System.out.println(task.getId());
            System.out.println(task.getName());
            System.out.println(task.getTenantId());
        }
    }

    public void taskCompleteTest() {
        //目前lisi只有一个任务，业务中根据场景选择其他合适的方式
        Task task = taskService.createTaskQuery()
                .taskId("0a77c449-fdae-11eb-9855-fa6451573302")
                .singleResult();
        taskService.saveTask(task);
        Map<String, Object> params = new HashMap<>();
        params.put("extra_info_1", false);
        params.put("approved_1", true);
        taskService.complete(task.getId(), params);
    }
}
