package net.cn.ssd.config;

import net.cn.ssd.quartz.WeatherTask;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    @Value("${cron.cronExpression}")
    private String cronExpression;

    // 定义要执行的HelloTask任务类
    @Bean
    public JobDetail helloJobDetail() {
        return JobBuilder.newJob(WeatherTask.class).withIdentity("weatherTask").storeDurably().build();
    }

    // Simple触发器定义与设置
//    @Bean
//    public SimpleTrigger sayHelloBySimpleTrigger(){
//        // Simple类型：可设置时间间隔、是否重复、触发频率（misfire机制）等
//        SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(5).repeatForever();
//
//        // 一个Trigger只对应一个Job，Schedule调度器调度Trigger执行对应的Job
//        SimpleTrigger sTrigger = TriggerBuilder.newTrigger().forJob(helloJobDetail()).
//                withIdentity("helloSimpleTrigger").withDescription("simple类型的触发器").withSchedule(ssb).build();
//        return sTrigger;
//    }

    @Bean
    public CronTrigger sayHelloByCronTrigger(){
        // Cron类型：通过cron表达式设置触发规则
        CronScheduleBuilder csb = CronScheduleBuilder.cronSchedule(String.format(cronExpression))
                .withMisfireHandlingInstructionDoNothing();

        // 一个Trigger只对应一个Job，Schedule调度器调度Trigger执行对应的Job
        CronTrigger cTrigger = TriggerBuilder.newTrigger().forJob(helloJobDetail()).
                withIdentity("helloCronTrigger").withDescription("corn类型的触发器").withSchedule(csb).startNow().build();
        return cTrigger;
    }
}
