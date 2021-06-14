package processes;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName:processes
 * @ClassName:HolidayProcessesTest
 * @Description:
 * @author: 悟空
 * @date: 2021/4/28 19:02
 * @email: 10947@163.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@SpringBootTest
@Slf4j
public class HolidayProcessesTest {

    private static final ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();



    /**
     * 开启请假流程
     * @author 悟空
     * @description //TODO
     * @date 19:04 2021/4/28
     * @param
     */
    @Test
    public void start(){
        String instanceKey = "myProcess_1";
        log.info("开启请假流程...");
        //在holiday.bpmn中，填写请假单的任务办理人为动态传入的userId,此处模拟一个id
        RuntimeService runtimeService=processEngine.getRuntimeService(); // 任务Service
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(instanceKey);
        log.info("启动流程实例成功:{}", instance);
        log.info("流程实例ID:{}", instance.getId());
        log.info("流程定义ID:{}", instance.getProcessDefinitionId());
    }


    /**
     * 查看任务
     * @author 悟空
     * @description //TODO
     * @date 11:36 2021/4/29
     * @param
     */
    @Test
    public void queryMyTask() throws Exception{
        //指定任务办理者
        String assignee = "1";
        //查询任务列表
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()  //创建任务查询对象
                .taskAssignee(assignee)   //指定个人任务办理人
                .list();
        //遍历集合查看内容
        for(Task task:tasks){
            log.info("taskId:{} ,taskName:{} ",task.getId(),task.getName());
        }

    }


    /**
     * 查看流程定义
     * @author 悟空
     * @description //TODO
     * @date 11:16 2021/4/29
     * @param
     */
    @Test
    public void queryProcessDefinition() throws Exception{
        //获取仓库服务对象，使用版本的升级排序，查询列表
        List<ProcessDefinition> pdList = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc()
                .list();
        //遍历集合查看内容
        for(ProcessDefinition pd: pdList){
            log.info("id:{}",pd.getId());
            log.info("name:{}",pd.getName());
            log.info("key:{}",pd.getKey());
            log.info("version:{}",pd.getVersion());
            log.info("resouceName:{}",pd.getResourceName());
            log.info("deploymentId:{}",pd.getDeploymentId());
        }
    }



    /**
     * 填写请假单
     * @author 悟空
     * @description //TODO
     * @date 19:48 2021/4/28
     * @param
     */
    @Test
    public void employeeApply(){
        String instanceId = "1";   //任务ID
        String leaveDays = "10"; // 请假天数
        String leaveReason = "回老家结婚"; // 请假原因
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        if (task == null) {
            log.info("任务ID:{}查询到任务为空！", instanceId);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("days", leaveDays);
        map.put("date", new Date());
        map.put("reason", leaveReason);
        map.put("userId","10001");
        taskService.complete(task.getId(),map);
        log.info("执行【员工申请】环节，流程推动到【上级审核】环节");
    }

    @Test
    public void showTaskVariable(){
        String instanceId = "1";   //任务ID
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        String days = (String) taskService.getVariable(task.getId(),"days");
        String reason = (String) taskService.getVariable(task.getId(), "reason");
        String userId = (String) taskService.getVariable(task.getId(), "userId");
        Date date = (Date) taskService.getVariable(task.getId(), "date");
        System.out.println("请假天数:  " + days);
        System.out.println("请假理由:  " + reason);
        System.out.println("请假人id:  " + userId);
        System.out.println("请假日期:  " + date.toString());
    }

    @Test
    public void departmentAudit() {
        String instanceId = "5001"; // 任务ID
        String departmentalOpinion = "早去早回";
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("departmentalOpinion", departmentalOpinion);
        taskService.complete(task.getId(), map);
        log.info("添加审批意见,请假流程结束");
    }

}
